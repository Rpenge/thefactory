package com.systemk.thefactor2.Controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.systemk.thefactor2.Security.LoginUser;

import com.systemk.thefactor2.Service.BrandService;
import com.systemk.thefactor2.Service.CommService;
import com.systemk.thefactor2.Mapper.TfUserAuthMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/member")
public class UserController {


	@Autowired
	private TfUserAuthMapper tfUserAuthMapper;

	@Autowired
	private CommService commService;

	@Autowired
	private BrandService brandService;


	@RequestMapping("/userAuth")
	public Map user(@AuthenticationPrincipal LoginUser user, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(30*60);
		HashMap resultMap = new HashMap<>();

		if(user != null) {
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("role", user.getRole());
			session.setAttribute("storeCd", user.getStoreCd());
			resultMap.put("userId", user.getUserId());
			resultMap.put("role", user.getRole());
			resultMap.put("storeCd", user.getStoreCd());

			List<HashMap> AuthList = tfUserAuthMapper.authSearch(resultMap);
			resultMap.put("auth", AuthList);
		}
	    return resultMap;
	}

	//새로고침 : 메뉴 + 권한 다시 조회
	@RequestMapping("/reUserAuth")
	public Map user(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(30*60);
		HashMap resultMap = new HashMap<>();

		resultMap.put("userId", session.getAttribute("userId"));
		resultMap.put("role", session.getAttribute("role"));
		resultMap.put("storeCd", session.getAttribute("storeCd"));

		List<HashMap> AuthList = tfUserAuthMapper.authSearch(resultMap);
		resultMap.put("auth", AuthList);

		//resultMap.put("commCode", commService.findList());

		return resultMap;
	}

	// 브랜드 , 공통코드 불러오기
	@RequestMapping("/getCode")
	public Map getCode() throws Exception{
		HashMap resultMap = new HashMap<>();
		resultMap.put("commCode", commService.commList());
		resultMap.put("brandList", brandService.findBrand());
		return resultMap;
	}

	@RequestMapping("/getTotCode")
	public Map getTotCode() throws Exception{
		HashMap resultMap = new HashMap<>();
		resultMap.put("commCode", commService.commTotList());
		return resultMap;
	}

	@RequestMapping("/getTotBrand")
	public Map getTotBrand() throws Exception{
		HashMap resultMap = new HashMap<>();
		resultMap.put("brandList", brandService.findTotalBrand());
		return resultMap;
	}

	//브랜드 하위 정보 불러오기
	@RequestMapping(value = "/brandSub", method = RequestMethod.GET)
	public Map getBrandSub(HttpServletRequest request) throws Exception{
		HashMap resultMap = new HashMap<>();
		resultMap.put("brandSubList", brandService.findBrandSub(request.getParameter("brandCd")));
		return resultMap;
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
