package com.systemk.thefactor2.Controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.systemk.thefactor2.Service.InoutTotService;
import com.systemk.thefactor2.Util.RequestUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/inout")
public class InoutController {

    @Autowired
    private InoutTotService inoutTotService;


    //입출고관리 - 입출고내역조회 : 리스트 조회
    @RequestMapping(value="/inoutList", method = RequestMethod.GET)
    public Map<String, Object> inputList(HttpServletRequest request) throws Exception{
        Map param = RequestUtil.reqParamToMap(request);
        return inoutTotService.findList(param);
    }


    //입출고관리 - 입출고 부가 정보 조회
    @RequestMapping(value="/inoutSubList", method = RequestMethod.GET)
    public Map<String, Object> inoutSubList(HttpServletRequest request) throws Exception{
        Map param = RequestUtil.reqParamToMap(request);
        if(param.get("ST_OUT_TYPE") != null){
            return inoutTotService.findOutSubList(param);
        }
        return inoutTotService.findInSubList(param);
    }




}
