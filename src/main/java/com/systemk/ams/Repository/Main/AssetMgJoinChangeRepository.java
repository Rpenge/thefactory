package com.systemk.ams.Repository.Main;


import org.springframework.data.jpa.repository.JpaRepository;

import com.systemk.ams.Entity.Main.AssetMgJoinChange;

public interface AssetMgJoinChangeRepository extends JpaRepository<AssetMgJoinChange, Long>{
	
	public AssetMgJoinChange findByAssetControlCode(String controlCode);

}
