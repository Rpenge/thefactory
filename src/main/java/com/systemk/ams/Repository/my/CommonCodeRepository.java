package com.systemk.ams.Repository.my;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemk.ams.Entity.Main.CommonCode;

public interface CommonCodeRepository extends JpaRepository<CommonCode, Integer>{

	public List<CommonCode> findByParentCode(String code);
	
	public CommonCode findByCode(String code);
	
	public List<CommonCode> findAll();

}
