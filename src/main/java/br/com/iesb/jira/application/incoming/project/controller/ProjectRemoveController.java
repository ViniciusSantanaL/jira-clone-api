package br.com.iesb.jira.application.incoming.project.controller;

import br.com.iesb.jira.application.incoming.project.controller.api.ProjectRemoveApi;
import br.com.iesb.jira.domain.project.service.ProjectRemoveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectRemoveController implements ProjectRemoveApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectRemoveController.class);
    private final ProjectRemoveService projectRemoveService;

    public ProjectRemoveController(ProjectRemoveService projectRemoveService) {
        this.projectRemoveService = projectRemoveService;
    }

    @Override
    public void deleteProjectById(final UUID projectId) {
        LOGGER.info("deleteProjectById, projectId: {}", projectId);
        projectRemoveService.deleteProjectById(projectId);
    }
}
