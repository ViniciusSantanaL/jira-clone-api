package br.com.iesb.jira.domain.task.service;

import br.com.iesb.jira.domain.task.model.Task;
import br.com.iesb.jira.domain.task.repository.TaskRepository;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskRemoveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskRemoveService.class);

    private final TaskRepository taskRepository;

    public TaskRemoveService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void deleteTaskById(final UUID taskId) {
        LOGGER.info("deleteTaskById");
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("task", "task_id"));
        taskRepository.deleteById(task.getId());
        LOGGER.info("task delete by id: {}",taskId);
    }

}
