package br.com.iesb.jira.domain.user.service;

import br.com.iesb.jira.domain.user.model.User;
import br.com.iesb.jira.domain.user.repository.UserRepository;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserFetchService {

    private final UserRepository userRepository;

    public UserFetchService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> fetchAllUsers(final Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User fetchUserById(final UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->  new EntityNotFoundException("user", "user_id"));
    }
}
