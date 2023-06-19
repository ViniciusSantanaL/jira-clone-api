package br.com.iesb.jira.domain.project.service;

import br.com.iesb.jira.domain.project.model.Project;
import br.com.iesb.jira.domain.project.repository.ProjectRepository;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProjectFetchService {

    private final ProjectRepository projectRepository;
    
    public ProjectFetchService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Page<Project> fetchAllProject(final Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public Project fetchProjectById(final UUID projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() ->  new EntityNotFoundException("project", "project_id"));
    }
}
