package com.systemk.thefactor2.Service.Impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.systemk.thefactor2.Mapper.PageMapper;
import com.systemk.thefactor2.Mapper.TfAcStockMapper;
import com.systemk.thefactor2.Mapper.TfOutputMapper;
import com.systemk.thefactor2.Service.AcStockService;
import com.systemk.thefactor2.Service.BrandService;
import com.systemk.thefactor2.Service.OutputService;
import com.systemk.thefactor2.Util.MybatisUtil;
import com.systemk.thefactor2.Util.StringUtil;
import com.systemk.thefactor2.VO.TfAcStockVO;
import com.systemk.thefactor2.VO.TfOutputVO;
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


	@Override
	public Map<String, Object> findStock(Map param) throws Exception {
		Map map = new HashMap();
		TfAcStockVO vo = tfAcStockMapper.findStockByTagId((String)param.get("tagId"));

		return map;
	}
}
