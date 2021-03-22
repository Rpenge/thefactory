package com.systemk.thefactor2.Service.Impl;


import com.systemk.thefactor2.Mapper.TfProductMapper;
import com.systemk.thefactor2.Service.ProductService;
import com.systemk.thefactor2.VO.TfProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private TfProductMapper tfProductMapper;

	@Override
	public List<TfProductVO> productList() throws Exception {
		return tfProductMapper.productList();
	}
	// 상품정보 추가
	@Override
	public Map<String, Object> productSave(Map param) throws Exception {
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
		Map map = new HashMap();
		if (tfProductMapper.productUpdate(param) == 1) {
			map.put("resultCode", "S");
		} else {
			map.put("resultCode", "E");
		}
		return map;
	}
	// 상품정보 삭제
	@Override
	public Map<String, Object> productDelete(Map param) throws Exception {
		Map map =  new HashMap();
		if (tfProductMapper.productDelete(param) == 1) {
			map.put("resultCode", "S");
		} else {
			map.put("resultCode", "E");
		}
		return map;
	}
}
