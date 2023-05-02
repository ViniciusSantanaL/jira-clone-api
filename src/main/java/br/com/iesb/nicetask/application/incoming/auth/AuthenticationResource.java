package br.com.iesb.nicetask.application.incoming.auth;

import br.com.iesb.nicetask.application.incoming.auth.commons.request.AuthenticationRequest;
import br.com.iesb.nicetask.infrastructure.security.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationResource {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    private final TokenService tokenService;

    @Autowired
    public AuthenticationResource(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenService = tokenService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> authenticate(@RequestBody @Valid  AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.userUsername(), request.userPassword()));
        final UserDetails user = userDetailsService.loadUserByUsername(request.userUsername());
        if(user != null) {
            return ResponseEntity.ok(tokenService.generateToken(user));
        }
        return ResponseEntity.badRequest().body("Error");
    }
}
