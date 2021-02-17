package com.systemk.ams.Service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.systemk.ams.Entity.Main.AssetManagement;
import com.systemk.ams.Entity.Main.AssetMgJoinChange;
import com.systemk.ams.Entity.Main.AssetRepair;

 

public interface AssetManagementService {
	
	public Map<String, Object> findList(Map<String, String> search, Pageable pageable) throws Exception;
	
	public List<Map<String, String>> findList(Map<String, String> search) throws Exception;
	
	public AssetManagement assetReg (AssetManagement asset, String userId) throws Exception; 
	
	public void assetModi (AssetManagement asset, String userId) throws Exception; 
	
	public void assetDelete (List<Integer> list, String userId) throws Exception;
	
	public AssetMgJoinChange findAssetDetail(String code) throws Exception;

	public void statusUpdate(List list, String command, String userId);

	public void assetTagUpdate(List list, String command, String userId);

	void assetReg(AssetManagement asset, String userId, int num) throws Exception;

	public List<AssetRepair> createAssetRepair(Map<String, Object> map);
	
	
}
 