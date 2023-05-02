package br.com.iesb.nicetask.domain.user.vo.builder;

import br.com.iesb.nicetask.domain.user.model.User;
import br.com.iesb.nicetask.domain.user.model.UserStatus;
import br.com.iesb.nicetask.domain.user.vo.UserVO;

public class UserBuilder {

    private UserBuilder() { throw new UnsupportedOperationException("constructor cannot be call!"); }

    public static User create(UserVO userVO) {
        User user = new User();
        user.setUserEmail(userVO.email());
        user.setUserPassword(userVO.password());
        user.setUserUsername(userVO.username());
        user.setUserStatus(UserStatus.ACTIVE);
        return user;
    }

    public static UserVO of(User user) {
        return new UserVO(user.getUserUsername(), user.getUserEmail(), user.getUserPassword());
    }
}
