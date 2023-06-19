package br.com.iesb.jira.application.incoming.subtask.commons.response;

import br.com.iesb.jira.domain.subtask.model.SubtaskStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record SubtaskResponse(@NotNull UUID subtaskId,
                              @NotEmpty String subtaskTitle,
                              @NotNull UUID taskId,
                              SubtaskStatus subtaskStatus) {
}
