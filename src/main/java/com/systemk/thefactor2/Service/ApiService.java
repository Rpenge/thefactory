package com.systemk.thefactor2.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ApiService {

    public void apiReg(HttpServletRequest request);

    public Map<String, Object> pdaLogin(Map param) throws Exception;

    public Map<String, Object> workCount(Map param) throws Exception;

    public Map<String, Object> inputList(Map param) throws Exception;

    public Map<String, Object> searchPrd(Map param) throws Exception;

    public Map<String, Object> inputWork(Map param) throws Exception;

}
