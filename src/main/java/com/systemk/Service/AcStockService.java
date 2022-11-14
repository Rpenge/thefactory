package com.systemk.Service;



import java.util.Map;

public interface AcStockService {

    public Map<String, Object> findStock(Map param) throws Exception;

    public Map<String, Object> findAcStock(Map param) throws Exception;
}
