package com.systemk.thefactor2.Service.Impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.systemk.thefactor2.Mapper.PageMapper;
import com.systemk.thefactor2.Mapper.TfAcStockMapper;
import com.systemk.thefactor2.Mapper.TfProductMapper;
import com.systemk.thefactor2.Service.AcStockService;
import com.systemk.thefactor2.Service.BrandService;
import com.systemk.thefactor2.Service.StockService;
import com.systemk.thefactor2.Util.MybatisUtil;
import com.systemk.thefactor2.Util.StringUtil;
import com.systemk.thefactor2.VO.TfAcStockVO;
import com.systemk.thefactor2.VO.TfInputVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private PageMapper pageMapper;


	@Override
	public Map<String, Object> findList(Map param) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("TF_STOCK");

		for(Object key : param.keySet()) {    //분류 처리
			if(key.equals("sort") || key.equals("direct") || key.equals("size")|| key.equals("page")){
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


		mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch())); // 수량조회
		if(param.get("page")!=null)
			mu.setPage(Integer.parseInt((String)param.get("page")));
		if(param.get("size")!=null)
			mu.setSize(Integer.parseInt((String)param.get("size")));

//		List<TfInputVO> voList =  tfInputMapper.inputList(mu.getTableSearch());
		List<Map> listMap = new ArrayList<Map>();
		//mu.setContent(tfInputMapper.inputList(mu.getTableSearch())); //리스트 조회
//		for(TfInputVO vo : voList){
//			ObjectMapper objectMapper = new ObjectMapper();
//			Map map = objectMapper.convertValue(vo, Map.class);
//			Map brandInfo = brandService.detailSearch(vo.getBrandKindCd());
//			map.putAll(brandInfo);
//			listMap.add(map);
//		}
		mu.setContent(listMap);
		return mu.getList();
	}

	@Override
	public Map<String, Object> stkGubSearch(Map param) throws Exception {
		return null;
	}
}
