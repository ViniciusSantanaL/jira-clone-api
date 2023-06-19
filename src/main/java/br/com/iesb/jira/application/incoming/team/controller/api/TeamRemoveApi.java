package br.com.iesb.jira.application.incoming.team.controller.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Tag(name = "teams")
public interface TeamRemoveApi {

    @DeleteMapping("/{team_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTeamById(@PathVariable(name = "team_id") final UUID teamId);
}