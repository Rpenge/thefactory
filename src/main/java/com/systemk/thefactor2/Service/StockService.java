package com.systemk.thefactor2.Service;



import java.util.Map;

public interface StockService {

    public Map<String, Object> findList(Map param) throws Exception;

    public Map<String, Object> stkGubSearch(Map param) throws Exception;


}
