package br.com.iesb.jira.application.incoming.task.commons.request;

import br.com.iesb.jira.domain.task.model.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record TaskRequest(
        @NotEmpty String taskTitle,
        String taskDescription,
        TaskStatus taskStatus,
        LocalDate taskStartDate,
        LocalDate taskEndDate,
        @NotNull UUID sprintId,
        UUID userId) {
}