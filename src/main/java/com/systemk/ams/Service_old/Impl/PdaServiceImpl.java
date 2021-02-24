package com.systemk.ams.Service.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.systemk.ams.Config.MultiDataBase.myDataSourceConfig;
import com.systemk.ams.DAO.PdaDAO;
import com.systemk.ams.Entity.Main.AssetManagement;
import com.systemk.ams.Entity.Main.UserInfo;
import com.systemk.ams.Repository.Main.AssetManagementRepository;
import com.systemk.ams.Repository.Main.MemberRepository;
import com.systemk.ams.Service.PdaService;

@Service
public class PdaServiceImpl implements PdaService{
	
	@Autowired
    private myDataSourceConfig myDataSourceConfig;

	@Autowired
	private MemberRepository memberRepository; 
	
	@Autowired
	private AssetManagementRepository assetManagementRepository; 
	
	@Autowired
	private PdaDAO pdaDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserInfo loginService(UserInfo user) {
		try {
			UserInfo result = memberRepository.findByUserId(user.getUserId());
			if(passwordEncoder.matches(user.getPassword(), result.getPassword())) {
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String> menuService(String role) {
		try {
			return pdaDAO.menuSearch(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AssetManagement> assetList(String userId) {
		try {
			UserInfo user = memberRepository.findByUserId(userId);
			if(user.getEmpAuthorization().equals("MT")) { //관리자 권한은 전체 조회, 그외에는 각 부서 데이터 조회
				return assetManagementRepository.findAll();
			}else {
				return assetManagementRepository.findByAssetDept(user.getDept());
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean assetProcess(List<Map<String, String>> list) throws SQLException {
		TransactionSynchronizationManager.initSynchronization();
        Connection conn = DataSourceUtils.getConnection(myDataSourceConfig.myDataSource());
        conn.setAutoCommit(false);
        boolean check = true;
        try {
        	for(int i=0;i<list.size();i++) {
        		Map<String, String> map = list.get(i);
            	if(map.get("typeCode").equals("AR")) {
            		int updateCheck = pdaDAO.assetMapping(map.get("controlCode"));
            	}else if(map.get("typeCode").equals("AM")) {
            		int updateCheck = pdaDAO.assetMove(map.get("controlCode"), map.get("location"));
            	}else if(map.get("typeCode").equals("WI")) {
            		//조사
            	}else if(map.get("typeCode").equals("AD")) {
            		int updateCheck = pdaDAO.assetDisposal(map.get("controlCode"));
            	}
            	int insertCheck = pdaDAO.assetChangeInsert(map, "Y");
            }
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            check = false;
        } finally{
            DataSourceUtils.releaseConnection(conn, myDataSourceConfig.myDataSource()); // 커넥션을 닫음
            TransactionSynchronizationManager.unbindResource(myDataSourceConfig.myDataSource());
            TransactionSynchronizationManager.clearSynchronization();
        }	
		return check;
	}

	@Override
	public void updateFail(List<Map<String, String>> list){
    	for(int i=0;i<list.size();i++) {
    		Map<String, String> map = list.get(i);
        	pdaDAO.assetChangeInsert(map, "N");
    	}
	}
	
	@Override
	public Map<String, Object> dataCheck(List<Map<String, String>> list) {
		List<String> failList = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
		for(int i=0;i<list.size();i++) {
			AssetManagement asset = assetManagementRepository.findByAssetControlCode((String)list.get(i).get("controlCode"));
			if(asset == null) {
				failList.add((String)list.get(i).get("controlCode"));
			}else {
				list.get(i).put("astNm", asset.getAssetName());
				list.get(i).put("astDp", asset.getAssetDept());
				list.get(i).put("astDiv", asset.getAssetDivision());
				list.get(i).put("astSt", asset.getAssetStatus());
			}
		}
		map.put("failList", failList);
		map.put("assetList", list);
		return map;
	}

}
