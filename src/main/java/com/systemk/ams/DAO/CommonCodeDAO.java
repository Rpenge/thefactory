package com.systemk.ams.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.systemk.ams.Config.MultiDataBase.myDataSourceConfig;
import com.systemk.ams.Entity.Main.CommonCode;


@Repository
public class CommonCodeDAO {
	
	@Autowired
	private myDataSourceConfig myDataSourceConfig;
	
	@Autowired
	private JdbcTemplate template;
		
	public void insert(CommonCode code) throws Exception {
		template.setDataSource(myDataSourceConfig.myDataSource());
		String sql = "INSERT INTO common_code(cmmn_seq, code, code_name, parent_code)"
				+ "VALUES(get_seq('cmmn_seq'),'"+code.getCode()+"','"+code.getCodeName()+"','"+code.getParentCode()+"')";
		template.update(sql);
	}

	public void delete(List<String> list) {
		template.setDataSource(myDataSourceConfig.myDataSource());
		String sql = "DELETE FROM common_code WHERE cmmn_seq IN (";
		for(String str : list)
			sql += str +",";
		sql = sql.substring(0, sql.length() - 1);
		sql += ")";	
		template.update(sql);
		
	}
	
	
	
}
