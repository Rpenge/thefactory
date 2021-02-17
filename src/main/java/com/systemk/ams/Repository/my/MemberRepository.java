package com.systemk.ams.Repository.my;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.systemk.ams.Entity.mapping.memberMapping;
import com.systemk.ams.Entity.Main.UserInfo;


public interface MemberRepository extends JpaRepository<UserInfo, Long>{

	
	public UserInfo findByUserId(String userId);
	
	public Page<memberMapping> findAllBy(Pageable pageable);
	
	public memberMapping findOneByUserId(String userId);
	
}
