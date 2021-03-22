package com.systemk.thefactor2.Service.Impl;


import com.systemk.thefactor2.Mapper.TfBrandMapper;
import com.systemk.thefactor2.Service.BrandService;
import com.systemk.thefactor2.VO.TfBrandVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private TfBrandMapper tfBrandMapper;

	@Override
	public List<TfBrandVO> findBrand() throws Exception {
		return tfBrandMapper.brandList();
	}

	@Override
	public List<TfBrandVO> findBrandSub(String brandCd) throws Exception {
		return tfBrandMapper.brandSubList(brandCd);
	}

	@Override
	public String codeToNm(String code) {
		TfBrandVO vo = tfBrandMapper.findBrand(code);
		return vo.getBrandNm();
	}
	// 브랜드 전체 목록
	@Override
	public List<TfBrandVO> brandAllList() throws Exception {
		return tfBrandMapper.brandAllList();
	}
	// 브랜드 추가
	@Override
	public Map<String, Object> brandSave(Map param) throws Exception {
		Map map = new HashMap();
		if (tfBrandMapper.brandSave(param) == 1) {
			map.put("resultCode", "S");
		} else {
			map.put("resultCode", "E");
		}
		return map;
	}
	// 브랜드 수정
	@Override
	public Map<String, Object> brandUpdate(Map param) throws Exception {
		Map map = new HashMap();
		if (tfBrandMapper.brandUpdate(param) == 1) {
			map.put("resultCode", "S");
		} else {
			map.put("resultCode", "E");
		}
		return map;
	}
	// 브랜드 삭제
	@Override
	public Map<String, Object> brandDelete(Map param) throws Exception {
		Map map = new HashMap();
		if (tfBrandMapper.brandDelete(param) == 1) {
			map.put("resultCode", "S");
		} else {
			map.put("resultCode", "E");
		}
		return map;
	}
}
