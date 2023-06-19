package br.com.iesb.jira.domain.project.service;

import br.com.iesb.jira.domain.project.model.Project;
import br.com.iesb.jira.domain.project.repository.ProjectRepository;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProjectRemoveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectRemoveService.class);

    private final ProjectRepository projectRepository;

    public ProjectRemoveService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void deleteProjectById(final UUID projectId) {
        LOGGER.info("deleteProjectById");
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException("project", "project_id"));
        projectRepository.deleteById(project.getId());
        LOGGER.info("project delete by id: {}",projectId);
    }

}
