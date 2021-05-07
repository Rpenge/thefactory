package com.systemk.thefactor2.Controller;

import com.systemk.thefactor2.Mapper.TfInoutTotalMapper;
import com.systemk.thefactor2.Service.InoutTotService;
import com.systemk.thefactor2.Util.RequestUtil;
import com.systemk.thefactor2.VO.TfInoutTotalVO;
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
@RequestMapping("/home")
public class HomeController {


	@Autowired
	private InoutTotService inoutTotService;

	//HOME 화면 입출고 수량 조회
	@RequestMapping(value="/homeInfo", method = RequestMethod.GET)
	public Map<String, Object> homeInfo(HttpServletRequest request) throws Exception{
		Map resultMap = new HashMap();
		Map param = RequestUtil.reqParamToMap(request);
		resultMap.put("todayData",inoutTotService.todayWork(param));
		resultMap.put("monthData",inoutTotService.monthWork());
		return resultMap;
	}

	//HOME 화면 입출고 수량 당일 데이터 조회
	@RequestMapping(value="/homeSimple", method = RequestMethod.GET)
	public Map<String, Object> homeSimple(HttpServletRequest request) throws Exception{
		Map resultMap = new HashMap();
		Map param = RequestUtil.reqParamToMap(request);
		resultMap.put("todayData",inoutTotService.todayWork(param));
		return resultMap;
	}



}
