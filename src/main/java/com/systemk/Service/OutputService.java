package com.systemk.Service;



import java.util.Map;

public interface OutputService {

    public Map<String, Object> findList(Map param) throws Exception;

    public Map<String, Object> findSalseList(Map param) throws Exception;
    
    public Map<String, Object> outputMoveSearch(String tagId) throws Exception; // 211015 추가

    public Map<String, Object> outputSearch(String tagid) throws Exception;

    public Map<String, Object> outputAdd(Map param) throws Exception;

    public Map<String, Object> outputDelete(Map param) throws Exception;


}
