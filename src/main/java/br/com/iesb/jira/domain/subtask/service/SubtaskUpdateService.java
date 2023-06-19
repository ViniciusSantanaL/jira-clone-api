package br.com.iesb.jira.domain.subtask.service;

import br.com.iesb.jira.domain.project.repository.ProjectRepository;
import br.com.iesb.jira.domain.subtask.model.Subtask;
import br.com.iesb.jira.domain.subtask.repository.SubtaskRepository;
import br.com.iesb.jira.domain.subtask.vo.SubtaskVO;
import br.com.iesb.jira.domain.subtask.vo.builder.SubtaskBuilder;
import br.com.iesb.jira.domain.task.repository.TaskRepository;
import br.com.iesb.jira.infrastructure.exception.EntityDuplicatedException;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class SubtaskUpdateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubtaskUpdateService.class);

    private final SubtaskRepository subtaskRepository;

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public SubtaskUpdateService(SubtaskRepository subtaskRepository, ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.subtaskRepository = subtaskRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public SubtaskVO updateSubtaskById(final SubtaskVO subtaskVO) {
        Subtask subtaskToSave = subtaskRepository.findById(subtaskVO.subtaskId())
                .orElseThrow(() -> new EntityNotFoundException("subtask", "subtask_id"));

        subtaskToSave.setSubtaskStatus(subtaskVO.subtaskStatus());
        subtaskToSave.setSubtaskTitle(subtaskVO.subtaskTitle());

        Subtask subtaskSaved = saveSubtask(subtaskToSave);

        return SubtaskBuilder.createSubtaskVO(subtaskSaved);
    }

    private Subtask saveSubtask(Subtask subtask) {
        try {
            return subtaskRepository.save(subtask);
        } catch(DataIntegrityViolationException ex) {
            LOGGER.error("Error to updateSubtask, {}",ex.getMessage());
            throw new EntityDuplicatedException("subtask", "subtask_id");
        }
    }
}
