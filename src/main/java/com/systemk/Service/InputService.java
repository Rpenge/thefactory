package com.systemk.Service;



import java.util.Map;

public interface InputService {

    public Map<String, Object> findList(Map param) throws Exception;

    public Map<String, Object> searchPrd(Map param) throws Exception;

    public Map<String, Object> inputAddResult(Map param) throws Exception;

    public Map<String, Object> inputDelete(Map param) throws Exception;


}
