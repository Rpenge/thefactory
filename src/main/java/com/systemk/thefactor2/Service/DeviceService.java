package com.systemk.thefactor2.Service;


import com.systemk.thefactor2.VO.TfDeviceVO;

import java.util.List;
import java.util.Map;

public interface DeviceService {
    public List<TfDeviceVO> deviceList() throws Exception;

    public Map<String, Object> deviceSave(Map param) throws Exception;

    public Map<String, Object> deviceUpdate(Map param) throws Exception;

    public Map<String, Object> deviceDelete(Map param) throws Exception;
}
