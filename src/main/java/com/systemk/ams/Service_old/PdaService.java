package com.systemk.ams.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.systemk.ams.Entity.Main.AssetManagement;
import com.systemk.ams.Entity.Main.UserInfo;

public interface PdaService {

	public UserInfo loginService(UserInfo user);
	
	public List<String> menuService(String role);
	
	public List<AssetManagement> assetList(String userId);
	
	public boolean assetProcess(List<Map<String, String>> list) throws SQLException;
	
	public Map<String, Object> dataCheck(List<Map<String, String>> list);

	void updateFail(List<Map<String, String>> list);
}
