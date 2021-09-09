package com.systemk.thefactor2.Service.Impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.systemk.thefactor2.Mapper.PageMapper;
import com.systemk.thefactor2.Mapper.TfProductMapper;
import com.systemk.thefactor2.Service.BrandService;
import com.systemk.thefactor2.Service.ProductService;
import com.systemk.thefactor2.Util.MybatisUtil;
import com.systemk.thefactor2.Util.StringUtil;
import com.systemk.thefactor2.VO.TfInputVO;
import com.systemk.thefactor2.VO.TfProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private TfProductMapper tfProductMapper;

	@Autowired
	private BrandService brandService;

	@Autowired
	private PageMapper pageMapper;

	@Override
	public Map<String, Object> productList(Map param) throws Exception {

		MybatisUtil mu = new MybatisUtil();
		mu.setTable("TF_PRODUCT");

		for(Object key : param.keySet()) {    //분류 처리
			if(key.equals("startDate") || key.equals("endDate") || key.equals("sort") || key.equals("direct") || key.equals("size")|| key.equals("page")){
				continue;
			}
			
			if (key.equals("word")) {
				/*
				mu.addLike("TF_PRD_NM", (String)param.get(key));
				mu.addORLike("TF_PRD_CD", (String)param.get(key));
				mu.addORLike("EC_PRD_CD", (String)param.get(key));
				*/
				/*210903 수정*/
				mu.addAndORLike("TF_PRD_NM", "TF_PRD_CD", "EC_PRD_CD", (String)param.get(key));
				break;
			}
			if (key.equals("EC_SIZE_NM")) {
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

		List<TfProductVO> voList =  tfProductMapper.productList(mu.getTableSearch());
		List<Map> listMap = new ArrayList<Map>();
		//mu.setContent(tfInputMapper.inputList(mu.getTableSearch())); //리스트 조회
		for(TfProductVO vo : voList){
			ObjectMapper objectMapper = new ObjectMapper();
			Map map = objectMapper.convertValue(vo, Map.class);
			Map brandInfo = brandService.detailSearch(vo.getBrandKindCd());
			if(brandInfo != null) {
				map.putAll(brandInfo);
			}
			listMap.add(map);
		}
		mu.setContent(listMap);
		return mu.getList();
	}

	// 상품정보 추가
	@Override
	public Map<String, Object> productSave(Map param) throws Exception {
		param.put("useYn", "Y");
		Map map = new HashMap();
		if (tfProductMapper.productSave(param) == 1) {
			map.put("resultCode", "S");
		} else {
			map.put("resultCode", "E");
		}
		return map;
	}
	// 상품정보 수정
	@Override
	public Map<String, Object> productUpdate(Map param) throws Exception {
		System.out.println(param);
		Map map = new HashMap();
		if (tfProductMapper.productUpdate(param) == 1) {
			map.put("resultCode", "S");
		} else {
			map.put("resultCode", "E");
		}
		return map;
	}
	// 상품정보 삭제
	/*
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Map<String, Object> productDelete(Map param) throws Exception {
		List<Integer> list = (List) param.get("list");
		Map map = new HashMap();
		for(int seq : list){
			if (tfProductMapper.productDelete(seq) == 1) {
				map.put("resultCode", "S");
			} else {
				map.put("resultCode", "E");
			}
		}
		return map;
	}
	*/
	
	/*
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Map<String, Object> productDelYn(Map param) throws Exception {
		List<Integer> list = (List) param.get("list");
		Map map = new HashMap();
		for(int seq : list){
			if (tfProductMapper.productDelYn(seq) == 1) {
				map.put("resultCode", "S");
			} else {
				map.put("resultCode", "E");
			}
		}
		return map;
	}
	*/
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Map<String, Object> productDelYn(Map param) throws Exception {
		List<Integer> list = (List) param.get("list");
		Map map = new HashMap();
		String[] sArray = new String[list.size()]; // 체크박스에 체크된 삭제할 데이터 배열 선언
		int k = 0;
		String[] spArray = null;
		
		// 상품을 삭제 시 상품데이터의 삭제 플래그를 Y로 업데이트 
		for(int seq : list){
			if (tfProductMapper.productDelYn1(seq) == 1) { // Y로 업데이트 성공이면,
				sArray[k] = Integer.toString(seq) + "," + "S"; // sArray 변수에 삭제할 품목의 seq 값, "S" 담는다(왜?: 재고테이블에서 해당 상품을 삭제할 때 사용하기 위해)
			} else {
				sArray[k] = Integer.toString(seq) + "," + "E";
			}
			k++;
		}
		
		// 재고테이블에서 삭제 플래그 Y로 업데이트
		for(int i=0; i < sArray.length; i++) {
			spArray = sArray[i].split(",");
			if(spArray[1].equals("S")) {
				int aa = Integer.parseInt(spArray[0]);
				if(tfProductMapper.productDelYn2(aa) == 1) {
					map.put("resultCode", "S");
				} else {
					tfProductMapper.productDelYn3(aa);
					map.put("resultCode", "E");
				}
			} else {
				map.put("resultCode", "E");
			}
		}
		return map;
	}
}
