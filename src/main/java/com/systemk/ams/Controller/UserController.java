package com.systemk.ams.Controller;

import java.security.Principal;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.systemk.ams.Security.LoginUser;
import com.systemk.ams.mapper.MenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import com.systemk.ams.Entity.Main.UserInfo;
//import com.systemk.ams.Entity.mapping.memberMapping;
//import com.systemk.ams.Service.MemberService;

@Slf4j
@RestController
@RequestMapping("/member")
public class UserController {

//	@Autowired
//	private MemberService memberService;

	@Autowired
	private MenuMapper menuMapper;

	@RequestMapping("/userAuth")
	public Map user(@AuthenticationPrincipal LoginUser user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap sessionMap = new HashMap<>();

		if(user != null) {
			session.setAttribute("userId", user.getName());
			session.setAttribute("role", user.getRole());
			sessionMap.put("userId", user.getName());
			sessionMap.put("role", user.getRole());
			sessionMap.put("menu", menuSort(menuMapper.menuSearch(sessionMap), "AMS"));
		}

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

//	//사용자 리스트 조회
//	@RequestMapping(method = RequestMethod.GET)
//	public Page<memberMapping> memberList(@PageableDefault(sort = {"userId"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable) throws Exception {
//		return memberService.memberList(pageable);
//	}
//
//	//사용자 조회
//	@RequestMapping(value="/userInfo", method = RequestMethod.GET)
//	public memberMapping userInfo(Principal user) throws Exception {
//		return memberService.userInfo(user.getName());
//	}

//	//유저 등록
//	@RequestMapping(method = RequestMethod.POST)
//	public void userReg(@RequestBody(required = false) UserInfo user) throws Exception {
//		memberService.memberReg(user);
//	}
//
//	//권한 업데이트
//	@RequestMapping(value="/roleUpdate", method = RequestMethod.PUT)
//	public void roleUpdate(@RequestBody UserInfo user) throws Exception {
//		memberService.userUpdate(user);
//	}



	//메뉴 정리
	public HashMap<String, Object> menuSort(List<HashMap<String, Object>> menu, String parent){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List list = new ArrayList();
		for (HashMap<String, Object> item : menu) {
			if(item.get("parent_code").equals(parent)){
				list.add(item);
				if(item.get("menu_code").toString().length()!=2) {
					HashMap<String, Object> reData = menuSort(menu, (String) item.get("menu_code"));
					item.put("child", reData);
				}
			}
		}
		result.put("parent", list);
		return result;
	}
}
