package br.com.iesb.jira.domain.task.vo.builder;

import br.com.iesb.jira.domain.comment.vo.CommentUserVO;
import br.com.iesb.jira.domain.comment.vo.CommentVO;
import br.com.iesb.jira.domain.sprint.model.Sprint;
import br.com.iesb.jira.domain.subtask.vo.SubtaskVO;
import br.com.iesb.jira.domain.task.model.Task;
import br.com.iesb.jira.domain.task.model.TaskStatus;
import br.com.iesb.jira.domain.task.vo.TaskVO;
import br.com.iesb.jira.domain.user.model.User;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class TaskBuilder {
    private TaskBuilder() {
        throw new UnsupportedOperationException("cannot be call constructor");
    }

    public static Task create(TaskVO taskVO, Integer taskCode, Sprint sprint, User user) {
        Task task = new Task();
        task.setTaskCode(taskCode);
        task.setTaskDescription(taskVO.taskDescription());
        task.setTaskTitle(taskVO.taskTitle());
        task.setTaskStartDate(taskVO.taskStartDate());
        task.setTaskEndDate(taskVO.taskEndDate());
        task.setTaskStatus(taskVO.taskStatus() != null ? taskVO.taskStatus() : TaskStatus.TO_DO);
        task.setSprint(sprint);
        task.setTaskOwner(user);
        task.setComments(Collections.emptySet());
        task.setSubtasks(Collections.emptySet());
        return task;
    }

    public static TaskVO createTaskVO(Task task) {
        Set<SubtaskVO> subtasksVO = task.getSubtasks().stream()
                .map(s -> new SubtaskVO(s.getId(), s.getSubtaskTitle(), task.getId(), s.getSubtaskStatus()))
                .collect(Collectors.toSet());

        Set<CommentVO> commentsVO = task.getComments().stream()
                .map(c -> new CommentVO(c.getId(), c.getCommentDescription(),
                        c.getTask().getId(),
                        new CommentUserVO(c.getUser().getId(), c.getUser().getUserUsername())))
                .collect(Collectors.toSet());

        return new TaskVO(
                task.getId(),
                task.getTaskCode(),
                task.getTaskTitle(),
                task.getTaskDescription(),
                task.getTaskStartDate(),
                task.getTaskEndDate(),
                task.getTaskStatus(),
                task.getTaskOwner() != null ? task.getTaskOwner().getId() : null,
                task.getSprint().getId(),
                subtasksVO,
                commentsVO);
    }
}
