//package com.systemk.thefactor2.Config;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.util.StreamUtils;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.ReadListener;
//import javax.servlet.ServletInputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//
//public class LoggerInterceptor extends HandlerInterceptorAdapter {
//
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//        System.out.println("getRequestURL : " + request.getRequestURL());
//        System.out.println("getQueryString : " + request.getQueryString());
//        System.out.println("getMethod : " + request.getMethod());
//        System.out.println("getRemoteAddr : " + request.getRemoteAddr());
//        System.out.println("getRemoteHost : " + request.getRemoteHost());
//        System.out.println("getRemotePort : " + request.getRemotePort());
//        System.out.println("getCharacterEncoding : " + request.getCharacterEncoding());
////        Map map = new HashMap();
////        map.put("deviceIp", request.getRemoteAddr());
////        map.put("serialNo", request.getRemoteAddr());
////        map.put("modelNo", request.getRemoteAddr());
////        map.put("rcvSeq", request.getRemoteAddr());
////        map.put("deviceGub", request.getRemoteAddr());
////        map.put("rcvResult", request.getRemoteAddr());
////        map.put("rcvWorkGub", request.getRemoteAddr());
////        System.out.println(map);
//
////        try {
////            // 이부분 주목!!
////            Object requestBody = ((RequestWrapper) request).convertToObject();
////            requestMap.put("body", StringUtil.convertJsonString(requestBody));
////        } catch (IOException e) {
////        }
//        //tfDeviceHisMapper.deviceHisSave(map);
//
////        헤더확인
////        Map<String, Object> requestMap = new HashMap<>();
////        Map<String, Object> requestHeaderMap = new HashMap<>();
////        Enumeration<String> requestHeaderNameList = request.getHeaderNames();
////        while(requestHeaderNameList.hasMoreElements()) {
////            String headerName = requestHeaderNameList.nextElement();
////            requestHeaderMap.put(headerName, request.getHeader(headerName));
////            String value = request.getHeader(headerName);
////            if(headerName.equals("user-agent")){
////                requestMap.put("userAgent", value);
////            }
////            if(headerName.equals("cookie")){
////                String[] str = StringUtils.split(value, "=");
////
////                if(str.length > 0) {
////                    requestMap.put("session", str[1]);
////                }
////            }
////        }
////        requestMap.put("header", StringUtil.convertJsonString(requestHeaderMap));
//
//
//    }
//
//}
