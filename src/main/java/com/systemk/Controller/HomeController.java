package com.systemk.Controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.systemk.Service.InoutTotService;
import com.systemk.Util.RequestUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/home")
public class HomeController {


    @Autowired
    private InoutTotService inoutTotService;

    //HOME 화면 입출고 수량 조회
    @RequestMapping(value="/homeInfo", method = RequestMethod.GET)
    public Map<String, Object> homeInfo(HttpServletRequest request) throws Exception{
        Map resultMap = new HashMap();
        Map param = RequestUtil.reqParamToMap(request);
        resultMap.put("todayData",inoutTotService.todayWork(param));
        resultMap.put("monthData",inoutTotService.monthWork());
        return resultMap;
    }

    //HOME 화면 입출고 수량 당일 데이터 조회
    @RequestMapping(value="/homeSimple", method = RequestMethod.GET)
    public Map<String, Object> homeSimple(HttpServletRequest request) throws Exception{
        Map resultMap = new HashMap();
        Map param = RequestUtil.reqParamToMap(request);
        resultMap.put("todayData",inoutTotService.todayWork(param));
        return resultMap;
    }



}
