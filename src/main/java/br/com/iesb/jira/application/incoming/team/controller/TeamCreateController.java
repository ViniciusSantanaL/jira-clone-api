package br.com.iesb.jira.application.incoming.team.controller;

import br.com.iesb.jira.application.incoming.team.commons.converter.TeamConverter;
import br.com.iesb.jira.application.incoming.team.commons.request.TeamRequest;
import br.com.iesb.jira.application.incoming.team.commons.response.TeamResponse;
import br.com.iesb.jira.application.incoming.team.controller.api.TeamCreateApi;
import br.com.iesb.jira.domain.team.service.TeamCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/teams")
@RestController
public class TeamCreateController implements TeamCreateApi {

    private final static Logger LOGGER = LoggerFactory.getLogger(TeamCreateController.class);
    private final TeamCreateService teamCreateService;

    public TeamCreateController(TeamCreateService teamCreateService) {
        this.teamCreateService = teamCreateService;
    }

    @Override
    public TeamResponse createTeam(final TeamRequest request) {
        LOGGER.info("start create team, request: {}", request);
        return TeamConverter.toResponse(teamCreateService.createTeam(TeamConverter.of(request)));
    }
}
