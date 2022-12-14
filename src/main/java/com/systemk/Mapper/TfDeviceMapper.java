package com.systemk.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.systemk.VO.TfDeviceVO;


@Mapper
public interface TfDeviceMapper {
    // 디바이스 목록
    List<TfDeviceVO> deviceList(HashMap item);
    // 디바이스 추가
    int deviceSave(Map item);
    // 디바이스 수정
    int deviceUpdate(Map item);
    // 디바이스 삭제
    int deviceDelete(int item);

    TfDeviceVO serialSearch(Map item);

}
