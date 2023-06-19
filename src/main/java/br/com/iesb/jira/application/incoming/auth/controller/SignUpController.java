package br.com.iesb.jira.application.incoming.auth.controller;

import br.com.iesb.jira.application.incoming.auth.commons.converter.SingUpConverter;
import br.com.iesb.jira.application.incoming.auth.commons.request.SignUpRequest;
import br.com.iesb.jira.application.incoming.auth.commons.response.SignUpResponse;
import br.com.iesb.jira.application.incoming.auth.controller.api.SingUpApi;
import br.com.iesb.jira.domain.user.service.UserCreateService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sign-up")
public class SignUpController implements SingUpApi {

    private final UserCreateService userCreateService;

    public SignUpController(UserCreateService userCreateService) {
        this.userCreateService = userCreateService;
    }

    @Override
    public SignUpResponse createUser(@RequestBody @Valid SignUpRequest request) {
        return SingUpConverter.toResponse(userCreateService.create(SingUpConverter.of(request)));
    }
}
