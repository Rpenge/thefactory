package com.systemk.thefactor2.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.systemk.thefactor2.Mapper.TfDeviceHisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TfDeviceHisMapper tfDeviceHisMapper;

    private byte[] httpRequestBodyByteArray;
    private ByteArrayInputStream bis;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println(request.getRequestURL());
        System.out.println(request.getRemoteAddr());

        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("getRequestURL : " + request.getRequestURL());
        System.out.println("getQueryString : " + request.getQueryString());
        System.out.println("getMethod : " + request.getMethod());
        System.out.println("getRemoteAddr : " + request.getRemoteAddr());
        System.out.println("getRemoteHost : " + request.getRemoteHost());
        System.out.println("getRemotePort : " + request.getRemotePort());
        System.out.println("getRemoteUser : " + request.getRemoteUser());
        System.out.println("getCharacterEncoding : " + request.getCharacterEncoding());
//        Map map = new HashMap();
//        map.put("deviceIp", request.getRemoteAddr());
//        map.put("serialNo", request.getRemoteAddr());
//        map.put("modelNo", request.getRemoteAddr());
//        map.put("rcvSeq", request.getRemoteAddr());
//        map.put("deviceGub", request.getRemoteAddr());
//        map.put("rcvResult", request.getRemoteAddr());
//        map.put("rcvWorkGub", request.getRemoteAddr());
//        System.out.println(map);


//            ObjectMapper objectMapper = new ObjectMapper();
//
//
//            try {
//                this.httpRequestBodyByteArray = StreamUtils.copyToByteArray(request.getInputStream());
//                this.bis = new ByteArrayInputStream(httpRequestBodyByteArray);
//            } catch (IOException e) {
//                ex.printStackTrace();
//            }

        //tfDeviceHisMapper.deviceHisSave(map);

    }

    //스파이더거 확인용
//    public static Map<String, Object> makeLoggingRequestMap(final HttpServletRequest request) {
//        // request info
//        Map<String, Object> requestMap = new HashMap<>();
//        requestMap.put("requestUrl", request.getRequestURL().toString());
//        requestMap.put("queryString", request.getQueryString());
//        requestMap.put("method", request.getMethod());
//        requestMap.put("remoteAddr", request.getRemoteAddr());
//        requestMap.put("remoteHost", request.getRemoteHost());
//        requestMap.put("remotePort", request.getRemotePort());
//        requestMap.put("remoteUser", request.getRemoteUser());
//        requestMap.put("encoding", request.getCharacterEncoding());
//
//        // request header
//        Map<String, Object> requestHeaderMap = new HashMap<>();
//        Enumeration<String> requestHeaderNameList = request.getHeaderNames();
//        while(requestHeaderNameList.hasMoreElements()) {
//            String headerName = requestHeaderNameList.nextElement();
//            requestHeaderMap.put(headerName, request.getHeader(headerName));
//
//            String value = request.getHeader(headerName);
//
//            if(headerName.equals("user-agent")){
//                requestMap.put("userAgent", value);
//            }
//
//            if(headerName.equals("cookie")){
//                String[] str = StringUtils.split(value, "=");
//
//                if(str.length > 0) {
//                    requestMap.put("session", str[1]);
//                }
//            }
//
//        }
//
//        requestMap.put("header", StringUtil.convertJsonString(requestHeaderMap));
//
//        // request Body
//        try {
//            // 이부분 주목!!
//            Object requestBody = ((RequestWrapper) request).convertToObject();
//            requestMap.put("body", StringUtil.convertJsonString(requestBody));
//        } catch (IOException ex) {
//        }
//
//        return requestMap;
//    }



}
