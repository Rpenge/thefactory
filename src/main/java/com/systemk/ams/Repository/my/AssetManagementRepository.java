package com.systemk.ams.Repository.my;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.systemk.ams.Entity.Main.AssetManagement;

public interface AssetManagementRepository extends JpaRepository<AssetManagement, Long>, JpaSpecificationExecutor<AssetManagement>{
	

	public List<AssetManagement> findByAssetDept(String dept);
	
	public AssetManagement findByAssetControlCode(String controlCode);
	
	public AssetManagement findByAssetManagementSeq(long number);
	
	public Integer countByAssetDivision(String div);
	
	public Integer countByAssetStatus(String status);
	
	@Query("select m.assetDivision, count(*) from AssetManagement m group by m.assetDivision order by count(*) DESC")
	public List countSelect();
	
	public Integer countByAssetRegDateBetween(Date fromDate, Date toDate);

	@Query("select max(m.assetControlCode) from AssetManagement m where m.assetControlCode like ?1")
	public String findctrlCd(String controlCode);
}
