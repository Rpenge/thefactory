package com.systemk.thefactor2.Util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {


    public static Map reqParamToMap(HttpServletRequest request){
        Enumeration en = request.getParameterNames();
        Map resultMap = new HashMap();
        while(en.hasMoreElements()){
            String param = (String)en.nextElement();
            String value = request.getParameter(param);
            resultMap.put(param, value);
        }
        return resultMap;
    }


}
