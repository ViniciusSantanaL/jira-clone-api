package br.com.iesb.jira.application.incoming.subtask.controller;

import br.com.iesb.jira.application.incoming.subtask.commons.converter.SubtaskConverter;
import br.com.iesb.jira.application.incoming.subtask.commons.request.SubtaskRequest;
import br.com.iesb.jira.application.incoming.subtask.commons.response.SubtaskResponse;
import br.com.iesb.jira.application.incoming.subtask.controller.api.SubtaskCreateApi;
import br.com.iesb.jira.domain.subtask.service.SubtaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/subtasks")
@RestController
public class SubtaskCreateController implements SubtaskCreateApi {

    private final static Logger LOGGER = LoggerFactory.getLogger(SubtaskCreateController.class);
    private final SubtaskCreateService subtaskCreateService;

    public SubtaskCreateController(SubtaskCreateService subtaskCreateService) {
        this.subtaskCreateService = subtaskCreateService;
    }

    @Override
    public SubtaskResponse createSubtask(final SubtaskRequest request) {
        LOGGER.info("start create subtask, request: {}", request);
        return SubtaskConverter.toResponse(subtaskCreateService.createSubtask(SubtaskConverter.of(request)));
    }
}
