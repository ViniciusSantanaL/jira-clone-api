package br.com.iesb.nicetask.application.incoming.auth.commons.response;

import br.com.iesb.nicetask.domain.user.model.UserStatus;

public record SingUpResponse(String userId, String userUsername, String userEmail, UserStatus userStatus) {
}
