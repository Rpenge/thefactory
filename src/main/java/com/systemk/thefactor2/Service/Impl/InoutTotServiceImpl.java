package com.systemk.thefactor2.Service.Impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.systemk.thefactor2.Mapper.PageMapper;
import com.systemk.thefactor2.Mapper.TfInoutTotalMapper;
import com.systemk.thefactor2.Mapper.TfUserMapper;
import com.systemk.thefactor2.Service.InoutTotService;
import com.systemk.thefactor2.Service.UserService;
import com.systemk.thefactor2.Util.MybatisUtil;
import com.systemk.thefactor2.Util.StringUtil;
import com.systemk.thefactor2.VO.TfInoutTotalVO;
import com.systemk.thefactor2.VO.TfInputVO;
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

	@Autowired
	private PageMapper pageMapper;

	@Override
	public Map<String, Object> findList(Map param) throws Exception {

		MybatisUtil mu = new MybatisUtil();
		mu.setTable("TF_INOUT_TOTAL");

		for(Object key : param.keySet()) {    //분류 처리
			if(key.equals("startDate") || key.equals("endDate") || key.equals("sort") || key.equals("direct") || key.equals("size")|| key.equals("page")){
				continue;
			}

//			if (key.equals("word")) {
//				mu.addLike("TF_PRD_NM", (String)param.get(key));
//				mu.addORLike("TF_PRD_CD", (String)param.get(key));
//				break;
//			}
			if (key.equals("PRD_SIZE")) {
				mu.addLike((String)key, (String)param.get(key));
			}else if(key.equals("BRAND_KIND_CD")){
				mu.addStartLike((String)key, (String)param.get(key));
			}else{
				mu.addEqual((String)key, (String)param.get(key));
			}
		}
		if(param.get("sort")!= null) {
			mu.setSort(StringUtil.camelToSnake((String)param.get("sort")), (String) param.get("direct"));
		}

		if(param.get("startDate")!= null && param.get("endDate")!= null){
			mu.addBetween("IN_OUT_DATE",(String)param.get("startDate"), (String)param.get("endDate"));
		}

		mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch())); // 수량조회
		if(param.get("page")!=null)
			mu.setPage(Integer.parseInt((String)param.get("page")));
		if(param.get("size")!=null)
			mu.setSize(Integer.parseInt((String)param.get("size")));

		mu.setContent(tfInoutTotalMapper.inoutList(mu.getTableSearch())); //리스트 조회

		return mu.getList();
	}


	@Override
	public TfInoutTotalVO todayWork(Map param) {
		if(param.get("storeCd")!= null) {
			return tfInoutTotalMapper.todayWork(param);
		}else{
			return tfInoutTotalMapper.todayWorkAllVO();
		}
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
