package br.com.iesb.jira.application.incoming.task.controller;

import br.com.iesb.jira.application.incoming.task.commons.converter.TaskConverter;
import br.com.iesb.jira.application.incoming.task.commons.request.TaskRequest;
import br.com.iesb.jira.application.incoming.task.commons.response.TaskResponse;
import br.com.iesb.jira.application.incoming.task.controller.api.TaskUpdateApi;
import br.com.iesb.jira.domain.task.service.TaskUpdateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/api/v1/tasks")
@RestController
public class TaskUpdateController implements TaskUpdateApi {

    private final TaskUpdateService taskUpdateService;

    public TaskUpdateController(TaskUpdateService taskUpdateService) {
        this.taskUpdateService = taskUpdateService;
    }

    @Override
    public TaskResponse updateTaskById(final UUID taskId, final TaskRequest taskRequest) {
        return TaskConverter.toResponse(taskUpdateService.updateTaskById(TaskConverter.of(taskId, taskRequest)));
    }
}
