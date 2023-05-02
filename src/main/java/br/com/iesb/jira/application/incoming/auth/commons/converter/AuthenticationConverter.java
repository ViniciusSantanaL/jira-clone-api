package br.com.iesb.jira.application.incoming.auth.commons.converter;

import br.com.iesb.jira.application.incoming.auth.commons.response.AuthenticateResponse;

public class AuthenticationConverter {
    private AuthenticationConverter() { throw new UnsupportedOperationException("constructor can not be call"); }

    public static AuthenticateResponse toResponse(String jwt) {
        return new AuthenticateResponse(jwt);
    }
}
