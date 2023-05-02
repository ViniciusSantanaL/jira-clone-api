package br.com.iesb.nicetask.domain.user.vo;

import br.com.iesb.nicetask.domain.user.model.UserStatus;

public record UserVO(String username, String email, String password, UserStatus userStatus) {
}
