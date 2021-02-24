package com.systemk.ams.Service.Impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemk.ams.Entity.Main.CommonCode;
import com.systemk.ams.Repository.Main.AssetChangeRepository;
import com.systemk.ams.Repository.Main.AssetManagementRepository;
import com.systemk.ams.Repository.Main.CommonCodeRepository;
import com.systemk.ams.Service.ChartService;


@Service
public class ChartServiceImpl implements ChartService{

	@Autowired
	private CommonCodeRepository commonCodeRepository;
	
	@Autowired
	private AssetManagementRepository assetManagementRepository;
	
	@Autowired
	private AssetChangeRepository assetChangeRepository;
	
	//자산 종류 별 수량
	@Override
	public List divCount() throws Exception {
		return assetManagementRepository.countSelect();
	}

	//자산 상태 별 수량
	@Override
	public Map<String, Integer> statusCount() throws Exception {
		List<CommonCode> list = commonCodeRepository.findByParentCode("AST");
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(int i=0;i<list.size();i++) {
			String code = list.get(i).getCode();
			map.put(list.get(i).getCodeName(), assetManagementRepository.countByAssetStatus(code));
		}
		return map;
	}

	@Override
	public Map<String, Integer> countNewOrder() throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Date today = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = format.format(today);
		Date todayInit = format.parse(strDate);
		Date MonthInit = format.parse(strDate.substring(0, 7)+"-01");
		
		int dayCount = assetManagementRepository.countByAssetRegDateBetween(todayInit, today);
		int monthCount = assetManagementRepository.countByAssetRegDateBetween(MonthInit, today);
		
		int dayCountRfid = assetChangeRepository.countByUpdateYnAndWkEnvAndChgDtBetween("Y", "PDA", todayInit, today);
		map.put("dayAsset", dayCount);
		map.put("monthAsset", monthCount);
		map.put("dayRfid", dayCountRfid);
		
		return map;
	}


}
