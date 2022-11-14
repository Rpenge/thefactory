package com.systemk.Service;


import java.util.Map;

public interface ProductService {

    public Map<String, Object> productList(Map param) throws Exception;

    public Map<String, Object> productSave(Map param) throws Exception;

    public Map<String, Object> productUpdate(Map param) throws Exception;

    // public Map<String, Object> productDelete(Map param) throws Exception;
    
    public Map<String, Object> productDelYn(Map param) throws Exception;
}
