package br.com.iesb.jira.application.incoming.team.controller.api;

import br.com.iesb.jira.application.incoming.team.commons.request.TeamRequest;
import br.com.iesb.jira.application.incoming.team.commons.response.TeamResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "teams")
public interface TeamCreateApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TeamResponse createTeam(final @RequestBody @Valid TeamRequest request);

}
