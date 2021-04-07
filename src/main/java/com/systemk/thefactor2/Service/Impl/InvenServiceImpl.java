package com.systemk.thefactor2.Service.Impl;


import com.systemk.thefactor2.Mapper.PageMapper;
import com.systemk.thefactor2.Mapper.TfInvStatusMapper;
import com.systemk.thefactor2.Mapper.TfInventoryMapper;
import com.systemk.thefactor2.Service.*;
import com.systemk.thefactor2.Util.MybatisUtil;
import com.systemk.thefactor2.Util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class InvenServiceImpl implements InvenService {

	@Autowired
	private PageMapper pageMapper;

	@Autowired
	private TfInvStatusMapper tfInvStatusMapper;

	@Autowired
	private TfInventoryMapper tfInventoryMapper;


	@Override
	public Map<String, Object> findInvStatus(Map param) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("TF_INV_STATUS");

		if(param.get("startDate")!= null && param.get("endDate")!= null){
			mu.addBetween("ST_INV_DATE",(String)param.get("startDate"), (String)param.get("endDate"));
		}

		mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch())); // 수량조회
		if(param.get("page")!=null)
			mu.setPage(Integer.parseInt((String)param.get("page")));
		if(param.get("size")!=null)
			mu.setSize(Integer.parseInt((String)param.get("size")));

		List<Map> listMap = new ArrayList<Map>();
		mu.setContent(tfInvStatusMapper.invStatusList(mu.getTableSearch())); //리스트 조회

		return mu.getList();
	}


	@Override
	public Map<String, Object> findInvList(Map param) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("TF_INVENTORY");

		for(Object key : param.keySet()) {
			if(key.equals("startDate") || key.equals("endDate") || key.equals("sort") || key.equals("direct") || key.equals("size")|| key.equals("page")){
				continue;
			}else{
				mu.addEqual(StringUtil.camelToSnake((String)key), (String)param.get(key));
			}
		}

		mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch())); // 수량조회
		if(param.get("page")!=null)
			mu.setPage(Integer.parseInt((String)param.get("page")));
		if(param.get("size")!=null)
			mu.setSize(Integer.parseInt((String)param.get("size")));

		List<Map> listMap = new ArrayList<Map>();
		mu.setContent(tfInventoryMapper.invenList(mu.getTableSearch())); //리스트 조회

		return mu.getList();
	}

	@Override
	public Map<String, Object> invenUpdate(Map param) throws Exception {
		tfInventoryMapper.manualInvUpdate(param);
		return null;
	}


}
