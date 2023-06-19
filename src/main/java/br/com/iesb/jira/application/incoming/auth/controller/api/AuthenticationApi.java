package br.com.iesb.jira.application.incoming.auth.controller.api;

import br.com.iesb.jira.application.incoming.auth.commons.request.AuthenticationRequest;
import br.com.iesb.jira.application.incoming.auth.commons.response.AuthenticateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "auth")
public interface AuthenticationApi {

    @Operation(summary = "Authenticate in Api", description = "Authenticate in Api", tags = "auth")
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    AuthenticateResponse authenticate(@RequestBody @Valid AuthenticationRequest request);

}
