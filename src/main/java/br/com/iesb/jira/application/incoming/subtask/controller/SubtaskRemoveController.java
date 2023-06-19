package br.com.iesb.jira.application.incoming.subtask.controller;

import br.com.iesb.jira.application.incoming.subtask.controller.api.SubtaskRemoveApi;
import br.com.iesb.jira.domain.subtask.service.SubtaskRemoveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/subtasks")
public class SubtaskRemoveController implements SubtaskRemoveApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubtaskRemoveController.class);
    private final SubtaskRemoveService subtaskRemoveService;

    public SubtaskRemoveController(SubtaskRemoveService subtaskRemoveService) {
        this.subtaskRemoveService = subtaskRemoveService;
    }

    @Override
    public void deleteSubtaskById(final UUID subtaskId) {
        LOGGER.info("deleteSubtaskById, subtaskId: {}", subtaskId);
        subtaskRemoveService.deleteSubtaskById(subtaskId);
    }
}
