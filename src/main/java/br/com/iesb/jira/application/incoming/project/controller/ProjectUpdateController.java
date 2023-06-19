package br.com.iesb.jira.application.incoming.project.controller;

import br.com.iesb.jira.application.incoming.project.commons.converter.ProjectConverter;
import br.com.iesb.jira.application.incoming.project.commons.request.ProjectRequest;
import br.com.iesb.jira.application.incoming.project.commons.response.ProjectResponse;
import br.com.iesb.jira.application.incoming.project.controller.api.ProjectUpdateApi;
import br.com.iesb.jira.domain.project.service.ProjectUpdateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/api/v1/projects")
@RestController
public class ProjectUpdateController implements ProjectUpdateApi {

    private final ProjectUpdateService projectUpdateService;

    public ProjectUpdateController(ProjectUpdateService projectUpdateService) {
        this.projectUpdateService = projectUpdateService;
    }

    @Override
    public ProjectResponse updateProjectById(final UUID projectId, final ProjectRequest projectRequest) {
        return ProjectConverter.toResponse(projectUpdateService.updateProjectById(ProjectConverter.of(projectId, projectRequest)));
    }
}
