package com.systemk.thefactor2.Controller;

import com.systemk.thefactor2.Service.UserService;
import com.systemk.thefactor2.Util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/system")
public class SystemController {

	@Autowired
	private UserService userService;



	//시스템관리 - 사용자 및 권한 관리 : 리스트 조회(사이즈, 분류, 검색, 페이지 처리)
	@RequestMapping(value="/userList", method = RequestMethod.GET)
	public Map<String, Object> userList(HttpServletRequest request) throws Exception{
		Map param = RequestUtil.reqParamToMap(request);
		return userService.findList(param);
	}



	//시스템관리 - 사용자 및 권한 관리 : 사용자 추가
	@RequestMapping(value="/userSave", method = RequestMethod.POST)
	public Map userSave(@RequestBody(required = false) Map<String, String> map) throws Exception{
		System.out.println(map);
		userService.userSave(map);
		return null;
	}






}
