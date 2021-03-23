package com.systemk.thefactor2.Service.Impl;


import com.systemk.thefactor2.Mapper.TfBrandMapper;
import com.systemk.thefactor2.Service.BrandService;
import com.systemk.thefactor2.VO.TfBrandVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public String codeToNm(String brandCd) {
		TfBrandVO vo = tfBrandMapper.findBrand(brandCd);
		return vo.getBrandNm();
	}

	@Override
	public Map detailSearch(String brandCd){
		Map map = tfBrandMapper.detailSearch(brandCd);
		map.put("brand", brandCd.substring(0,2)+"0000");
		map.put("gender", brandCd.substring(0,4)+"00");
		map.put("cls",brandCd);
		return map;
	}

}
