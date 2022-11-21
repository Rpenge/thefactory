package com.systemk.thefactor2.Security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import com.systemk.thefactor2.VO.TfUserVO;
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

    //security 에서 제공해주는 Username, PW를 제외한 더 추가사항을 Auth에 넣어줄라면 아래를 실행함
    //enabled :사용가능 여부, accountNOnExpired : 계정만료 여부, credentialsNonExpired: 신용정보 만료 여부
    //accountNonLocked : 계정 잠김여부, authorities: 계정이 갖고있는 권한 리스트, TfUserVO: 의 관련된 정보들 
    public LoginUser(String username, 
                          String password, 
                          boolean enabled, 
                          boolean accountNonExpired,
                          boolean credentialsNonExpired, 
                          boolean accountNonLocked,
                          Collection<? extends GrantedAuthority> authorities, 
                          TfUserVO userVO
                          ) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		//UserID: 유저아이디, Grade : 등급,  storeCd: 매장코드, UserNm: 유저이름
		this.userId = userVO.getUserId();
		this.role = userVO.getGrade();
		this.storeCd = userVO.getStoreCd();
		this.name = userVO.getUserNm();
	}


}
