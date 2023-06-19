package br.com.iesb.jira.application.incoming.project.controller.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Tag(name = "projects")
public interface ProjectRemoveApi {

    @DeleteMapping("/{project_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteProjectById(@PathVariable(name = "project_id") final UUID projectId);
}