package br.com.iesb.jira.application.incoming.auth.controller;

import br.com.iesb.jira.application.incoming.auth.commons.converter.AuthenticationConverter;
import br.com.iesb.jira.application.incoming.auth.commons.request.AuthenticationRequest;
import br.com.iesb.jira.application.incoming.auth.commons.response.AuthenticateResponse;
import br.com.iesb.jira.application.incoming.auth.controller.api.AuthenticationApi;
import br.com.iesb.jira.domain.user.model.User;
import br.com.iesb.jira.infrastructure.exception.NotHaveAccessException;
import br.com.iesb.jira.infrastructure.security.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController implements AuthenticationApi {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }
    @Override
    public AuthenticateResponse authenticate(AuthenticationRequest request) {
        Authentication authenticate;
        try {
            authenticate =  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserUsername(), request.getUserPassword()));
        } catch (InternalAuthenticationServiceException ex) {
            throw new NotHaveAccessException("Not Have Access or Creadentials are incorrect!");
        }

        final User user = (User) authenticate.getPrincipal();

        return AuthenticationConverter.toResponse(tokenService.generateToken(user));
    }
}
