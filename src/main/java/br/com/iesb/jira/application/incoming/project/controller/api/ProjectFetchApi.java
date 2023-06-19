package br.com.iesb.jira.application.incoming.project.controller.api;

import br.com.iesb.jira.application.incoming.project.commons.response.ProjectResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Tag(name = "projects")
public interface ProjectFetchApi {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Page<ProjectResponse> fetchAllProject(final Pageable pageable);

    @GetMapping("/{project_id}")
    @ResponseStatus(HttpStatus.OK)
    ProjectResponse fetchProjectById(@PathVariable(value = "project_id") final UUID teamId);

}