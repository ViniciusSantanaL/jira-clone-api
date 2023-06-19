package br.com.iesb.jira.application.incoming.sprint.controller;

import br.com.iesb.jira.application.incoming.sprint.commons.converter.SprintConverter;
import br.com.iesb.jira.application.incoming.sprint.commons.response.SprintResponse;
import br.com.iesb.jira.application.incoming.sprint.controller.api.SprintFetchApi;
import br.com.iesb.jira.domain.sprint.service.SprintFetchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sprints")
public class SprintFetchController implements SprintFetchApi {

    private final SprintFetchService sprintFetchService;

    public SprintFetchController(SprintFetchService sprintFetchService) {
        this.sprintFetchService = sprintFetchService;
    }

    @Override
    public Page<SprintResponse> fetchAllSprint(final Pageable pageable) {
        return sprintFetchService.fetchAllSprint(pageable)
                .map(SprintConverter::toResponse);
    }

    @Override
    public SprintResponse fetchSprintById(final UUID sprintId) {
        return SprintConverter.toResponse(sprintFetchService.fetchSprintById(sprintId));
    }
}
