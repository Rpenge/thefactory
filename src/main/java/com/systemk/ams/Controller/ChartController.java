package com.systemk.ams.Controller;




import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.systemk.ams.Service.ChartService;



@RestController
public class ChartController {
	
	@Autowired
	private ChartService chartService;
	
	@RequestMapping(value = "/homeChart", method = RequestMethod.GET)
	public Map<String, Object> homeChart() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("div" , chartService.divCount());
		map.put("status" , chartService.statusCount());
		map.put("count", chartService.countNewOrder());
		return map;
		
	}
}
