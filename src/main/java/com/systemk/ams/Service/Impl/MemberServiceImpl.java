package com.systemk.ams.Service.Impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.systemk.ams.Config.MultiDataBase.myDataSourceConfig;
import com.systemk.ams.DAO.MemberDAO;
import com.systemk.ams.Entity.Main.UserInfo;
import com.systemk.ams.Entity.mapping.memberMapping;
import com.systemk.ams.Repository.Main.MemberRepository;
import com.systemk.ams.Service.MemberService;




@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
    private myDataSourceConfig myDataSourceConfig;

	@Override
	public void memberReg(UserInfo regData) throws Exception {
		regData.setPassword(passwordEncoder.encode(regData.getPassword()));
		memberDAO.insert(regData);
	}

	@Override
	public Page<memberMapping> memberList(Pageable pageable) {
		return memberRepository.findAllBy(pageable);
	}

	@Override
	public void userUpdate(UserInfo user) throws SQLException {
		TransactionSynchronizationManager.initSynchronization();
        Connection conn = DataSourceUtils.getConnection(myDataSourceConfig.myDataSource());
        conn.setAutoCommit(false);
        try {
        	memberRepository.save(user);
        	if(user.getPassword() != null) {
        		memberDAO.passwordUpdate(user.getUserId(), passwordEncoder.encode(user.getPassword()));
        	}
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
        } finally{
            DataSourceUtils.releaseConnection(conn, myDataSourceConfig.myDataSource()); // 커넥션을 닫음
            TransactionSynchronizationManager.unbindResource(myDataSourceConfig.myDataSource());
            TransactionSynchronizationManager.clearSynchronization();
        }	
	}

	
	@Override
	public memberMapping userInfo(String userId) {
		return memberRepository.findOneByUserId(userId);
	}
}
