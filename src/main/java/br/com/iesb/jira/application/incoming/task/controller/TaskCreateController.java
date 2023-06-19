package br.com.iesb.jira.application.incoming.task.controller;

import br.com.iesb.jira.application.incoming.task.commons.converter.TaskConverter;
import br.com.iesb.jira.application.incoming.task.commons.request.TaskRequest;
import br.com.iesb.jira.application.incoming.task.commons.response.TaskResponse;
import br.com.iesb.jira.application.incoming.task.controller.api.TaskCreateApi;
import br.com.iesb.jira.domain.task.service.TaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/tasks")
@RestController
public class TaskCreateController implements TaskCreateApi {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskCreateController.class);
    private final TaskCreateService taskCreateService;

    public TaskCreateController(TaskCreateService taskCreateService) {
        this.taskCreateService = taskCreateService;
    }

    @Override
    public TaskResponse createTask(final TaskRequest request) {
        LOGGER.info("start create task, request: {}", request);
        return TaskConverter.toResponse(taskCreateService.createTask(TaskConverter.of(request)));
    }
}
