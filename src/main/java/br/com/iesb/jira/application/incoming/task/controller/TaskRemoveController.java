package br.com.iesb.jira.application.incoming.task.controller;

import br.com.iesb.jira.application.incoming.task.controller.api.TaskRemoveApi;
import br.com.iesb.jira.domain.task.service.TaskRemoveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskRemoveController implements TaskRemoveApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskRemoveController.class);
    private final TaskRemoveService taskRemoveService;

    public TaskRemoveController(TaskRemoveService taskRemoveService) {
        this.taskRemoveService = taskRemoveService;
    }

    @Override
    public void deleteTaskById(final UUID taskId) {
        LOGGER.info("deleteTaskById, taskId: {}", taskId);
        taskRemoveService.deleteTaskById(taskId);
    }
}
