package br.com.iesb.jira.domain.subtask.service;

import br.com.iesb.jira.domain.subtask.model.Subtask;
import br.com.iesb.jira.domain.subtask.repository.SubtaskRepository;
import br.com.iesb.jira.domain.subtask.vo.SubtaskVO;
import br.com.iesb.jira.domain.subtask.vo.builder.SubtaskBuilder;
import br.com.iesb.jira.domain.task.model.Task;
import br.com.iesb.jira.domain.task.repository.TaskRepository;
import br.com.iesb.jira.infrastructure.exception.EntityDuplicatedException;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class SubtaskCreateService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SubtaskCreateService.class);
    private final SubtaskRepository subtaskRepository;

    private final TaskRepository taskRepository;

    public SubtaskCreateService(SubtaskRepository subtaskRepository, TaskRepository taskRepository) {
        this.subtaskRepository = subtaskRepository;
        this.taskRepository = taskRepository;
    }

    public SubtaskVO createSubtask(SubtaskVO subtaskVO) {
        Task task = taskRepository.findById(subtaskVO.taskId())
                .orElseThrow(() -> new EntityNotFoundException("task", "task_id"));

        Subtask subtask = saveSubtask(SubtaskBuilder.create(subtaskVO,task));
        LOGGER.info("createSubtask, Subtask: {}", subtask);

        return SubtaskBuilder.createSubtaskVO(subtask);
    }

    private Subtask saveSubtask(final Subtask subtask) {
        try {
            return subtaskRepository.save(subtask);
        } catch (DataIntegrityViolationException ex) {
            LOGGER.error("Error to saveSubtask: {}", ex.getMessage());
            throw new EntityDuplicatedException("subtask","subtaskName");
        }
    }
}
