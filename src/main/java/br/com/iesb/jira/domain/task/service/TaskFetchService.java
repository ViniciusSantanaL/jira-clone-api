package br.com.iesb.jira.domain.task.service;

import br.com.iesb.jira.domain.task.model.Task;
import br.com.iesb.jira.domain.task.repository.TaskRepository;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskFetchService {

    private final TaskRepository taskRepository;

    public TaskFetchService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Page<Task> fetchAllTask(final Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public Task fetchTaskById(final UUID taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() ->  new EntityNotFoundException("task", "task_id"));
    }
}
