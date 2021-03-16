package com.systemk.thefactor2.Controller.Api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.systemk.thefactor2.Service.UserService;
import com.systemk.thefactor2.Util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;


@RestController
@RequestMapping("/api")
public class ApiCommonController {

    @Autowired
    private UserService userService;


    // id,비밀번호, 버전,
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> restMemberLogin(@RequestHeader("type") String deviceGub,
                                               HttpServletRequest request,
                                               @RequestBody(required = false) Map<String, Object> data) throws Exception {
        data.put("deviceGub", deviceGub);
        data.put("appDownUrl", request.getServerName()+":"+request.getServerPort());
        Map resultMap = userService.PdaLogin(data);
        if(resultMap.get("resultCode").equals("S")){
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(60*10);
            session.setAttribute("PdaUserId", data.get("userId")); //세션 ID 저장
        }
        return resultMap;
    }


}
