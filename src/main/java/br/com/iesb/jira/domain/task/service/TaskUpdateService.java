package br.com.iesb.jira.domain.task.service;

import br.com.iesb.jira.domain.sprint.model.Sprint;
import br.com.iesb.jira.domain.sprint.repository.SprintRepository;
import br.com.iesb.jira.domain.task.model.Task;
import br.com.iesb.jira.domain.task.model.TaskStatus;
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
public class TaskUpdateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskUpdateService.class);

    private final TaskRepository taskRepository;

    private final SprintRepository sprintRepository;

    private final UserRepository userRepository;


    public TaskUpdateService(TaskRepository taskRepository, SprintRepository sprintRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.sprintRepository = sprintRepository;
        this.userRepository = userRepository;
    }

    public TaskVO updateTaskById(final TaskVO taskVO) {
        Task taskToSave = taskRepository.findById(taskVO.taskId())
                .orElseThrow(() -> new EntityNotFoundException("task", "task_id"));

        Sprint sprint = sprintRepository.findById(taskVO.sprintId())
                .orElseThrow(() -> new EntityNotFoundException("sprint", "sprint_id"));

        User taskOwner = null;

        if(taskVO.userId() != null) {
            taskOwner = userRepository.findById(taskVO.userId())
                    .orElseThrow(() -> new EntityNotFoundException("user", "user_id"));
        }


        taskToSave.setTaskDescription(taskVO.taskDescription());
        taskToSave.setTaskTitle(taskVO.taskTitle());
        taskToSave.setTaskStartDate(taskVO.taskStartDate());
        taskToSave.setTaskEndDate(taskVO.taskStartDate());
        taskToSave.setTaskStatus(taskVO.taskStatus() != null ? taskVO.taskStatus() : TaskStatus.TO_DO);
        taskToSave.setSprint(sprint);
        taskToSave.setTaskOwner(taskOwner);

        Task taskSaved = saveTask(taskToSave);

        return TaskBuilder.createTaskVO(taskSaved);
    }

    private Task saveTask(Task task) {
        try {
            return taskRepository.save(task);
        } catch(DataIntegrityViolationException ex) {
            LOGGER.error("Error to updateTask, {}",ex.getMessage());
            throw new EntityDuplicatedException("task", "task_id");
        }
    }
}
