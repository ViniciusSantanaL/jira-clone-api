package br.com.iesb.jira.application.incoming.task.commons.converter;

import br.com.iesb.jira.application.incoming.comment.commons.response.CommentResponse;
import br.com.iesb.jira.application.incoming.comment.commons.response.CommentUserResponse;
import br.com.iesb.jira.application.incoming.subtask.commons.response.SubtaskResponse;
import br.com.iesb.jira.application.incoming.task.commons.request.TaskRequest;
import br.com.iesb.jira.application.incoming.task.commons.response.TaskResponse;
import br.com.iesb.jira.domain.task.model.Task;
import br.com.iesb.jira.domain.task.vo.TaskVO;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class TaskConverter {

    private TaskConverter() {
        throw new UnsupportedOperationException("can not be call constructor");
    }

    public static TaskVO of(final TaskRequest request) {
        return new TaskVO(
                null,
                request.taskTitle(),
                request.taskDescription(),
                request.taskStartDate(),
                request.taskEndDate(),
                request.taskStatus(),
                request.userId(),
                request.sprintId());
    }

    public static TaskVO of(final UUID taskId, final TaskRequest request) {
        return new TaskVO(
                taskId,
                request.taskTitle(),
                request.taskDescription(),
                request.taskStartDate(),
                request.taskEndDate(),
                request.taskStatus(),
                request.sprintId(),
                request.userId());
    }

    public static TaskResponse toResponse(TaskVO taskVO) {
        Set<SubtaskResponse> taskTasksResponse = taskVO.taskSubtasks().stream()
                .map(t -> new SubtaskResponse(t.subtaskId(), t.subtaskTitle(), t.taskId(), t.subtaskStatus()))
                .collect(Collectors.toSet());

        Set<CommentResponse> taskCommentResponse = taskVO.taskComments().stream()
                .map(c -> new CommentResponse(
                        c.commentId(),
                        c.commentDescription(),
                        new CommentUserResponse(c.commentUser().userId(), c.commentUser().userUsername()),
                        c.taskId()))
                .collect(Collectors.toSet());
        
        return new TaskResponse(
                taskVO.taskId(),
                taskVO.taskCode(),
                taskVO.taskTitle(),
                taskVO.taskDescription(),
                taskVO.taskStartDate(),
                taskVO.taskEndDate(),
                taskVO.taskStatus(),
                taskVO.userId(),
                taskVO.sprintId(),
                taskTasksResponse,
                taskCommentResponse);
    }

    public static TaskResponse toResponse(Task task) {
        Set<SubtaskResponse> taskTasksResponse = task.getSubtasks().stream()
                .map(t -> new SubtaskResponse(t.getId(), t.getSubtaskTitle(), task.getId(), t.getSubtaskStatus()))
                .collect(Collectors.toSet());

        Set<CommentResponse> taskCommentResponse = task.getComments().stream()
                .map(c -> new CommentResponse(
                        c.getId(),
                        c.getCommentDescription(),
                        new CommentUserResponse(c.getUser().getId(), c.getUser().getUserUsername()),
                        task.getId()))
                .collect(Collectors.toSet());

        return new TaskResponse(
                task.getId(),
                task.getTaskCode(),
                task.getTaskTitle(),
                task.getTaskDescription(),
                task.getTaskStartDate(),
                task.getTaskEndDate(),
                task.getTaskStatus(),
                task.getTaskOwner() != null ? task.getTaskOwner().getId() : null,
                task.getSprint().getId(),
                taskTasksResponse,
                taskCommentResponse);
    }
}
