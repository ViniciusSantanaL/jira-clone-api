package br.com.iesb.jira.application.incoming.user.controller.api;

import br.com.iesb.jira.application.incoming.user.commons.response.UserResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Tag(name = "users")
public interface UserFetchApi {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Page<UserResponse> fetchAllUsers(final Pageable pageable);

    @GetMapping("/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    UserResponse fetchUserById(@PathVariable(value = "user_id") final UUID userId);

}
