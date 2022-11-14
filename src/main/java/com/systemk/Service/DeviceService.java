package com.systemk.Service;


import java.util.Map;

public interface DeviceService {
    public Map<String, Object> deviceList(Map param) throws Exception;

    public Map<String, Object> deviceSave(Map param) throws Exception;

    public Map<String, Object> deviceUpdate(Map param) throws Exception;

    public Map<String, Object> deviceDelete(Map param) throws Exception;
}
