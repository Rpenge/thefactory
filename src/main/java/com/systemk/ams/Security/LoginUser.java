package com.systemk.ams.Security;

import java.util.Collection;

import com.systemk.ams.VO.UserInfoVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.systemk.ams.Entity.Main.UserInfo;


public class LoginUser extends User{

	private static final long serialVersionUID = 1L;

    private String userId;

    private String role;

    private String name;

    public LoginUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, UserInfoVO userInfo) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userId = userInfo.getId();
		this.role = userInfo.getRoleCode();
		this.name = userInfo.getName();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
