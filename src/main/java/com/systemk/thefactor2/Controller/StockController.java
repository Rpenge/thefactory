package com.systemk.thefactor2.Controller;

import com.systemk.thefactor2.Service.AcStockService;
import com.systemk.thefactor2.Service.StockService;
import com.systemk.thefactor2.Util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

		Map<String, Object> map = new HashMap<>();
		Map param = RequestUtil.reqParamToMap(request);
		if(param.get("ex")!=null && param.get("ex").equals("dis")){				//수량차이 발생한 데이터 조회
			map = stockService.findExList(param);
		}else if(param.get("ex")!=null && param.get("ex").equals("rfid")){		//RFID 재고 보유한 데이터 조회
			map = stockService.findRfidList(param);
		}else if(param.get("BRAND_KIND_CD")!=null){		//일반조회(검색속도 향상을 위해 브랜드검색 분리)
			map = stockService.findListSearch(param);
		}else{											//일반조회
			map = stockService.findList(param);
		}
		return map;
	}

	//태그재고조회 리스트
	@RequestMapping(value="/stkTagList", method = RequestMethod.GET)
	public Map<String, Object> stkTagList(HttpServletRequest request) throws Exception{
		Map param = RequestUtil.reqParamToMap(request);
		return acStockService.findAcStock(param);
	}

	//입출고관리 - 재고조회
	@RequestMapping(value="/stkSearch", method = RequestMethod.GET)
	public Map<String, Object> stkSearch(HttpServletRequest request) throws Exception{
		Map param = RequestUtil.reqParamToMap(request);
		return acStockService.findStock(param);
	}

}
