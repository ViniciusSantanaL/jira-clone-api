package br.com.iesb.jira.application.incoming.sprint.commons.request;

import br.com.iesb.jira.application.incoming.team.commons.request.TeamUserRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record SprintRequest(
        @NotBlank Integer sprintCode,
        @NotNull LocalDate sprintStartDate,
        @NotNull LocalDate sprintEndDate,
        @NotNull UUID projectId,
        @NotNull @Valid Set<SprintTaskRequest> sprintTasks) {
}