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
import org.springframework.transaction.annotation.Transactional;

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
			if(key.equals("startDate") || key.equals("endDate") || key.equals("sort") || key.equals("direct") || key.equals("size")|| key.equals("page")){
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
		map.put("size", 	mapData.get("PRD_SIZE"));		//재고정보에서
		map.put("tagId", 	param.get("tfPrdTagid"));		//  가져온 태그id
		map.put("storeCd", 	param.get("inStoreCd"));	//  입고매장
		map.put("storeNm", 	commService.codeToNm((String)param.get("inStoreCd")));	//  입고매장명
		map.put("outStoreCd", 	outputData.get("outStoreCd"));	//  출고매장
		map.put("outStoreNm", 	outputData.get("outStoreNm"));	//  출고매장명
		map.put("deviceGub",param.get("deviceGub"));	//장비값 :
		map.put("inType", 	param.get("stInType"));	//입고 코드

		tfInputMapper.inputRe((HashMap) map);
		return ResultUtil.setCommonResult("S","성공하였습니다");
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public Map<String, Object> inputDelete(Map param) throws Exception {
		String userId = (String)param.get("userId");
		List<Integer> dList = (List)param.get("list");

		for(Integer seq : dList){
			if(!tfInputMapper.inputDeleteCheck(seq)){
				return ResultUtil.setCommonResult("E",seq + "현재 재고에 존재하지 않거나, 추가로 입고 작업이 일어난 데이터입니다");
			}
		}
		List<TfInputVO> voList = tfInputMapper.inputDeleteList(dList);
		for(TfInputVO vo : voList){
			Map map = new HashMap();
			map.put("ST_IN_SEQ",vo.getStInSeq());
			map.put("ST_IN_DATE",vo.getStInDate());
			map.put("USER_ID", (String)param.get("userId"));
			map.put("PRD_BARCODE", vo.getBtPrdBarcode());
			map.put("TF_PRD_TAGID", vo.getTfPrdTagid());
			map.put("IN_STORE_CD", vo.getInStoreCd());
			map.put("ST_IN_TYPE", vo.getStInType());
			tfInputMapper.inputDelete((HashMap) map);
		}

		return ResultUtil.setCommonResult("S","성공하였습니다");
	}


}
