package com.systemk.thefactor2.Controller.Api;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.systemk.thefactor2.Service.ApiService;
import com.systemk.thefactor2.Service.InoutTotService;
import com.systemk.thefactor2.Service.UserService;
import com.systemk.thefactor2.Util.RequestUtil;
import com.systemk.thefactor2.Util.ResultUtil;
import com.systemk.thefactor2.VO.TfInoutTotalVO;
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
    private ApiService apiService;



    // id,비밀번호, 버전,
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> MemberLogin(@RequestHeader("type") String deviceGub,
                                               HttpServletRequest request,
                                               @RequestBody(required = false) Map<String, Object> data) throws Exception {
        data.put("deviceGub", deviceGub);
        data.put("appDownUrl", request.getServerName()+":"+request.getServerPort());
        Map resultMap = apiService.pdaLogin(data);
        if(resultMap.get("resultCode").equals("S")){
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(60*10);
            session.setAttribute("PdaUserId", data.get("userId")); //세션 ID 저장
        }
        return resultMap;
    }


    //메인화면 - 작업 수량
    @RequestMapping(value = "/workCount", method = RequestMethod.GET)
    public Map<String, Object> workCount(HttpServletRequest request) throws Exception {
        Map param = RequestUtil.reqParamToMap(request);
        return apiService.workCount(param);
    }


    //입고 - 입고 리스트
    @RequestMapping(value = "/inputList", method = RequestMethod.GET)
    public Map<String, Object> inputList(HttpServletRequest request) throws Exception {
        Map param = RequestUtil.reqParamToMap(request);
        return apiService.inputList(param);
    }


    //입고 작업 - 바코드 리딩 후 매칭되는 상품 데이터 return
    @RequestMapping(value = "/inputAdd", method = RequestMethod.GET)
    public Map<String, Object> inputAdd(HttpServletRequest request) throws Exception {
        Map param = RequestUtil.reqParamToMap(request);
        return apiService.searchPrd(param);
    }

    //입고 작업 - 바코드 리딩 후 매칭되는 상품 데이터 return (신규 입고 확정 )
    @RequestMapping(value = "/inputAddResult", method = RequestMethod.GET)
    public Map<String, Object> inputAddResult(HttpServletRequest request) throws Exception {
        Map param = RequestUtil.reqParamToMap(request);
        param.put("userId",  request.getHeader("id"));
        param.put("deviceGub",request.getHeader("type"));
        return apiService.inputWork(param);
    }



    //판매 - 판매 리스트
    @RequestMapping(value = "/outputList", method = RequestMethod.GET)
    public Map<String, Object> outputList(HttpServletRequest request) throws Exception {
        Map param = RequestUtil.reqParamToMap(request);
        return apiService.inputList(param);
    }


    //출고 - 태그로 출고시킬 데이터 조회
    @RequestMapping(value = "/outDataSearch", method = RequestMethod.POST)
    public Map<String, Object> outDataSearch(@RequestBody(required = false) List<Map<String, String>> data, HttpServletRequest request) throws Exception {
        return apiService.outDataSearch(data);
    }






}
