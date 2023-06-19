package br.com.iesb.jira.domain.task.vo;

import br.com.iesb.jira.domain.comment.vo.CommentVO;
import br.com.iesb.jira.domain.subtask.vo.SubtaskVO;
import br.com.iesb.jira.domain.task.model.TaskStatus;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record TaskVO(UUID taskId,
                     Integer taskCode,
                     String taskTitle,

                     String taskDescription,
                     LocalDate taskStartDate,
                     LocalDate taskEndDate,
                     TaskStatus taskStatus,
                     UUID userId,
                     UUID sprintId,
                     Set<SubtaskVO> taskSubtasks,
                     Set<CommentVO> taskComments) {

    public TaskVO(UUID taskId,String taskTitle,
                  String taskDescription,
                  LocalDate taskStartDate,
                  LocalDate taskEndDate,
                  TaskStatus taskStatus,
                  UUID userId,
                  UUID sprintId) {
        this(taskId, null, taskTitle, taskDescription, taskStartDate,
                taskEndDate, taskStatus, userId, sprintId, null, null);
    }

}
