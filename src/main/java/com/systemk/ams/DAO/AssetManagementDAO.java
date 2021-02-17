package com.systemk.ams.DAO;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.systemk.ams.Config.MultiDataBase.myDataSourceConfig;
import com.systemk.ams.Entity.Main.AssetManagement;


@Repository
public class AssetManagementDAO {
	
	@Autowired
	private myDataSourceConfig myDataSourceConfig;
	
	@Autowired
	private JdbcTemplate template;
		
	public void insertAsset(AssetManagement list) throws Exception {
		template.setDataSource(myDataSourceConfig.myDataSource());
		String sql = "INSERT INTO asset_management (`asset_management_seq`,`asset_control_code`, `asset_name`, `model_number`, `asset_division`, `serial_number`, `manufacturer`, `asset_reg_date`, `asset_reg_person`,`mapping_yn`, `asset_barcode`, `asset_purchase`, `asset_price`, `asset_status`, `asset_dept`, `asset_location`, `warranty`, `asset_contact`, `asset_quantity`, `asset_detail`)" 
			+ "VALUE(get_seq('asset_management_seq'),'" + list.getAssetControlCode()+"',";
			sql += list.getAssetName() != null ? "'"+list.getAssetName()+"'," : "null,";
			sql += list.getModelNumber() != null ? "'"+list.getModelNumber()+"'," : "null,";
			sql += list.getAssetDivision() != null ? "'"+list.getAssetDivision()+"'," : "null,";
			sql += list.getSerialNumber() != null ? "'"+list.getSerialNumber()+"'," : "null,";
			sql += list.getManufacturer() != null ? "'"+list.getManufacturer()+"'," : "null,";
			sql += list.getDateForm() != null ? "'"+list.getDateForm()+"'," : "null,";
			sql += list.getAssetRegPerson() != null ? "'"+list.getAssetRegPerson()+"'," : "null,";
			sql += list.getMappingYn() != null ? "'"+list.getMappingYn()+"'," : "null,";
			sql += list.getAssetBarcode() != null ? "'"+list.getAssetBarcode()+"'," : "null,";
			sql += list.getAssetPurchase() != null ? "'"+list.getAssetPurchase()+"'," : "null,";
			sql += list.getAssetPrice() != null ? "'"+list.getAssetPrice()+"'," : "null,";
			sql += list.getAssetStatus() != null ? "'"+list.getAssetStatus()+"'," : "null,";
			sql += list.getAssetDept() != null ? "'"+list.getAssetDept()+"'," : "null,";
			sql += list.getAssetLocation() != null ? "'"+list.getAssetLocation()+"'," : "null,";
			sql += list.getWarranty() != null ? "'"+list.getWarranty()+"'," : "null,";
			sql += list.getAssetContact() != null ? "'"+list.getAssetContact()+"'," : "null,";
			sql += list.getAssetQuantity() != null ? "'"+list.getAssetQuantity()+"'," : "null,";
			sql += list.getAssetDetail() != null ? "'"+list.getAssetDetail()+"')" : "null)";
		template.update(sql);
	}
	
	public void deleteAsset(AssetManagement asset) {
		template.setDataSource(myDataSourceConfig.myDataSource());
		String sql = "DELETE FROM asset_management WHERE asset_management_seq = '"+asset.getAssetManagementSeq()+"'";
		template.update(sql);
	}
	
	public int assetChangeInsert(AssetManagement asset, String userId, String assetWork) {
		template.setDataSource(myDataSourceConfig.myDataSource());
		String sql = "INSERT INTO asset_change(ast_chg_seq, ast_ctrl_cd, ast_dp, ast_nm, ast_st, ast_wk, chg_dt, ast_div, wk_prsn, wk_loc, wk_env, update_yn)"
				+ "value(get_seq('ast_chg_seq'), ?, ?, ?, ?, ?, NOW(),?,?,?,'WEP','Y')";
		return template.update(sql, asset.getAssetControlCode(), asset.getAssetDept(), asset.getAssetName()
				,asset.getAssetStatus() ,assetWork ,asset.getAssetDivision() ,userId ,asset.getAssetLocation());
	}
}
