package com.systemk.thefactor2.Controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.systemk.thefactor2.Security.LoginUser;

import com.systemk.thefactor2.mapper.TfUserAuthMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/member")
public class UserController {


	@Autowired
	private TfUserAuthMapper tfUserAuthMapper;

	@RequestMapping("/userAuth")
	public Map user(@AuthenticationPrincipal LoginUser user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap sessionMap = new HashMap<>();

		if(user != null) {
			session.setAttribute("userId", user.getName());
			session.setAttribute("role", user.getRole());
			sessionMap.put("userId", user.getName());
			sessionMap.put("role", user.getRole());


			List<HashMap> AuthList = tfUserAuthMapper.authSearch(sessionMap);
			sessionMap.put("auth", AuthList);

		}

	    return sessionMap;
	}

	//새로고침 : 메뉴 + 권한 다시 조회
	@RequestMapping("/reUserAuth")
	public Map user(HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap sessionMap = new HashMap<>();

		sessionMap.put("userId", session.getAttribute("userId"));
		sessionMap.put("role", session.getAttribute("role"));

		List<HashMap> AuthList = tfUserAuthMapper.authSearch(sessionMap);
		sessionMap.put("auth", AuthList);


		//유저 권한과 메뉴들을 프론트로 보낸다. usr_auth의 pgm cd와 pgm의 pgm cd를 조인

		return sessionMap;
	}


	@RequestMapping(value="/logout", method = RequestMethod.POST)
    public ResponseEntity<String> logoutPage (HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean success = false;
		JSONObject obj = new JSONObject();
		request.getSession().invalidate();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
            success = true;
        }
        obj.put("result", success ? "success" : "false");
        return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);
    }



}
