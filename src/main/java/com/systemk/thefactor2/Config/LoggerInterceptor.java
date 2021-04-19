package com.systemk.thefactor2.Config;

import com.systemk.thefactor2.Mapper.TfDeviceHisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

//    @Autowired
//    private TfDeviceHisMapper tfDeviceHisMapper;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        System.out.println(request.getRequestURL());
//        System.out.println(request.getRemoteAddr());
//
//        return super.preHandle(request, response, handler);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("after" + request.getRequestURL());
//        Map map = new HashMap();
//        map.put("deviceIp", request.getRemoteAddr());
//        map.put("serialNo", request.getRemoteAddr());
//        map.put("modelNo", request.getRemoteAddr());
//        map.put("rcvSeq", request.getRemoteAddr());
//        map.put("deviceGub", request.getRemoteAddr());
//        map.put("rcvResult", request.getRemoteAddr());
//        map.put("rcvWorkGub", request.getRemoteAddr());
//        System.out.println(map);
//        tfDeviceHisMapper.deviceHisSave(map);
//
//    }


}
