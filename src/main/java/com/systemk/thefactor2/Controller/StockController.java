package com.systemk.thefactor2.Controller;

import com.systemk.thefactor2.Service.AcStockService;
import com.systemk.thefactor2.Service.StockService;
import com.systemk.thefactor2.Util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/stock")
public class StockController {



	@Autowired
	private AcStockService acStockService;

	@Autowired
	private StockService stockService;


	//재고현황 - 재고현황관리 :  리스트조회
	@RequestMapping(value="/stockList", method = RequestMethod.GET)
	public Map<String, Object> stockList(HttpServletRequest request) throws Exception{
		Map param = RequestUtil.reqParamToMap(request);
		return stockService.stkGubSearch(param);
	}

	//입출고관리 - 재고조회
	@RequestMapping(value="/stkSearch", method = RequestMethod.GET)
	public Map<String, Object> stkSearch(HttpServletRequest request) throws Exception{
		Map param = RequestUtil.reqParamToMap(request);
		return acStockService.findStock(param);
	}


	//입출고관리 - 재고조회 : 일자/매장/구분/상품별 수량
	@RequestMapping(value="/stkGubSearch", method = RequestMethod.GET)
	public Map<String, Object> stkGubSearch(HttpServletRequest request) throws Exception{
		Map param = RequestUtil.reqParamToMap(request);
		return stockService.stkGubSearch(param);
	}









}
