package br.com.iesb.jira.application.incoming.task.controller.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Tag(name = "tasks")
public interface TaskRemoveApi {

    @DeleteMapping("/{task_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTaskById(@PathVariable(name = "task_id") final UUID taskId);
}