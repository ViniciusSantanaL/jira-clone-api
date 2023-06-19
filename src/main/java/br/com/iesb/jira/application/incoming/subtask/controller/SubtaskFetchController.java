package br.com.iesb.jira.application.incoming.subtask.controller;

import br.com.iesb.jira.application.incoming.subtask.commons.converter.SubtaskConverter;
import br.com.iesb.jira.application.incoming.subtask.commons.response.SubtaskResponse;
import br.com.iesb.jira.application.incoming.subtask.controller.api.SubtaskFetchApi;
import br.com.iesb.jira.domain.subtask.service.SubtaskFetchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/subtasks")
public class SubtaskFetchController implements SubtaskFetchApi {

    private final SubtaskFetchService subtaskFetchService;

    public SubtaskFetchController(SubtaskFetchService subtaskFetchService) {
        this.subtaskFetchService = subtaskFetchService;
    }

    @Override
    public Page<SubtaskResponse> fetchAllSubtask(final Pageable pageable) {
        return subtaskFetchService.fetchAllSubtask(pageable)
                .map(SubtaskConverter::toResponse);
    }

    @Override
    public SubtaskResponse fetchSubtaskById(final UUID subtaskId) {
        return SubtaskConverter.toResponse(subtaskFetchService.fetchSubtaskById(subtaskId));
    }
}
