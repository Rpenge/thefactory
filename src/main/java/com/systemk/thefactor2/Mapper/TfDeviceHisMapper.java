package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfDeviceVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Mapper
public interface TfDeviceHisMapper {

    int deviceHisSave(Map item);

}
