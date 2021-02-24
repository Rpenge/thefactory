package com.systemk.ams.Service.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class UserInfoMapper implements RowMapper<List<String>>{

	@Override
	public List<String> mapRow(ResultSet rs, int rowNum) throws SQLException {
		List<String> list = new ArrayList<>();
		list.add(rs.getString("USER_ID"));
		list.add(rs.getString("USER_PW"));
		return list;
	}

}
