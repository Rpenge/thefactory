package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfDeviceVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface TfDeviceMapper {
    // 디바이스 목록
    List<TfDeviceVO> deviceList();
    // 디바이스 추가
    int deviceSave(Map item);
    // 디바이스 수정
    int deviceUpdate(Map item);
    // 디바이스 삭제
    int deviceDelete(Map item);

    TfDeviceVO serialSearch(Map item);

}
