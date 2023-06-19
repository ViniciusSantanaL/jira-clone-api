package br.com.iesb.jira.domain.sprint.service;

import br.com.iesb.jira.domain.project.model.Project;
import br.com.iesb.jira.domain.project.repository.ProjectRepository;
import br.com.iesb.jira.domain.sprint.model.Sprint;
import br.com.iesb.jira.domain.sprint.repository.SprintRepository;
import br.com.iesb.jira.domain.sprint.vo.SprintTaskVO;
import br.com.iesb.jira.domain.sprint.vo.SprintVO;
import br.com.iesb.jira.domain.sprint.vo.builder.SprintBuilder;
import br.com.iesb.jira.domain.task.model.Task;
import br.com.iesb.jira.domain.task.repository.TaskRepository;
import br.com.iesb.jira.infrastructure.exception.EntityDuplicatedException;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import br.com.iesb.jira.infrastructure.utils.validation.EntityValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SprintUpdateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SprintUpdateService.class);

    private final SprintRepository sprintRepository;

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public SprintUpdateService(SprintRepository sprintRepository, ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.sprintRepository = sprintRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public SprintVO updateSprintById(final SprintVO sprintVO) {
        Sprint sprintToSave = sprintRepository.findById(sprintVO.id())
                .orElseThrow(() -> new EntityNotFoundException("sprint", "sprint_id"));

        Project project = projectRepository.findById(sprintVO.sprintProjectId())
                .orElseThrow(() -> new EntityNotFoundException("project", "project_id"));
        Set<Task> sprintTasksToSave = Collections.emptySet();

        if(!sprintVO.sprintTasksVO().isEmpty()) {
            List<UUID> sprintTasksVOId = sprintVO.sprintTasksVO().stream().map(SprintTaskVO::taskId).toList();
            List<Task> sprintTasks = taskRepository.findAllById(sprintTasksVOId);

            EntityValidation.validateIfEntityExist(sprintTasksVOId, sprintTasks, "task");

            sprintTasksToSave = new HashSet<>(sprintTasks);
        }

        sprintToSave.setTasks(sprintTasksToSave);
        sprintToSave.setProject(project);
        sprintToSave.setSprintStartDate(sprintVO.sprintStartDate());
        sprintToSave.setSprintEndDate(sprintVO.sprintEndDate());

        Sprint sprintSaved = saveSprint(sprintToSave);

        return SprintBuilder.createSprintVO(sprintSaved);
    }

    private Sprint saveSprint(Sprint sprint) {
        try {
            return sprintRepository.save(sprint);
        } catch(DataIntegrityViolationException ex) {
            LOGGER.error("Error to updateSprint, {}",ex.getMessage());
            throw new EntityDuplicatedException("sprint", "sprint_id");
        }
    }
}
