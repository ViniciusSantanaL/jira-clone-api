package br.com.iesb.jira.domain.task.service;

import br.com.iesb.jira.domain.sprint.model.Sprint;
import br.com.iesb.jira.domain.sprint.repository.SprintRepository;
import br.com.iesb.jira.domain.task.model.Task;
import br.com.iesb.jira.domain.task.repository.TaskRepository;
import br.com.iesb.jira.domain.task.vo.TaskVO;
import br.com.iesb.jira.domain.task.vo.builder.TaskBuilder;
import br.com.iesb.jira.domain.user.model.User;
import br.com.iesb.jira.domain.user.repository.UserRepository;
import br.com.iesb.jira.infrastructure.exception.EntityDuplicatedException;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class TaskCreateService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskCreateService.class);

    private final TaskRepository taskRepository;

    private final SprintRepository sprintRepository;
    
    private final UserRepository userRepository;

    public TaskCreateService(TaskRepository taskRepository, SprintRepository sprintRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.sprintRepository = sprintRepository;
        this.userRepository = userRepository;
    }

    public TaskVO createTask(TaskVO taskVO) {
        Sprint sprint = sprintRepository.findById(taskVO.sprintId())
                .orElseThrow(() -> new EntityNotFoundException("sprint", "sprint_id"));

        User taskOwner = null;

        if(taskVO.userId() != null) {
            taskOwner = userRepository.findById(taskVO.userId())
                    .orElseThrow(() -> new EntityNotFoundException("user", "user_id"));
        }

        Integer taskCode = taskRepository.fetchSizeTaskByProjectId(sprint.getId()) + 1;

        Task task = saveTask(TaskBuilder.create(taskVO,taskCode, sprint, taskOwner));
        LOGGER.info("createTask, Task: {}", task);

        return TaskBuilder.createTaskVO(task);
    }

    private Task saveTask(final Task task) {
        try {
            return taskRepository.save(task);
        } catch (DataIntegrityViolationException ex) {
            LOGGER.error("Error to saveTask: {}", ex.getMessage());
            throw new EntityDuplicatedException("task","taskName");
        }
    }
}
