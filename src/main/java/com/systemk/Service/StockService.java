package com.systemk.Service;



import java.util.Map;

public interface StockService {

    public Map<String, Object> findList(Map param) throws Exception;

    public Map<String, Object> findExList(Map param) throws Exception;

    public Map<String, Object> findRfidList(Map param) throws Exception;

    public Map<String, Object> findListSearch(Map param) throws Exception;


}
