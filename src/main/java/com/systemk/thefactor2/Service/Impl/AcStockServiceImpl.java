package com.systemk.thefactor2.Service.Impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.systemk.thefactor2.Mapper.PageMapper;
import com.systemk.thefactor2.Mapper.TfAcStockMapper;
import com.systemk.thefactor2.Mapper.TfProductMapper;
import com.systemk.thefactor2.Service.AcStockService;
import com.systemk.thefactor2.Service.BrandService;
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
public class AcStockServiceImpl implements AcStockService {

	@Autowired
	private TfAcStockMapper tfAcStockMapper;

	@Autowired
	private TfProductMapper tfProductMapper;

	@Autowired
	private BrandService brandService;

	@Autowired
	private PageMapper pageMapper;


	@Override
	public Map<String, Object> findStock(Map param) throws Exception {
		Map map = new HashMap();
		TfAcStockVO vo = tfAcStockMapper.findStockByTagId((String)param.get("tagId"));
		if(vo != null){
			map.put("barcode", vo.getTfPrdBarcode());
			Map mapData = tfProductMapper.prdAndStk(map);
			Map brandInfo = brandService.detailSearch((String)mapData.get("BRAND_KIND_CD"));
			map.put("btPrdBarcode",mapData.get("TF_PRD_BARCODE"));
			map.put("tfPrdTagid", param.get("tagId"));
			map.put("outStoreCd", vo.getStoreCd());
			map.put("prdSize",mapData.get("PRD_SIZE"));
			map.put("ecPrdCd",mapData.get("EC_PRD_CD"));
			map.put("tfPrdCd",mapData.get("TF_PRD_CD"));
			map.putAll(brandInfo);
		}
		return map;
	}

	@Override
	public Map<String, Object> findAcStock(Map param) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("TF_AC_STOCK");

		for(Object key : param.keySet()) {    //분류 처리
			if(key.equals("startDate") || key.equals("endDate") || key.equals("sort") || key.equals("direct") || key.equals("size")|| key.equals("page")){
				continue;
			}

			if (key.equals("word")) {
//				mu.addLike("TF_PRD_NM", (String)param.get(key));
//				mu.addORLike("TF_PRD_CD", (String)param.get(key));
				mu.addLike("TF_PRD_BARCODE", (String)param.get(key));
				mu.addORLike("TF_PRD_TAGID", (String)param.get(key));
				break;
			}
			if (key.equals("PRD_SIZE")) {
				mu.addEqual("AC_PRD_SIZE", (String)param.get(key));
			}else if(key.equals("BRAND_KIND_CD")){
				mu.addStartLike((String)key, (String)param.get(key));
			}else{
				mu.addEqual((String)key, (String)param.get(key));
			}
		}

		if(param.get("startDate")!= null && param.get("endDate")!= null){
			mu.addBetween("ST_IN_DATE",(String)param.get("startDate"), (String)param.get("endDate"));
		}

		mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch())); // 수량조회
		if(param.get("page")!=null)
			mu.setPage(Integer.parseInt((String)param.get("page")));
		if(param.get("size")!=null)
			mu.setSize(Integer.parseInt((String)param.get("size")));

		List<Map> acList =  tfAcStockMapper.acStockList(mu.getTableSearch());

		mu.setContent(acList);
		return mu.getList();
	}


}
