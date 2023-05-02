package br.com.iesb.jira.application.incoming.auth.controller;

import br.com.iesb.jira.application.incoming.auth.commons.converter.SingUpConverter;
import br.com.iesb.jira.application.incoming.auth.commons.request.SignUpRequest;
import br.com.iesb.jira.application.incoming.auth.commons.response.SignUpResponse;
import br.com.iesb.jira.domain.user.service.UserCreateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/v1/sign-up")
public class SignUpController {

    private final UserCreateService userCreateService;

    @Autowired
    public SignUpController(UserCreateService userCreateService) {
        this.userCreateService = userCreateService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpResponse createUser(@RequestBody @Valid SignUpRequest request) {
        return SingUpConverter.toResponse(userCreateService.create(SingUpConverter.of(request)));
    }
}
