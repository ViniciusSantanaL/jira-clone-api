package br.com.iesb.jira.application.incoming.project.commons.response;

import br.com.iesb.jira.application.incoming.team.commons.response.TeamUserResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ProjectResponse(UUID projectId,
                              String projectName,
                              LocalDate projectCreateDate,
                              Set<TeamUserResponse> projectUsers,
                              Set<ProjectSprintResponse> projectSprints) {
}
