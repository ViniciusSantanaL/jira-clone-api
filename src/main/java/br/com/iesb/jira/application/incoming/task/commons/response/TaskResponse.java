package br.com.iesb.jira.application.incoming.task.commons.response;

import br.com.iesb.jira.application.incoming.comment.commons.response.CommentResponse;
import br.com.iesb.jira.application.incoming.subtask.commons.response.SubtaskResponse;
import br.com.iesb.jira.domain.task.model.TaskStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TaskResponse(UUID taskId,
                           Integer taskCode,
                           String taskTitle,

                           String taskDescription,
                           LocalDate taskStartDate,
                           LocalDate taskEndDate,
                           TaskStatus taskStatus,
                           UUID userId,
                           UUID sprintId,
                           Set<SubtaskResponse> taskSubtasks,
                           Set<CommentResponse> taskComments) {
}
