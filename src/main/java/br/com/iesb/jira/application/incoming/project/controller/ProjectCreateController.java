package br.com.iesb.jira.application.incoming.project.controller;

import br.com.iesb.jira.application.incoming.project.commons.converter.ProjectConverter;
import br.com.iesb.jira.application.incoming.project.commons.request.ProjectRequest;
import br.com.iesb.jira.application.incoming.project.commons.response.ProjectResponse;
import br.com.iesb.jira.application.incoming.project.controller.api.ProjectCreateApi;
import br.com.iesb.jira.domain.project.service.ProjectCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/projects")
@RestController
public class ProjectCreateController implements ProjectCreateApi {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProjectCreateController.class);
    private final ProjectCreateService projectCreateService;

    public ProjectCreateController(ProjectCreateService projectCreateService) {
        this.projectCreateService = projectCreateService;
    }

    @Override
    public ProjectResponse createProject(final ProjectRequest request) {
        LOGGER.info("start create project, request: {}", request);
        return ProjectConverter.toResponse(projectCreateService.createProject(ProjectConverter.of(request)));
    }
}
