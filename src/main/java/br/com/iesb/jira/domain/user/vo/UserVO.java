package br.com.iesb.jira.domain.user.vo;

import br.com.iesb.jira.domain.user.model.UserStatus;

import java.util.UUID;

public record UserVO(UUID userId, String username, String userEmail, String password, UserStatus userStatus) {
}
