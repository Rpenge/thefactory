package com.systemk.thefactor2.Controller.Api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.systemk.thefactor2.Config.ConstansConfig;
import com.systemk.thefactor2.Service.ApiService;
import com.systemk.thefactor2.Util.RequestUtil;
import com.systemk.thefactor2.Util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class ApiCommonController {

    @Autowired
    private ApiService apiService;

    // id, 비밀번호, 버전 확인
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> MemberLogin(@RequestHeader("type") String deviceGub,
                                               HttpServletRequest request,
                                               @RequestBody(required = false) Map<String, Object> data) throws Exception {
        data.put("deviceGub", deviceGub);
        data.put("appDownUrl", request.getServerName()+":"+request.getServerPort());
        Map resultMap = apiService.pdaLogin(data);
        if(resultMap.get("resultCode").equals("S")){
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(60*60);
            session.setAttribute("PdaUserId", data.get("userId")); //세션 ID 저장
        }
        return resultMap;
    }


    //메인화면(조회) - 작업 수량
    @RequestMapping(value = "/workCount", method = RequestMethod.GET)
    public Map<String, Object> workCount(HttpServletRequest request) throws Exception {
        Map param = RequestUtil.reqParamToMap(request);
        return apiService.workCount(param);
    }


    //입고(조회) - 입고 리스트
    @RequestMapping(value = "/inputList", method = RequestMethod.GET)
    public Map<String, Object> inputList(HttpServletRequest request) throws Exception {
        Map param = RequestUtil.reqParamToMap(request);
        return apiService.inputList(param);
    }

    //입고 작업(조회/저장) - 바코드 리딩 후 매칭되는 상품 데이터 return
    @RequestMapping(value = "/inputAdd", method = RequestMethod.GET)
    public Map<String, Object> inputAdd(HttpServletRequest request) throws Exception {
        Map param = RequestUtil.reqParamToMap(request);
        return apiService.searchPrd(param);
    }

    //입고 작업(저장) - 입고 확정
    @RequestMapping(value = "/inputAddResult", method = RequestMethod.GET)
    public Map<String, Object> inputAddResult(HttpServletRequest request) throws Exception {
        Map param = RequestUtil.reqParamToMap(request);
        param.put("userId", request.getHeader("id"));
        param.put("deviceGub", request.getHeader("type"));
        if(param.get("state").equals("060101") || param.get("state").equals("060103")){   // 신규입고, 입고
            return apiService.inputWork(param);
        }else{
            return ResultUtil.setCommonResult("E", ConstansConfig.PDA_STATE_DATA_NONE_MSG);
        }
    }

    //반품입고 - 리스트 처리
    @RequestMapping(value = "/inputAddList", method = RequestMethod.POST)
    public Map<String, Object> inputAddList(@RequestBody(required = false) List<Map> param, HttpServletRequest request) {
        Map map = new HashMap();
        map.put("userId",request.getHeader("id"));
        map.put("deviceGub", request.getHeader("type"));
        try {
            return apiService.inputReWorkList(param, map);
        }catch (Exception e){
            return ResultUtil.setCommonResult("E", e.getMessage());
        }
    }


    //반품입고 할 데이터 조회 (판매 데이터에서 조회)
    @RequestMapping(value = "/saleDataSearch", method = RequestMethod.POST)
    public Map<String, Object> saleDataSearch(@RequestBody(required = false) List<Map<String, String>> data) throws Exception {
        return apiService.saleDataSearch(data);
    }

    //반품입고 할 데이터 조회 (점간출고 데이터에서 조회)
    @RequestMapping(value = "/moveOutDataSearch", method = RequestMethod.POST)
    public Map<String, Object> moveOutDataSearch(@RequestBody(required = false) List<Map<String, String>> data) throws Exception {
        return apiService.moveOutDataSearch(data);
    }

    //판매/출고(조회) - 판매 리스트
    @RequestMapping(value = "/outputList", method = RequestMethod.GET)
    public Map<String, Object> outputList(HttpServletRequest request) throws Exception {
        Map param = RequestUtil.reqParamToMap(request);
        return apiService.outputList(param);
    }


    //출고(조회) - 태그로 출고시킬 데이터 조회
    @RequestMapping(value = "/outDataSearch", method = RequestMethod.POST)
    public Map<String, Object> outDataSearch(@RequestBody(required = false) List<Map<String, String>> data, HttpServletRequest request) throws Exception {
        return apiService.outDataSearch(data);
    }


    //출고등록 - 출고, 점간출고, 판매, 온라인판매
    @RequestMapping(value = "/outputAdd", method = RequestMethod.POST)
    public Map<String, Object> outputAdd(@RequestBody(required = false) List<Map<String, String>> data, HttpServletRequest request) throws Exception {
        return apiService.outputAdd(data, request.getHeader("id"), "020102");
    }


    //재고실사정보 - 매장별 현재고 목록
    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    public Map<String, Object> stock(HttpServletRequest request) throws Exception {
        Map param = RequestUtil.reqParamToMap(request);
        return apiService.findAcStkList(param);
    }

    //재고실사 - 실사목록 받아서 생성&업데이트
    @RequestMapping(value = "/inventory", method = RequestMethod.POST)
    public Map<String, Object> inventory(@RequestBody(required = false) Map data, HttpServletRequest request) throws Exception {
        data.put("deviceGub", request.getHeader("type"));
        data.put("regId", request.getHeader("id"));
        return apiService.addInven(data);
    }

    //찾기 - barcode / tagId로 조회
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public Map<String, Object> find(HttpServletRequest request) throws Exception {
        Map param = RequestUtil.reqParamToMap(request);
        return apiService.findStock(param);
    }

    //공통코드 - 공통코드 전송
    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public Map<String, Object> code(HttpServletRequest request) throws Exception {
        Map param = RequestUtil.reqParamToMap(request);
        return apiService.commonCd(param);
    }

    //공통코드 - 공통코드 전송
    @RequestMapping(value = "/code/brand", method = RequestMethod.GET)
    public Map<String, Object> brand(HttpServletRequest request) throws Exception {
        Map param = RequestUtil.reqParamToMap(request);
        return apiService.brandCd(param);
    }



}
