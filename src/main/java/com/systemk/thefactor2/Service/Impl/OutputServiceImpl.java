package com.systemk.thefactor2.Service.Impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.systemk.thefactor2.Mapper.*;
import com.systemk.thefactor2.Service.BrandService;
import com.systemk.thefactor2.Service.InputService;
import com.systemk.thefactor2.Service.OutputService;
import com.systemk.thefactor2.Util.MybatisUtil;
import com.systemk.thefactor2.VO.TfInputVO;
import com.systemk.thefactor2.VO.TfOutputVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OutputServiceImpl implements OutputService {

	@Autowired
	private TfOutputMapper tfOutputMapper;

	@Autowired
	private BrandService brandService;

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
}
