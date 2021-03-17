package com.systemk.thefactor2.Service.Impl;


import com.systemk.thefactor2.Mapper.TfBrandMapper;
import com.systemk.thefactor2.Service.BrandService;
import com.systemk.thefactor2.VO.TfBrandVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
}
