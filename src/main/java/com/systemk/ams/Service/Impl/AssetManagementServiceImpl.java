package com.systemk.ams.Service.Impl;


import java.util.Map;

import com.systemk.ams.Util.MybatisUtil;
import com.systemk.ams.Util.StringUtil;
import com.systemk.ams.mapper.AssetMgmtMapper;
import com.systemk.ams.mapper.PageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.systemk.ams.Service.AssetManagementService;

@Service
public class AssetManagementServiceImpl implements AssetManagementService{


	@Autowired
	private PageMapper mapper;

	@Autowired
	private AssetMgmtMapper AMMapper;

	//자산 조회 및 검색
	@Override
	public Map<String, Object> findList(Map<String, String> search) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("asset_management");
		if(search.get("startDate")!=null && search.get("endDate")!=null){	//기간 처리
			mu.addBetween("chg_dt", search.get("startDate"), search.get("endDate"));
		}
		if(search.get("option")!=null && search.get("text") != ""){	//검색어 처리
			mu.addLike(StringUtil.camelToSnake(search.get("option")), search.get("text"));
		}
		for(String key : search.keySet()){	//분류 처리
			if(key.equals("size") || key.equals("page") || key.equals("text") || key.equals("startDate") || key.equals("endDate")
					|| key.equals("option") || key.equals("sort")) {
				continue;
			} else{
				mu.addEqual(StringUtil.camelToSnake(key), search.get(key));
			}
		}
		int count = mapper.pageRecord(mu.getTableSearch());
		mu.pager(search); // 수량, 페이지 설정
		mu.setTotalElements(count);
		mu.setSort(search.get("sort"));
		mu.setContent(AMMapper.assetMgmtList(mu.getTableSearch()));

		return mu.getList();
	}






}
