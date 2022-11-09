package com.systemk.Security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import com.systemk.VO.TfUserVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUser extends User {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String role;

    private String storeCd;

    private String name;

    public LoginUser(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities, TfUserVO userVO) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userVO.getUserId();
        this.role = userVO.getGrade();
        this.storeCd = userVO.getStoreCd();
        this.name = userVO.getUserNm();
    }


}
