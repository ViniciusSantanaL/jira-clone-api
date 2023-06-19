package br.com.iesb.jira.application.incoming.sprint.controller;

import br.com.iesb.jira.application.incoming.sprint.controller.api.SprintRemoveApi;
import br.com.iesb.jira.domain.sprint.service.SprintRemoveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sprints")
public class SprintRemoveController implements SprintRemoveApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(SprintRemoveController.class);
    private final SprintRemoveService sprintRemoveService;

    public SprintRemoveController(SprintRemoveService sprintRemoveService) {
        this.sprintRemoveService = sprintRemoveService;
    }

    @Override
    public void deleteSprintById(final UUID sprintId) {
        LOGGER.info("deleteSprintById, sprintId: {}", sprintId);
        sprintRemoveService.deleteSprintById(sprintId);
    }
}
