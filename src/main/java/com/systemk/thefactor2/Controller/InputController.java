package com.systemk.thefactor2.Controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.systemk.thefactor2.Service.InputService;
import com.systemk.thefactor2.Util.RequestUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/input")
public class InputController {

	@Autowired
	private InputService inputService;

	//입출고관리 - 입고관리 : 리스트 조회(사이즈, 분류, 검색, 페이지 처리)
	@RequestMapping(value="/inputList", method = RequestMethod.GET)
	public Map<String, Object> inputList(HttpServletRequest request) throws Exception{
		Map param = RequestUtil.reqParamToMap(request);
		return inputService.findList(param);
	}

	//입출고관리 - 입고관리 : 태그ID로 출고데이터 조회 (웹에서 반품입고, 점간이동 등록 데이터로 활용)
	@RequestMapping(value = "/inputAdd", method = RequestMethod.GET)
	public Map<String, Object> inputAdd(HttpServletRequest request) throws Exception {
		Map param = RequestUtil.reqParamToMap(request);
		return inputService.searchPrd(param);
	}

	//입출고관리 - 입고관리 : 반품입고, 점간이동 추가
	@RequestMapping(value = "/inputAddResult", method = RequestMethod.POST)
	public Map<String, Object> inputAddResult(@RequestBody(required = false) Map<String, Object> map, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		map.put("userId", session.getAttribute("userId"));
		map.put("deviceGub", "020103");	//장비 : PC
		return inputService.inputAddResult(map);
	}

	//입출고관리 - 입고관리 : 입고 삭제
	@RequestMapping(value = "/inputDelete", method = RequestMethod.POST)
	public Map<String, Object> inputDelete(@RequestBody(required = false) Map<String, Object> map, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		map.put("userId", session.getAttribute("userId"));
		return inputService.inputDelete(map);
	}


}
