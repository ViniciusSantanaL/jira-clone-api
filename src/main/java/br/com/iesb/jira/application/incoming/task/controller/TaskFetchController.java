package br.com.iesb.jira.application.incoming.task.controller;

import br.com.iesb.jira.application.incoming.task.commons.converter.TaskConverter;
import br.com.iesb.jira.application.incoming.task.commons.response.TaskResponse;
import br.com.iesb.jira.application.incoming.task.controller.api.TaskFetchApi;
import br.com.iesb.jira.domain.task.service.TaskFetchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskFetchController implements TaskFetchApi {

    private final TaskFetchService taskFetchService;

    public TaskFetchController(TaskFetchService taskFetchService) {
        this.taskFetchService = taskFetchService;
    }

    @Override
    public Page<TaskResponse> fetchAllTask(final Pageable pageable) {
        return taskFetchService.fetchAllTask(pageable)
                .map(TaskConverter::toResponse);
    }

    @Override
    public TaskResponse fetchTaskById(final UUID taskId) {
        return TaskConverter.toResponse(taskFetchService.fetchTaskById(taskId));
    }
}
