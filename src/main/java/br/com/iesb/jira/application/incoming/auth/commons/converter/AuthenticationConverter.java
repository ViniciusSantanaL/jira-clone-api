package br.com.iesb.jira.application.incoming.auth.commons.converter;

import br.com.iesb.jira.application.incoming.auth.commons.response.AuthenticateResponse;
import br.com.iesb.jira.domain.user.model.User;

public class AuthenticationConverter {
    private AuthenticationConverter() { throw new UnsupportedOperationException("constructor can not be call"); }

    public static AuthenticateResponse toResponse(User user, String jwt) {
        return new AuthenticateResponse(
                user.getId(),
                user.getUserUsername(),
                user.getUserEmail(),
                user.getUserStatus(),
                jwt);
    }
}
