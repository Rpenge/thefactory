package com.systemk.Controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.systemk.Service.InvenService;
import com.systemk.Util.RequestUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/inven")
public class InvenController {

    @Autowired
    private InvenService invenService;


    //실사현황 리스트 조회
    @RequestMapping(value="/invList", method = RequestMethod.GET)
    public Map<String, Object> invList(HttpServletRequest request) throws Exception{
        Map param = RequestUtil.reqParamToMap(request);
        return invenService.findInvStatus(param);
    }

    //실사상세내역 리스트 조회
    @RequestMapping(value="/invenList", method = RequestMethod.GET)
    public Map<String, Object> invenList(HttpServletRequest request) throws Exception{
        Map param = RequestUtil.reqParamToMap(request);
        return invenService.findInvList(param);
    }

    //실사상세내역 확정
    @RequestMapping(value="/invenUpdate", method = RequestMethod.POST)
    public Map<String, Object> invenUpdate(@RequestBody(required = false) Map<String, Object> map, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        map.put("modId", (String)session.getAttribute("userId"));
        map.put("deviceGub", "020103");
        return invenService.invenUpdate(map);
    }

    //실사상세내역(list 확정)
    @RequestMapping(value="/invenUpdateList", method = RequestMethod.POST)
    public Map<String, Object> invenUpdateList(@RequestBody(required = false) Map<String, Object> map, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        map.put("modId", (String)session.getAttribute("userId"));
        map.put("deviceGub", "020103");
        return invenService.invenUpdateList(map);
    }

    //실사삭제
    @RequestMapping(value="/invenDelete", method = RequestMethod.POST)
    public Map<String, Object> invenDelete(@RequestBody(required = false) Map<String, Object> map, HttpServletRequest request) throws Exception{
        return invenService.invenDelete(map);
    }



}
