package com.systemk.thefactor2.Security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.systemk.thefactor2.Mapper.TfUserMapper;
import com.systemk.thefactor2.VO.TfUserVO;

@Service("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private TfUserMapper tfUserMapper;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		HashMap item = new HashMap();
		item.put("userId", userId);

		TfUserVO user = tfUserMapper.login(item);
		
		 // 유저가 null 이면 3000	
		if(user == null){
			throw new BadCredentialsException("3000");
		}
		//유저 상태가 N이면 3001
		if(user.getUserStat().equals("N")){
			throw new BadCredentialsException("3001");
		}
		//계정이 갖고 있는 권한 목록
//		List<GrantedAuthority> authorities = buildUserAuthority(user);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
		return buildUserForAuthentication(user, authorities);
	}
	//인증에 대한 지원
	private User buildUserForAuthentication(TfUserVO user, List<GrantedAuthority> authorities) {
		return new LoginUser(user.getUserId(), user.getUserPwd(), true, true, true, true, authorities, user);
	}


//	private List<GrantedAuthority> buildUserAuthority(TfUserVO user) {
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
//		return authorities;
//	}

}
