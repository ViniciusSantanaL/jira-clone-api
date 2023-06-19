package br.com.iesb.jira.application.incoming.auth.controller.api;

import br.com.iesb.jira.application.incoming.auth.commons.request.SignUpRequest;
import br.com.iesb.jira.application.incoming.auth.commons.response.SignUpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "auth")
public interface SingUpApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "SingUp user in App",
            description = "SingUp user in App", tags = "auth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = SignUpResponse.class))})
    })
    SignUpResponse createUser(
            @Parameter(description = "SingUp Data for create User", required = true)
            @RequestBody @Valid SignUpRequest request);

}
