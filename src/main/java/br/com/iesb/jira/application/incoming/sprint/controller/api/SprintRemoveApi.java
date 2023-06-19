package br.com.iesb.jira.application.incoming.sprint.controller.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Tag(name = "sprints")
public interface SprintRemoveApi {

    @DeleteMapping("/{sprint_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteSprintById(@PathVariable(name = "sprint_id") final UUID sprintId);
}