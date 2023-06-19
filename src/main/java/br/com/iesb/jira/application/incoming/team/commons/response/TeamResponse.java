package br.com.iesb.jira.application.incoming.team.commons.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TeamResponse(UUID teamId,
                           String teamName,
                           Set<TeamUserResponse> teamUsers,
                           Set<TeamProjectResponse> teamProjects) {
}
