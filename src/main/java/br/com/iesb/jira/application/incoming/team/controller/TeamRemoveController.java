package br.com.iesb.jira.application.incoming.team.controller;

import br.com.iesb.jira.application.incoming.team.controller.api.TeamRemoveApi;
import br.com.iesb.jira.domain.team.service.TeamRemoveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamRemoveController implements TeamRemoveApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamRemoveController.class);
    private final TeamRemoveService teamRemoveService;

    public TeamRemoveController(TeamRemoveService teamRemoveService) {
        this.teamRemoveService = teamRemoveService;
    }

    @Override
    public void deleteTeamById(final UUID teamId) {
        LOGGER.info("deleteTeamById, teamId: {}", teamId);
        teamRemoveService.deleteTeamById(teamId);
    }
}
