package br.com.iesb.jira.application.incoming.team.controller;

import br.com.iesb.jira.application.incoming.team.commons.converter.TeamConverter;
import br.com.iesb.jira.application.incoming.team.commons.response.TeamResponse;
import br.com.iesb.jira.application.incoming.team.controller.api.TeamFetchApi;
import br.com.iesb.jira.domain.team.service.TeamFetchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamFetchController implements TeamFetchApi {

    private final TeamFetchService teamFetchService;

    public TeamFetchController(TeamFetchService teamFetchService) {
        this.teamFetchService = teamFetchService;
    }

    @Override
    public Page<TeamResponse> fetchAllTeam(final Pageable pageable) {
        return teamFetchService.fetchAllTeam(pageable)
                .map(TeamConverter::toResponse);
    }

    @Override
    public TeamResponse fetchTeamById(final UUID teamId) {
        return TeamConverter.toResponse(teamFetchService.fetchTeamById(teamId));
    }
}
