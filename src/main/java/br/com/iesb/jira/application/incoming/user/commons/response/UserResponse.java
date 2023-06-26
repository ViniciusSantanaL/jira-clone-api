package br.com.iesb.jira.application.incoming.user.commons.response;

import br.com.iesb.jira.domain.user.model.UserStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserResponse(UUID userId,
                           String userUsername,
                           String userEmail,
                           UserStatus userStatus) {
}
