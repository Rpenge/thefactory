package com.systemk.thefactor2.Service;



import java.util.Map;

public interface InvenService {

    public Map<String, Object> findInvStatus(Map param) throws Exception;

    public Map<String, Object> findInvList(Map param) throws Exception;

    public Map<String, Object> invenUpdate(Map param) throws Exception;

    public Map<String, Object> invenUpdateList(Map param) throws Exception;

    public Map<String, Object> invenDelete(Map param) throws Exception;

}
