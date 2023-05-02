package br.com.iesb.jira.domain.user.vo.builder;

import br.com.iesb.jira.domain.user.model.User;
import br.com.iesb.jira.domain.user.model.UserStatus;
import br.com.iesb.jira.domain.user.vo.UserVO;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserBuilder {

    private UserBuilder() { throw new UnsupportedOperationException("constructor cannot be call!"); }

    public static User create(UserVO userVO, PasswordEncoder encoder) {
        User user = new User();
        user.setUserEmail(userVO.userEmail());
        user.setUserPassword(encoder.encode(userVO.password()));
        user.setUserUsername(userVO.username());
        user.setUserStatus(UserStatus.ACTIVE);
        return user;
    }

    public static UserVO of(User user) {
        return new UserVO(user.getId(), user.getUserUsername(),
                user.getUserEmail(), user.getUserPassword(), user.getUserStatus());
    }
}
