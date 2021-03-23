package com.systemk.thefactor2.Service.Impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.systemk.thefactor2.Mapper.*;
import com.systemk.thefactor2.Service.BrandService;
import com.systemk.thefactor2.Service.InputService;
import com.systemk.thefactor2.Service.OutputService;
import com.systemk.thefactor2.Util.MybatisUtil;
import com.systemk.thefactor2.Util.StringUtil;
import com.systemk.thefactor2.VO.TfInputVO;
import com.systemk.thefactor2.VO.TfOutputVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OutputServiceImpl implements OutputService {

	@Autowired
	private TfOutputMapper tfOutputMapper;

	@Autowired
	private PageMapper pageMapper;

	@Autowired
	private BrandService brandService;



	@Override
	public Map<String, Object> findList(Map param) throws Exception {

		System.out.println("출고 서비스");
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("TF_OUTPUT");

		for(Object key : param.keySet()) {
			if(key.equals("startDate") || key.equals("endDate") || key.equals("sort") || key.equals("direct")){
				continue;
			}
			if (key.equals("word")) {
				mu.addLike("TF_PRD_NM", (String)param.get(key));
				mu.addORLike("TF_PRD_CD", (String)param.get(key));
				break;
			}
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
			mu.addBetween("ST_IN_DATE",(String)param.get("startDate"), (String)param.get("endDate"));
		}

		mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch())); // 수량조회
		if(param.get("page")!=null)
			mu.setPage(Integer.parseInt((String)param.get("page")));
		if(param.get("size")!=null)
			mu.setSize(Integer.parseInt((String)param.get("size")));

		List<TfOutputVO> voList =  tfOutputMapper.outList(mu.getTableSearch());
		List<Map> listMap = new ArrayList<Map>();
		for(TfOutputVO vo : voList){
			ObjectMapper objectMapper = new ObjectMapper();
			Map map = objectMapper.convertValue(vo, Map.class);
			Map brandInfo = brandService.detailSearch(vo.getBrandKindCd());
			map.putAll(brandInfo);
			listMap.add(map);
		}
		mu.setContent(listMap);
		return mu.getList();
	}

	@Override
	public Map<String, Object> outputSearch(String tagId) throws Exception {
		TfOutputVO vo = tfOutputMapper.outputSearch(tagId);
			ObjectMapper objectMapper = new ObjectMapper();
			Map map = objectMapper.convertValue(vo, Map.class);
		if(vo!=null) {
			map.putAll(brandService.detailSearch(vo.getBrandKindCd()));
		}
		return map;
	}
}
