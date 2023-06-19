package br.com.iesb.jira.application.incoming.subtask.controller.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Tag(name = "subtasks")
public interface SubtaskRemoveApi {

    @DeleteMapping("/{subtask_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteSubtaskById(@PathVariable(name = "subtask_id") final UUID subtaskId);
}