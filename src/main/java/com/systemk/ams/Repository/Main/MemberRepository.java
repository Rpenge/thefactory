package com.systemk.ams.Repository.Main;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.systemk.ams.Entity.Main.UserInfo;
import com.systemk.ams.Entity.mapping.memberMapping;


public interface MemberRepository extends JpaRepository<UserInfo, Long>{

	
	public UserInfo findByUserId(String userId);
	
	public Page<memberMapping> findAllBy(Pageable pageable);
	
	public memberMapping findOneByUserId(String userId);
	
}
