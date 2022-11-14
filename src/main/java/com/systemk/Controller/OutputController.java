package com.systemk.Controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.systemk.Service.OutputService;
import com.systemk.Util.RequestUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    private OutputService outputService;


    //입출고관리 - 출고관리 : 리스트 조회(사이즈, 분류, 검색, 페이지 처리)
    @RequestMapping(value="/outList", method = RequestMethod.GET)
    public Map<String, Object> outList(HttpServletRequest request) throws Exception{
        Map param = RequestUtil.reqParamToMap(request);
        return outputService.findList(param);
    }

    //입출고관리 - 판매관리 : 리스트 조회(사이즈, 분류, 검색, 페이지 처리)
    @RequestMapping(value="/salesList", method = RequestMethod.GET)
    public Map<String, Object> salesList(HttpServletRequest request) throws Exception{
        Map param = RequestUtil.reqParamToMap(request);
        return outputService.findSalseList(param);
    }

    //입출고관리 - 출고관리 : 출고, 점간이동출고 추가
    @RequestMapping(value = "/outputAdd", method = RequestMethod.POST)
    public Map<String, Object> outputAdd(@RequestBody(required = false) Map<String, Object> map, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        map.put("userId", session.getAttribute("userId"));
        map.put("deviceGub", "020103");
        return outputService.outputAdd(map);
    }

    //입출고관리 - 출고관리 : 출고 삭제
    @RequestMapping(value = "/outputDelete", method = RequestMethod.POST)
    public Map<String, Object> inputDelete(@RequestBody(required = false) Map<String, Object> map, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        map.put("userId", session.getAttribute("userId"));
        return outputService.outputDelete(map);
    }

}
