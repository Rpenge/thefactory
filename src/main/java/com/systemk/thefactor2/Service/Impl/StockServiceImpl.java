package com.systemk.thefactor2.Service.Impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.systemk.thefactor2.Mapper.PageMapper;
import com.systemk.thefactor2.Mapper.TfAcStockMapper;
import com.systemk.thefactor2.Mapper.TfProductMapper;
import com.systemk.thefactor2.Mapper.TfStockMapper;
import com.systemk.thefactor2.Service.AcStockService;
import com.systemk.thefactor2.Service.BrandService;
import com.systemk.thefactor2.Service.StockService;
import com.systemk.thefactor2.Util.MybatisUtil;
import com.systemk.thefactor2.Util.StringUtil;
import com.systemk.thefactor2.VO.TfAcStockVO;
import com.systemk.thefactor2.VO.TfInputVO;
import com.systemk.thefactor2.VO.TfStockVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private BrandService brandService;


	@Autowired
	private TfStockMapper tfStockMapper;

	@Autowired
	private TfProductMapper tfProductMapper;

	@Autowired
	private PageMapper pageMapper;



	@Override
	public Map<String, Object> findList(Map param) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("TF_STOCK");
		if(param.get("STORE_CD")!=null){
			mu.addEqual("STOCK_STORE_CD", (String)param.get("STORE_CD"));
		}
		if(param.get("PRD_SIZE")!=null){
			mu.addEqual("PRD_SIZE", (String)param.get("PRD_SIZE"));
		}
		if (param.get("word")!=null) {
			mu.addLike("TF_PRD_NM", (String)param.get("word"));
			mu.addORLike("TF_PRD_CD", (String)param.get("word"));
		}

		mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch())); // 수량조회
		if(param.get("page")!=null)
			mu.setPage(Integer.parseInt((String)param.get("page")));
		if(param.get("size")!=null)
			mu.setSize(Integer.parseInt((String)param.get("size")));

		List<Map> ListMap =  tfStockMapper.stockList(mu.getTableSearch());
		List<Map> resultList = new ArrayList<Map>();
		for(Map map : ListMap){
			HashMap searchMap = new HashMap();
			searchMap.put("barcode", map.get("TF_PRD_BARCODE"));
			searchMap.put("storeCd", map.get("STOCK_STORE_CD"));
			map.putAll(tfStockMapper.workCnt(searchMap));
			if(map.get("BRAND_KIND_CD") != null) {
				Map brandInfo = brandService.detailSearch((String) map.get("BRAND_KIND_CD"));
				map.putAll(brandInfo);
			}
			resultList.add(map);
		}
		mu.setContent(resultList);
		return mu.getList();
	}

	@Override
	public Map<String, Object> findExList(Map param) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("TF_STOCK");

		mu.setTotalElements(pageMapper.stkExPageRecord(mu.getTableSearch())); // 수량조회
		if(param.get("page")!=null)
			mu.setPage(Integer.parseInt((String)param.get("page")));
		if(param.get("size")!=null)
			mu.setSize(Integer.parseInt((String)param.get("size")));

		List<Map> ListMap =  tfStockMapper.stockExList(mu.getTableSearch());
		List<Map> resultList = new ArrayList<Map>();
		for(Map map : ListMap){
			HashMap searchMap = new HashMap();
			searchMap.put("barcode", map.get("TF_PRD_BARCODE"));
			searchMap.put("storeCd", map.get("STOCK_STORE_CD"));
			map.putAll(tfStockMapper.workCnt(searchMap));
			if(map.get("BRAND_KIND_CD") != null) {
				Map brandInfo = brandService.detailSearch((String) map.get("BRAND_KIND_CD"));
				map.putAll(brandInfo);
			}
			resultList.add(map);
		}
		mu.setContent(resultList);
		return mu.getList();
	}

	@Override
	public Map<String, Object> findListSearch(Map param) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("TF_STOCK");
		if(param.get("BRAND_KIND_CD")!=null){
			mu.addStr("BRAND_KIND_CD", (String)param.get("BRAND_KIND_CD"));
		}
		if(param.get("PRD_SIZE")!=null){
			mu.addStr("PRD_SIZE", (String)param.get("PRD_SIZE"));
		}
		if(param.get("STORE_CD")!=null){
			mu.addStr("STORE_CD", (String)param.get("STORE_CD"));
		}

		mu.setTotalElements(pageMapper.stkPageRecord(mu.getTableSearch())); // 수량조회
		if(param.get("page")!=null)
			mu.setPage(Integer.parseInt((String)param.get("page")));
		if(param.get("size")!=null)
			mu.setSize(Integer.parseInt((String)param.get("size")));

		List<Map> ListMap =  tfStockMapper.stockListSearch(mu.getTableSearch());
		List<Map> listMap = new ArrayList<Map>();
		for(Map map : ListMap){
			HashMap searchMap = new HashMap();
			searchMap.put("barcode", map.get("TF_PRD_BARCODE"));
			searchMap.put("storeCd", map.get("STOCK_STORE_CD"));
			map.putAll(tfStockMapper.workCnt(searchMap));

			Map brandInfo = brandService.detailSearch((String)map.get("BRAND_KIND_CD"));
			map.putAll(brandInfo);
			listMap.add(map);
		}
		mu.setContent(listMap);
		return mu.getList();
	}

	@Override
	public Map<String, Object> stkGubSearch(Map param) throws Exception {
		return null;
	}
}