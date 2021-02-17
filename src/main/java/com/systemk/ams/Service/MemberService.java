package com.systemk.ams.Service;

import java.sql.SQLException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.systemk.ams.Entity.Main.UserInfo;
import com.systemk.ams.Entity.mapping.memberMapping;


public interface MemberService {
	public void memberReg(UserInfo regData) throws Exception;
	
	public Page<memberMapping> memberList(Pageable pageable);

	public void userUpdate(UserInfo user) throws SQLException;

	public memberMapping userInfo(String userId);

}
