//package com.systemk.ams.DAO;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import com.systemk.ams.Config.MultiDataBase.myDataSourceConfig;
//import com.systemk.ams.Entity.Main.AssetManagement;
////import com.systemk.ams.Service.Mapper.MenuMapper;
//
//
//@Repository
//public class PdaDAO {
//
//	@Autowired
//	private myDataSourceConfig myDataSourceConfig;
//
//	@Autowired
//	private JdbcTemplate template;
//
////	public List<String> menuSearch(String role) throws Exception {
////		template.setDataSource(myDataSourceConfig.myDataSource());
////		String sql = "SELECT MENU_CODE FROM MENU_TABLE WHERE ROLE='"+role+"'";
////		return template.query(sql,new MenuMapper() );
////	}
//
//	public int assetMapping(String controlCode){
//		template.setDataSource(myDataSourceConfig.myDataSource());
//		String sql = "UPDATE asset_management SET MAPPING_YN = 'Y' WHERE asset_control_code='"+controlCode+"'";
//		return template.update(sql);
//	}
//
//	public int assetMove(String controlCode, String location){
//		template.setDataSource(myDataSourceConfig.myDataSource());
//		String sql = "UPDATE asset_management SET asset_location = '"+location+"' WHERE asset_control_code='"+controlCode+"'";
//		return template.update(sql);
//	}
//
//	public int assetDisposal(String controlCode){
//		template.setDataSource(myDataSourceConfig.myDataSource());
//		String sql = "UPDATE asset_management SET asset_status = 'DIS' WHERE asset_control_code='"+controlCode+"'";
//		return template.update(sql);
//	}
//
//	public int assetChangeInsert(Map<String, String> list, String updateYn) {
//		template.setDataSource(myDataSourceConfig.myDataSource());
//		String sql = "INSERT INTO asset_change(ast_chg_seq, ast_ctrl_cd,ast_dp, ast_nm, ast_st, ast_wk, chg_dt, ast_div, wk_prsn, wk_loc, wi_yn, wk_env, update_yn)"
//				+ "value(get_seq('ast_chg_seq'), ?, ?, ?, ?, ?, NOW(),?,?,?,?,'PDA', ?)";
//		return template.update(sql, list.get("controlCode"),list.get("astDp"),list.get("astNm")
//				,list.get("astSt"),list.get("typeCode"),list.get("astDiv"),list.get("regUserId"),list.get("location"),list.get("confirmYn"), updateYn);
//	}
//
//}
