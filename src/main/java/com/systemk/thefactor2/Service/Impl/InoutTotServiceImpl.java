package com.systemk.thefactor2.Service.Impl;


import com.systemk.thefactor2.Mapper.PageMapper;
import com.systemk.thefactor2.Mapper.TfInoutTotalMapper;
import com.systemk.thefactor2.Mapper.TfUserMapper;
import com.systemk.thefactor2.Service.InoutTotService;
import com.systemk.thefactor2.Service.UserService;
import com.systemk.thefactor2.Util.MybatisUtil;
import com.systemk.thefactor2.Util.StringUtil;
import com.systemk.thefactor2.VO.TfInoutTotalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class InoutTotServiceImpl implements InoutTotService {


	@Autowired
	private TfInoutTotalMapper tfInoutTotalMapper;

	@Override
	public TfInoutTotalVO todayWork(Map param) {
		return tfInoutTotalMapper.todayWork(param);
	}

	@Override
	public Map todayWorkAll() {
		return tfInoutTotalMapper.todayWorkAll();
	}

	@Override
	public List<Map> monthWork() {
		List list = new ArrayList();
		for(int i=0;i<=5;i++) {
			list.add(tfInoutTotalMapper.monthWork(i));
		}
		return list;
	}
}
