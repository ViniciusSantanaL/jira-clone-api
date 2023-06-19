package br.com.iesb.jira.domain.user.service;

import br.com.iesb.jira.domain.user.model.User;
import br.com.iesb.jira.domain.user.repository.UserRepository;
import br.com.iesb.jira.domain.user.vo.UserVO;
import br.com.iesb.jira.domain.user.vo.builder.UserBuilder;
import br.com.iesb.jira.infrastructure.exception.EntityDuplicatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCreateService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserCreateService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserCreateService(UserRepository userRepository, PasswordEncoder  passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserVO create(UserVO userVO) {
        User newUser = save(UserBuilder.create(userVO, passwordEncoder));
        LOGGER.info("saveUser " + newUser.getId());
        return UserBuilder.of(newUser);
    }

    private User save(User user) {
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            LOGGER.error("Error to saveUser", ex);
            throw new EntityDuplicatedException("user", "username or email");
        }
    }

}
