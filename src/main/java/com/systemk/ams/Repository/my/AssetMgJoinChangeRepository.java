package com.systemk.ams.Repository.my;


import org.springframework.data.jpa.repository.JpaRepository;

import com.systemk.ams.Entity.Main.AssetMgJoinChange;

public interface AssetMgJoinChangeRepository extends JpaRepository<AssetMgJoinChange, Long>{
	
	public AssetMgJoinChange findByAssetControlCode(String controlCode);

}
