package com.systemk.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.systemk.Mapper.TfUserAuthMapper;
import com.systemk.Security.LoginUser;

@RestController
@RequestMapping("/member")
public class UserController {
	
	@Autowired
	private TfUserAuthMapper tfUserAuthMapper;
	
	
	
	@RequestMapping("/userAuth")
	public Map user(@AuthenticationPrincipal LoginUser user, HttpServletRequest request) throws Exception {
		System.out.println("체크메이트 ");
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60*60*24*7);
		HashMap resultMap = new HashMap<>();

		if(user != null) {
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("role", user.getRole());
			session.setAttribute("storeCd", user.getStoreCd());
			resultMap.put("userId", user.getUserId());
			resultMap.put("role", user.getRole());
			resultMap.put("storeCd", user.getStoreCd());
			
			List<HashMap> authList = tfUserAuthMapper.authSearch(resultMap);
			resultMap.put("auth", authList);
		}
	    return resultMap;
	}

	//새로고침 : 메뉴 + 권한 다시 조회
	@RequestMapping("/reUserAuth")
	public Map user(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60*60*24*7); //60분x60초x24시간
		HashMap resultMap = new HashMap<>();

		resultMap.put("userId", session.getAttribute("userId"));
		resultMap.put("role", session.getAttribute("role"));
		resultMap.put("storeCd", session.getAttribute("storeCd"));

		List<HashMap> AuthList = tfUserAuthMapper.authSearch(resultMap);
		resultMap.put("auth", AuthList);

		//resultMap.put("commCode", commService.findList());

		return resultMap;
	}

}