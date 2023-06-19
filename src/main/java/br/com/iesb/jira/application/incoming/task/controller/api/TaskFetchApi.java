package br.com.iesb.jira.application.incoming.task.controller.api;

import br.com.iesb.jira.application.incoming.task.commons.response.TaskResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Tag(name = "tasks")
public interface TaskFetchApi {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Page<TaskResponse> fetchAllTask(final Pageable pageable);

    @GetMapping("/{task_id}")
    @ResponseStatus(HttpStatus.OK)
    TaskResponse fetchTaskById(@PathVariable(value = "task_id") final UUID taskId);

}