package br.com.iesb.nicetask.domain.user.service;

import br.com.iesb.nicetask.domain.user.model.User;
import br.com.iesb.nicetask.domain.user.repository.UserRepository;
import br.com.iesb.nicetask.domain.user.vo.UserVO;
import br.com.iesb.nicetask.domain.user.vo.builder.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCreateService {

    private final UserRepository userRepository;

    @Autowired
    public UserCreateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserVO create(UserVO userVO) {
        User newUser = userRepository.save(UserBuilder.create(userVO));
        return UserBuilder.of(newUser);
    }

}
