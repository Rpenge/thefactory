package com.systemk.thefactor2.Service;



import java.util.Map;

public interface OutputService {

    public Map<String, Object> findList(Map param) throws Exception;

    public Map<String, Object> outputSearch(String tagid) throws Exception;



}