package br.com.iesb.jira.application.incoming.auth.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "auth")
public interface RecoverPasswordApi {

    @PostMapping
    @Operation(summary = "Recovery passowrd by send email",
            description = "Recovery passowrd by send email", tags = "auth")
    void recoverPassowrd();
}
