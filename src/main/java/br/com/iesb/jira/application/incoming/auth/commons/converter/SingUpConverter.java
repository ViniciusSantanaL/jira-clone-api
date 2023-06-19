package br.com.iesb.jira.application.incoming.auth.commons.converter;

import br.com.iesb.jira.application.incoming.auth.commons.request.SignUpRequest;
import br.com.iesb.jira.application.incoming.auth.commons.response.SignUpResponse;
import br.com.iesb.jira.domain.user.model.UserStatus;
import br.com.iesb.jira.domain.user.vo.UserVO;

public class SingUpConverter {

    private SingUpConverter() { throw new UnsupportedOperationException("constructor can not be call"); }

    public static UserVO of(SignUpRequest request) {
        return new UserVO(null,request.getUserUsername(), request.getUserEmail(),
                request.getUserPassword(), UserStatus.ACTIVE);
    }

    public static SignUpResponse toResponse(UserVO userVO) {
        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setUserId(userVO.userId());
        signUpResponse.setUserUsername(userVO.username());
        signUpResponse.setUserEmail(userVO.userEmail());
        signUpResponse.setUserStatus(userVO.userStatus());
        return signUpResponse;
    }
}
