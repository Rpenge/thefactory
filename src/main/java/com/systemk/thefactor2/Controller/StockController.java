package com.systemk.thefactor2.Controller;

import com.systemk.thefactor2.Service.AcStockService;
import com.systemk.thefactor2.Service.OutputService;
import com.systemk.thefactor2.Util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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



	//입출고관리 - 재고조회
	@RequestMapping(value="/stkSearch", method = RequestMethod.GET)
	public Map<String, Object> stkSearch(HttpServletRequest request) throws Exception{
		Map param = RequestUtil.reqParamToMap(request);
		return acStockService.findStock(param);
	}






	// 출고 : **(판매/ 배송은 별도) 출고, 점간출고,  삭제가능, 신규 출고?,  태그 id를 받아서 출고 시킴, 입고예정매장


}
