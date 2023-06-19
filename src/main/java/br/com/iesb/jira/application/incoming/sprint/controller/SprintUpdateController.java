package br.com.iesb.jira.application.incoming.sprint.controller;

import br.com.iesb.jira.application.incoming.sprint.commons.converter.SprintConverter;
import br.com.iesb.jira.application.incoming.sprint.commons.request.SprintRequest;
import br.com.iesb.jira.application.incoming.sprint.commons.response.SprintResponse;
import br.com.iesb.jira.application.incoming.sprint.controller.api.SprintUpdateApi;
import br.com.iesb.jira.domain.sprint.service.SprintUpdateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/api/v1/sprints")
@RestController
public class SprintUpdateController implements SprintUpdateApi {

    private final SprintUpdateService sprintUpdateService;

    public SprintUpdateController(SprintUpdateService sprintUpdateService) {
        this.sprintUpdateService = sprintUpdateService;
    }

    @Override
    public SprintResponse updateSprintById(final UUID sprintId, final SprintRequest sprintRequest) {
        return SprintConverter.toResponse(sprintUpdateService.updateSprintById(SprintConverter.of(sprintId, sprintRequest)));
    }
}
