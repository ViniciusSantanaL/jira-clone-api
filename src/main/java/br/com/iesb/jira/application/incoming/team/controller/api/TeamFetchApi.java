package br.com.iesb.jira.application.incoming.team.controller.api;

import br.com.iesb.jira.application.incoming.team.commons.response.TeamResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Tag(name = "teams")
public interface TeamFetchApi {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Page<TeamResponse> fetchAllTeam(final Pageable pageable);

    @GetMapping("/{team_id}")
    @ResponseStatus(HttpStatus.OK)
    TeamResponse fetchTeamById(@PathVariable(value = "team_id") final UUID teamId);

}