package br.com.iesb.jira.application.incoming.team.controller;

import br.com.iesb.jira.application.incoming.team.commons.converter.TeamConverter;
import br.com.iesb.jira.application.incoming.team.commons.request.TeamRequest;
import br.com.iesb.jira.application.incoming.team.commons.response.TeamResponse;
import br.com.iesb.jira.application.incoming.team.controller.api.TeamUpdateApi;
import br.com.iesb.jira.domain.team.service.TeamUpdateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/api/v1/teams")
@RestController
public class TeamUpdateController implements TeamUpdateApi {

    private final TeamUpdateService teamUpdateService;

    public TeamUpdateController(TeamUpdateService teamUpdateService) {
        this.teamUpdateService = teamUpdateService;
    }

    @Override
    public TeamResponse updateTeamById(final UUID teamId, final TeamRequest teamRequest) {
        return TeamConverter.toResponse(teamUpdateService.updateTeamById(TeamConverter.of(teamId,teamRequest)));
    }
}
