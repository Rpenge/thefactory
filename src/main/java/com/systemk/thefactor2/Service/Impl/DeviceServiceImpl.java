package com.systemk.thefactor2.Service.Impl;


import com.systemk.thefactor2.Mapper.TfDeviceMapper;
import com.systemk.thefactor2.Service.DeviceService;
import com.systemk.thefactor2.VO.TfDeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private TfDeviceMapper tfDeviceMapper;
    // 디바이스 목록
    @Override
    public List<TfDeviceVO> deviceList() throws Exception {
        return tfDeviceMapper.deviceList();
    }
    // 디바이스 추가
    @Override
    public Map<String, Object> deviceSave(Map param) throws Exception {
        Map map = new HashMap();
        if (tfDeviceMapper.deviceSave(param) == 1) {
            map.put("resultCode", "S");
        } else {
            map.put("resultCode", "E");
        }
        return map;
    }
    // 디바이스 정보 수정
    @Override
    public Map<String, Object> deviceUpdate(Map param) throws Exception {
        Map map = new HashMap();
        if (tfDeviceMapper.deviceUpdate(param) == 1) {
            map.put("resultCode", "S");
        } else {
            map.put("resultCode", "E");
        }
        return map;
    }
    // 디바이스 삭제
    @Override
    public Map<String, Object> deviceDelete(Map param) throws Exception {
        Map map = new HashMap();
        if (tfDeviceMapper.deviceDelete(param) == 1) {
            map.put("resultCode", "S");
        } else {
            map.put("resultCode", "E");
        }
        return map;
    }
}
