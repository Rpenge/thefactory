package com.systemk.thefactor2.Service.Impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.systemk.thefactor2.Mapper.PageMapper;
import com.systemk.thefactor2.Mapper.TfInputMapper;
import com.systemk.thefactor2.Mapper.TfProductMapper;
import com.systemk.thefactor2.Mapper.TfTagPublishMapper;
import com.systemk.thefactor2.Service.BrandService;
import com.systemk.thefactor2.Service.CommService;
import com.systemk.thefactor2.Service.InputService;
import com.systemk.thefactor2.Service.OutputService;
import com.systemk.thefactor2.Util.MybatisUtil;
import com.systemk.thefactor2.Util.ResultUtil;
import com.systemk.thefactor2.Util.StringUtil;
import com.systemk.thefactor2.VO.TfInputVO;
import com.systemk.thefactor2.VO.TfStockVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class InputServiceImpl implements InputService {

	@Autowired
	private PageMapper pageMapper;

	@Autowired
	private TfInputMapper tfInputMapper;

	@Autowired
	private TfTagPublishMapper tfTagPublishMapper;

	@Autowired
	private TfProductMapper tfProductMapper;

	@Autowired
	private OutputService outputService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private CommService commService;



	@Override
	public Map<String, Object> findList(Map param) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("TF_INPUT");

		for(Object key : param.keySet()) {    //분류 처리
			if(key.equals("startDate") || key.equals("endDate")){
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
			}else if(key.equals("ST_IN_TYPE")){
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

		List<TfInputVO> voList =  tfInputMapper.inputList(mu.getTableSearch());
		List<Map> listMap = new ArrayList<Map>();
		//mu.setContent(tfInputMapper.inputList(mu.getTableSearch())); //리스트 조회
		for(TfInputVO vo : voList){
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
	public Map<String, Object> searchPrd(Map param) throws Exception {
		return outputService.outputSearch((String) param.get("tagId"));
	}



	//반품입고, 점간입고 : 태그발행x, 수량+1, 실재고 등록, 입고테이블 등록
	@Override
	public Map<String, Object> inputAddResult(Map param) throws Exception {

		Map map = new HashMap();
		Map outputData = outputService.outputSearch((String) param.get("tfPrdTagid"));
		Map mapData = tfProductMapper.prdAndStk(param);

		Date date = new Date();
		map.put("ymd", StringUtil.dateFormatYMD(date));		//오늘날짜
		map.put("userId", 	param.get("userId"));		//현재유저
		map.put("brandCd", 	mapData.get("BRAND_KIND_CD"));	//상품정보에서
		map.put("prdNm", 	mapData.get("TF_PRD_NM"));		//상품정보에서
		map.put("ecPrdCd", 	mapData.get("EC_PRD_CD"));	//상품정보에서
		map.put("prdCd", 	mapData.get("TF_PRD_CD"));		//상품정보에서
		map.put("barcode", 	param.get("barcode"));	//  가져온 바코드
		map.put("size", 	mapData.get("PRD_SIZE_CD"));		//재고정보에서
		map.put("tagId", 	param.get("tfPrdTagid"));		//  가져온 태그id
		map.put("storeCd", 	param.get("inStoreCd"));	//  입고매장
		map.put("storeNm", 	commService.codeToNm((String)param.get("inStoreCd")));	//  입고매장명
		map.put("storeCd", 	outputData.get("outStoreCd"));	//  출고매장
		map.put("storeNm", 	outputData.get("outStoreNm"));	//  출고매장명
		map.put("deviceGub",param.get("deviceGub"));	//장비값 : PDA코드 : 헤더값
		map.put("inType", 	param.get("stInType"));	//입고 코드

		tfInputMapper.inputRe((HashMap) map);
		return ResultUtil.setCommonResult("S","성공하였습니다");
	}


}
