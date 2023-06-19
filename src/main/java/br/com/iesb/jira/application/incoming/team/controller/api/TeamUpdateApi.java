package br.com.iesb.jira.application.incoming.team.controller.api;

import br.com.iesb.jira.application.incoming.team.commons.request.TeamRequest;
import br.com.iesb.jira.application.incoming.team.commons.response.TeamResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Tag(name = "teams")
public interface TeamUpdateApi {

    @PutMapping("/{team_id}")
    @ResponseStatus(HttpStatus.OK)
    TeamResponse updateTeamById(@PathVariable(name = "team_id") final UUID teamId,
                                @RequestBody @Valid final TeamRequest teamRequest);

}
