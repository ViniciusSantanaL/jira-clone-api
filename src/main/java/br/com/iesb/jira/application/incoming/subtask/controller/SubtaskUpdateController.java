package br.com.iesb.jira.application.incoming.subtask.controller;

import br.com.iesb.jira.application.incoming.subtask.commons.converter.SubtaskConverter;
import br.com.iesb.jira.application.incoming.subtask.commons.request.SubtaskRequest;
import br.com.iesb.jira.application.incoming.subtask.commons.response.SubtaskResponse;
import br.com.iesb.jira.application.incoming.subtask.controller.api.SubtaskUpdateApi;
import br.com.iesb.jira.domain.subtask.service.SubtaskUpdateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/api/v1/subtasks")
@RestController
public class SubtaskUpdateController implements SubtaskUpdateApi {

    private final SubtaskUpdateService subtaskUpdateService;

    public SubtaskUpdateController(SubtaskUpdateService subtaskUpdateService) {
        this.subtaskUpdateService = subtaskUpdateService;
    }

    @Override
    public SubtaskResponse updateSubtaskById(final UUID subtaskId, final SubtaskRequest subtaskRequest) {
        return SubtaskConverter.toResponse(subtaskUpdateService.updateSubtaskById(SubtaskConverter.of(subtaskId, subtaskRequest)));
    }
}
