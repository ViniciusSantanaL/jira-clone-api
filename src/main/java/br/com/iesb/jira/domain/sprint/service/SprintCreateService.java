package br.com.iesb.jira.domain.sprint.service;

import br.com.iesb.jira.domain.project.model.Project;
import br.com.iesb.jira.domain.project.repository.ProjectRepository;
import br.com.iesb.jira.domain.sprint.model.Sprint;
import br.com.iesb.jira.domain.sprint.repository.SprintRepository;
import br.com.iesb.jira.domain.sprint.vo.SprintVO;
import br.com.iesb.jira.domain.sprint.vo.builder.SprintBuilder;
import br.com.iesb.jira.infrastructure.exception.EntityDuplicatedException;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class SprintCreateService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SprintCreateService.class);
    private final SprintRepository sprintRepository;

    private final ProjectRepository projectRepository;

    public SprintCreateService(SprintRepository sprintRepository, ProjectRepository projectRepository) {
        this.sprintRepository = sprintRepository;
        this.projectRepository = projectRepository;
    }

    public SprintVO createSprint(SprintVO sprintVO) {
        Project project = projectRepository.findById(sprintVO.sprintProjectId())
                .orElseThrow(() -> new EntityNotFoundException("project", "project_id"));

        Integer sprintCode = sprintRepository.fetchSizeSprintByProjectId(project.getId()) + 1;

        Sprint sprint = saveSprint(SprintBuilder.create(sprintVO,sprintCode, project));
        LOGGER.info("createSprint, Sprint: {}", sprint);

        return SprintBuilder.createSprintVO(sprint);
    }

    private Sprint saveSprint(final Sprint sprint) {
        try {
            return sprintRepository.save(sprint);
        } catch (DataIntegrityViolationException ex) {
            LOGGER.error("Error to saveSprint: {}", ex.getMessage());
            throw new EntityDuplicatedException("sprint","sprintName");
        }
    }
}
