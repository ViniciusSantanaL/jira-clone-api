package br.com.iesb.jira.application.incoming.user.commons.converter;

import br.com.iesb.jira.application.incoming.user.commons.response.UserResponse;
import br.com.iesb.jira.domain.user.model.User;

public class UserConverter {

    private UserConverter() {
        throw new UnsupportedOperationException("constructor cannot be call");
    }

    public static UserResponse of(final User user) {
        return new UserResponse(user.getId(),
                user.getUserUsername(),
                user.getUserEmail(),
                user.getUserStatus());
    }
}
