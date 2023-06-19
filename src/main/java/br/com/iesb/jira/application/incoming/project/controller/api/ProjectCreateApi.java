package br.com.iesb.jira.application.incoming.project.controller.api;

import br.com.iesb.jira.application.incoming.project.commons.request.ProjectRequest;
import br.com.iesb.jira.application.incoming.project.commons.response.ProjectResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "projects")
public interface ProjectCreateApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProjectResponse createProject(final @RequestBody @Valid ProjectRequest request);

}
