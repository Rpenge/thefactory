package com.systemk.thefactor2.Service.Impl;


import com.systemk.thefactor2.Mapper.TfAcStockMapper;
import com.systemk.thefactor2.Mapper.TfProductMapper;
import com.systemk.thefactor2.Service.AcStockService;
import com.systemk.thefactor2.Service.BrandService;
import com.systemk.thefactor2.VO.TfAcStockVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class AcStockServiceImpl implements AcStockService {

	@Autowired
	private TfAcStockMapper tfAcStockMapper;

	@Autowired
	private TfProductMapper tfProductMapper;

	@Autowired
	private BrandService brandService;


	@Override
	public Map<String, Object> findStock(Map param) throws Exception {
		Map map = new HashMap();
		TfAcStockVO vo = tfAcStockMapper.findStockByTagId((String)param.get("tagId"));
		if(vo != null){
			map.put("barcode", vo.getTfPrdBarcode());
			Map mapData = tfProductMapper.prdAndStk(map);
			Map brandInfo = brandService.detailSearch((String)mapData.get("BRAND_KIND_CD"));
			map.put("btPrdBarcode",mapData.get("TF_PRD_BARCODE"));
			map.put("tfPrdTagid", param.get("tagId"));
			map.put("outStoreCd", vo.getStoreCd());
			map.put("prdSize",mapData.get("PRD_SIZE"));
			map.put("ecPrdCd",mapData.get("EC_PRD_CD"));
			map.put("tfPrdCd",mapData.get("TF_PRD_CD"));
			map.putAll(brandInfo);
		}
		return map;
	}


}
