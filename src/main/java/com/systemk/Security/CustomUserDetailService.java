package com.systemk.Security;

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
import com.systemk.Mapper.TfUserMapper;
import com.systemk.VO.TfUserVO;
@Service("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private TfUserMapper tfUserMapper;

    @Transactional
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        HashMap item = new HashMap();
        item.put("userId", userId);

        TfUserVO user = tfUserMapper.login(item);

        if(user == null){
            throw new BadCredentialsException("3000");
        }
        if(user.getUserStat().equals("N")){
            throw new BadCredentialsException("3001");
        }

//      List<GrantedAuthority> authorities = buildUserAuthority(user);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(TfUserVO user, List<GrantedAuthority> authorities) {
        return new LoginUser(user.getUserId(), user.getUserPwd(), true, true, true, true, authorities, user);
    }


//  private List<GrantedAuthority> buildUserAuthority(TfUserVO user) {
//      List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
//      return authorities;
//  }

}

