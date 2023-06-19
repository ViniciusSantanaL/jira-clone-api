package br.com.iesb.jira.application.incoming.project.controller.api;

import br.com.iesb.jira.application.incoming.project.commons.request.ProjectRequest;
import br.com.iesb.jira.application.incoming.project.commons.response.ProjectResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Tag(name = "projects")
public interface ProjectUpdateApi {

    @PutMapping("/{project_id}")
    @ResponseStatus(HttpStatus.OK)
    ProjectResponse updateProjectById(@PathVariable(name = "project_id") final UUID projectId,
                                   @RequestBody @Valid final ProjectRequest projectRequest);

}
