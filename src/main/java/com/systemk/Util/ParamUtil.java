package com.systemk.Util;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ParamUtil {

    public Map<String, String> requestGetParam(HttpServletRequest request) {
        Map<String, String> search = new HashMap<String, String>();
        Enumeration<String> e = request.getParameterNames();
        while ( e.hasMoreElements()){
            String name = (String) e.nextElement();
            search.put(name, request.getParameter(name));
        }
        return search;
    }


    //mybatis 페이지 처리 위해 만듬
    private Map<String, String> pageData;
    private Map<String, String> searchParam;

    public ParamUtil() {
        this.pageData = new HashMap<>();
        this.searchParam = new HashMap<>();
    }

    public void setSearch(HttpServletRequest request) {
        Enumeration<String> e = request.getParameterNames();
        while ( e.hasMoreElements()){
            String name = (String) e.nextElement();
            if(name.equals("size") || name.equals("page")) {
                this.pageData.put(name, request.getParameter(name));
            }else{
                this.searchParam.put(name, request.getParameter(name));
            }
        }
    }

    public Map<String, String> getPageData() {
        return pageData;
    }

    public Map<String, String> getSearchParam() {
        return searchParam;
    }
}
