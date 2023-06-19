package br.com.iesb.jira.application.incoming.project.commons.request;

import br.com.iesb.jira.application.incoming.team.commons.request.TeamUserRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record ProjectRequest(
        @NotBlank String projectName,
        @NotNull @Valid Set<TeamUserRequest> projectUsers,
        @NotNull @Valid Set<ProjectSprintRequest> projectSprints) {
}