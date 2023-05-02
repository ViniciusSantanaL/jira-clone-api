package br.com.iesb.jira.application.incoming.auth.controller;

import br.com.iesb.jira.application.incoming.auth.commons.converter.AuthenticationConverter;
import br.com.iesb.jira.application.incoming.auth.commons.request.AuthenticationRequest;
import br.com.iesb.jira.application.incoming.auth.commons.response.AuthenticateResponse;
import br.com.iesb.jira.domain.user.model.User;
import br.com.iesb.jira.infrastructure.exception.NotHaveAccessException;
import br.com.iesb.jira.infrastructure.security.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AuthenticateResponse authenticate(@RequestBody @Valid AuthenticationRequest request) {
        Authentication authenticate = null;
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
