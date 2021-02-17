package com.systemk.ams.Security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.systemk.ams.VO.UserInfoVO;
import com.systemk.ams.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		HashMap item = new HashMap();
		item.put("userId", userId);

		UserInfoVO user = userMapper.login(item);

		if(user == null){
			throw new BadCredentialsException("3000");
		}

		List<GrantedAuthority> authorities = buildUserAuthority(user);
		return buildUserForAuthentication(user, authorities);
	}

	private User buildUserForAuthentication(UserInfoVO user,
		List<GrantedAuthority> authorities) {
		return new LoginUser(user.getId(), user.getPassword(), true, true, true, true, authorities, user);
	}


	private List<GrantedAuthority> buildUserAuthority(UserInfoVO user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
		return authorities;
	}

}
