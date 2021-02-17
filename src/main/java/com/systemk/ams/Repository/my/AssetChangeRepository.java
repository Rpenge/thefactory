package com.systemk.ams.Repository.my;


import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.systemk.ams.Entity.Main.AssetChange;

public interface AssetChangeRepository extends JpaRepository<AssetChange, Long>, JpaSpecificationExecutor<AssetChange>{

	Page<AssetChange> findByWkEnv(String env, Pageable pageable);
	
	Page<AssetChange> findByWkEnv(String env, Specification<AssetChange> specification, Pageable pageable);
	
	Integer countByUpdateYnAndWkEnvAndChgDtBetween(String yn, String env, Date fromDate, Date toDate);
	
}
