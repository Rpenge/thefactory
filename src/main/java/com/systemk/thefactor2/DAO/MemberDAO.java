//package com.systemk.ams.DAO;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import com.systemk.ams.Config.MultiDataBase.myDataSourceConfig;
//import com.systemk.ams.Entity.Main.UserInfo;
//
//
//@Repository
//public class MemberDAO {
//
//	@Autowired
//	private myDataSourceConfig myDataSourceConfig;
//
//	@Autowired
//	private JdbcTemplate template;
//
//	public void insert(UserInfo regData) throws Exception {
//		template.setDataSource(myDataSourceConfig.myDataSource());
//		String sql = "INSERT INTO user_info(user_info_seq, user_id, password, emp_name, email, emp_no, dept, emp_contact, emp_phone, company, location, position, emp_authorization,user_reg_date) "
//				+"VALUES(get_seq('user_seq'),?,?,?,?,?,?,?,?,?,?,?,'WT', now())";
//		template.update(sql, regData.getUserId(), regData.getPassword(), regData.getEmpName(),regData.getEmail(), regData.getEmpNo(),
//				regData.getDept() ,regData.getEmpContact(),regData.getEmpPhone(), regData.getCompany(), regData.getLocation(), regData.getPosition());
//	}
//
//	public void passwordUpdate(String userId, String password) {
//		template.setDataSource(myDataSourceConfig.myDataSource());
//		String sql = "UPDATE user_info SET password='" + password + "' WHERE user_id='" + userId+"'";
//		template.update(sql);
//
//	}
//
//
//}
