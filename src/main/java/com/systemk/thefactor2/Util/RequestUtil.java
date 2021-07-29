package com.systemk.thefactor2.Util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {


    public static Map reqParamToMap(HttpServletRequest request){
        Enumeration<String> em = request.getParameterNames();
        Map<String,String> resultMap = new HashMap<String,String>();
        while(em.hasMoreElements()){
            String param = em.nextElement();
            String value = request.getParameter(param);
            resultMap.put(param, value);
        }
        return resultMap;
    }


}
