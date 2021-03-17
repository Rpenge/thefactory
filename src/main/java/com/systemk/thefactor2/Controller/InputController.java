package com.systemk.thefactor2.Controller;

import com.systemk.thefactor2.Service.InputService;
import com.systemk.thefactor2.Service.UserService;
import com.systemk.thefactor2.Util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/inout")
public class InputController {


	@Autowired
	private InputService inputService;


	//입출고관리 - 입고관리 : 리스트 조회(사이즈, 분류, 검색, 페이지 처리)
	@RequestMapping(value="/inputList", method = RequestMethod.GET)
	public Map<String, Object> inputList(HttpServletRequest request) throws Exception{
		Map param = RequestUtil.reqParamToMap(request);

		return inputService.findList(param);
	}


}
