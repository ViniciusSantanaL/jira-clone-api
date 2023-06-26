package br.com.iesb.jira.application.incoming.user.controller;

import br.com.iesb.jira.application.incoming.user.commons.converter.UserConverter;
import br.com.iesb.jira.application.incoming.user.commons.response.UserResponse;
import br.com.iesb.jira.application.incoming.user.controller.api.UserFetchApi;
import br.com.iesb.jira.domain.user.service.UserFetchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping(name = "/api/v1/users")
@RestController
public class UserFetchController implements UserFetchApi {
    private final UserFetchService userFetchService;

    public UserFetchController(UserFetchService userFetchService) {
        this.userFetchService = userFetchService;
    }

    @Override
    public Page<UserResponse> fetchAllUsers(Pageable pageable) {
        return userFetchService.fetchAllUsers(pageable)
                .map(UserConverter::of);
    }

    @Override
    public UserResponse fetchUserById(UUID userId) {
        return UserConverter.of(userFetchService.fetchUserById(userId));
    }
}
