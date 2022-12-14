package com.systemk.Controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.systemk.Security.LoginUser;
import com.systemk.Service.UserService;
import com.systemk.Util.RequestUtil;


@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private UserService userService;


    //시스템관리 - 사용자 및 권한 관리 : 리스트 조회(사이즈, 분류, 검색, 페이지 처리)
    @RequestMapping(value="/userList", method = RequestMethod.GET)
    public Map<String, Object> userList(@AuthenticationPrincipal LoginUser user, HttpServletRequest request) throws Exception{

        Map param = RequestUtil.reqParamToMap(request);
        if(!user.getRole().equals("010101")){
            param.put("role","010101");
        }
        return userService.findList(param);
    }

    //시스템관리 - 사용자 및 권한 관리 : 사용자 추가
    @RequestMapping(value="/userSave", method = RequestMethod.POST)
    public Map userSave(@RequestBody(required = false) Map<String, String> map) throws Exception{
        return userService.userSave(map);
    }

    //시스템관리 - 사용자 및 권한 관리 : 비밀번호 변경
    @RequestMapping(value="/userPwUpdate", method = RequestMethod.POST)
    public Map userPwUpdate(@RequestBody(required = false) Map<String, String> map) throws Exception{
        return userService.userPwUpdate(map);
    }

    //시스템관리 - 사용자 및 권한 관리 : 사용자 정보 수정
    @RequestMapping(value="/userUpdate", method = RequestMethod.POST)
    public Map userUpdate(@RequestBody(required = false) Map<String, String> map) throws Exception{
        return userService.userUpdate(map);
    }

    //시스템관리 - 사용자 및 권한 관리 : 사용자 탈퇴
    @RequestMapping(value="/userWd", method = RequestMethod.POST)
    public Map userWd(@RequestBody(required = false) Map<String, Object> map) throws Exception{
        List<Integer> list = (List)map.get("prList");
        return userService.userWd(list);
    }

}
