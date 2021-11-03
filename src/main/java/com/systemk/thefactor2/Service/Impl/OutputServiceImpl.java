package com.systemk.thefactor2.Service.Impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.systemk.thefactor2.Mapper.*;
import com.systemk.thefactor2.Service.BrandService;
import com.systemk.thefactor2.Service.CommService;
import com.systemk.thefactor2.Service.InputService;
import com.systemk.thefactor2.Service.OutputService;
import com.systemk.thefactor2.Util.MybatisUtil;
import com.systemk.thefactor2.Util.ResultUtil;
import com.systemk.thefactor2.Util.StringUtil;
import com.systemk.thefactor2.VO.TfInputVO;
import com.systemk.thefactor2.VO.TfOutputVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class OutputServiceImpl implements OutputService {

	@Autowired
	private TfOutputMapper tfOutputMapper;

	@Autowired
	private PageMapper pageMapper;

	@Autowired
	private TfProductMapper tfProductMapper;

	@Autowired
	private BrandService brandService;

	@Autowired
	private CommService commService;



	@Override
	public Map<String, Object> findList(Map param) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("TF_OUTPUT");
		for(Object key : param.keySet()) {
			if(key.equals("startDate") || key.equals("endDate") || key.equals("sort") || key.equals("direct")|| key.equals("size")|| key.equals("page")){
				continue;
			}
			if (key.equals("word")) {
				mu.addLike("TF_PRD_NM", (String)param.get(key));
				mu.addORLike("TF_PRD_CD", (String)param.get(key));
				mu.addORLike("BT_PRD_BARCODE", (String)param.get(key));
				mu.addORLike("TF_PRD_TAGID", (String)param.get(key));
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
			mu.addBetween("ST_OUT_DATE",(String)param.get("startDate"), (String)param.get("endDate"));
		}
		mu.addEqual("substring(ST_OUT_TYPE,1,4)", "0602");
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
	public Map<String, Object> findSalseList(Map param) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("TF_OUTPUT");
		for(Object key : param.keySet()) {
			if(key.equals("startDate") || key.equals("endDate") || key.equals("sort") || key.equals("direct")|| key.equals("size")|| key.equals("page")){
				continue;
			}
			if (key.equals("word")) {
				mu.addLike("TF_PRD_NM", (String)param.get(key));
				mu.addORLike("TF_PRD_CD", (String)param.get(key));
				mu.addORLike("BT_PRD_BARCODE", (String)param.get(key));
				mu.addORLike("TF_PRD_TAGID", (String)param.get(key));
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
			mu.addBetween("ST_OUT_DATE",(String)param.get("startDate"), (String)param.get("endDate"));
		}
		mu.addEqual("substring(ST_OUT_TYPE,1,4)", "0603");
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
	
	// 211015 추가
	@Override
	public Map<String, Object> outputMoveSearch(String tagId) throws Exception {
		TfOutputVO vo = tfOutputMapper.outputMoveSearch(tagId);
			ObjectMapper objectMapper = new ObjectMapper();
			Map map = objectMapper.convertValue(vo, Map.class);
		if(vo!=null) {
			map.putAll(brandService.detailSearch(vo.getBrandKindCd()));
		}
		return map;
	}

	@Override
	public Map<String, Object> outputAdd(Map param) throws Exception {

		//output데이터 추가, inouttotal수량 +1 재고는 -1, 재고테이블 -1, 실재고에서 삭제
		Map map = new HashMap();
		Map mapData = tfProductMapper.prdAndStk(param);

		map.put("ymd", StringUtil.dateFormatYMD(new Date()));		//오늘날짜
		map.put("userId", 	param.get("userId"));		//현재유저
		map.put("brandCd", 	mapData.get("BRAND_KIND_CD"));	//상품정보에서
		map.put("prdNm", 	mapData.get("TF_PRD_NM"));		//상품정보에서
		map.put("ecPrdCd", 	mapData.get("EC_PRD_CD"));	//상품정보에서
		map.put("prdCd", 	mapData.get("TF_PRD_CD"));		//상품정보에서
		map.put("barcode", 	param.get("barcode"));	//  가져온 바코드
		map.put("size", 	mapData.get("PRD_SIZE"));		//재고정보에서
		map.put("tagId", 	param.get("tfPrdTagid"));		//  가져온 태그id
		if(param.get("stOutType").equals("060202") && param.get("inStoreCd") != null) {
			map.put("inStoreCd", param.get("inStoreCd"));    //  입고예정매장
			map.put("inStoreNm", commService.codeToNm((String) param.get("inStoreCd")));    //  입고예정매장명
		}
		map.put("outStoreCd", param.get("outStoreCd"));	//  출고매장
		map.put("outStoreNm", commService.codeToNm((String)param.get("outStoreCd")));	//  출고매장명
		map.put("deviceGub",param.get("deviceGub"));	//장비값 : PC
		map.put("outType", 	param.get("stOutType"));	//출고 코드
		map.put("outComment", param.get("comment"));

		tfOutputMapper.outputAdd((HashMap) map);
		return ResultUtil.setCommonResult("S","성공하였습니다");
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public Map<String, Object> outputDelete(Map param) throws Exception {
		List<Integer> dList = (List)param.get("list");
		List<TfOutputVO> voList = tfOutputMapper.outDeleteList(dList);

		for(Integer seq : dList){
			if(!tfOutputMapper.outDeleteCheck(seq)){
				return ResultUtil.setCommonResult("E",seq + "출고 상태가 아니거나 최근 데이터가 아닙니다");
			}
		}
		for(TfOutputVO vo : voList){
			Map map = new HashMap();
			map.put("stOutSeq",vo.getStOutSeq());
			map.put("stOutDate",vo.getStOutDate());
			map.put("userId", param.get("userId"));
			map.put("barcode", vo.getBtPrdBarcode());
			map.put("tagId", vo.getTfPrdTagid());
			map.put("storeCd", vo.getOutStoreCd());
			map.put("stOutType", vo.getStOutType());
			map.put("brandCd", vo.getBrandKindCd());
			tfOutputMapper.outDelete((HashMap) map);
		}
		return ResultUtil.setCommonResult("S","성공하였습니다");
	}


}
