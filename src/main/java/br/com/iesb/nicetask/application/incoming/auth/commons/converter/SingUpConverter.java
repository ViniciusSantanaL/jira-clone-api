package br.com.iesb.nicetask.application.incoming.auth.commons.converter;

import br.com.iesb.nicetask.application.incoming.auth.commons.request.SingUpRequest;
import br.com.iesb.nicetask.application.incoming.auth.commons.response.SingUpResponse;
import br.com.iesb.nicetask.domain.user.model.UserStatus;
import br.com.iesb.nicetask.domain.user.vo.UserVO;

public class SingUpConverter {

    private SingUpConverter() { throw new UnsupportedOperationException("constructor can not be call"); }

    public static UserVO of(SingUpRequest request) {
        return new UserVO(request.userUsername(), request.userEmail(), request.userPassword(), UserStatus.ACTIVE);
    }

    public static SingUpResponse toResponse(UserVO userVO) {
        return new SingUpResponse(userVO.username(),userVO.email(), userVO.password(), userVO.userStatus());
    }
}
