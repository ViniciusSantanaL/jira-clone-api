package br.com.iesb.jira.application.incoming.project.controller;

import br.com.iesb.jira.application.incoming.project.commons.converter.ProjectConverter;
import br.com.iesb.jira.application.incoming.project.commons.response.ProjectResponse;
import br.com.iesb.jira.application.incoming.project.controller.api.ProjectFetchApi;
import br.com.iesb.jira.domain.project.service.ProjectFetchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectFetchController implements ProjectFetchApi {

    private final ProjectFetchService projectFetchService;

    public ProjectFetchController(ProjectFetchService projectFetchService) {
        this.projectFetchService = projectFetchService;
    }

    @Override
    public Page<ProjectResponse> fetchAllProject(final Pageable pageable) {
        return projectFetchService.fetchAllProject(pageable)
                .map(ProjectConverter::toResponse);
    }

    @Override
    public ProjectResponse fetchProjectById(final UUID projectId) {
        return ProjectConverter.toResponse(projectFetchService.fetchProjectById(projectId));
    }
}
