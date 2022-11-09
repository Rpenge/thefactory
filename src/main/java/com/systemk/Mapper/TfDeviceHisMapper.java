package com.systemk.Mapper;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TfDeviceHisMapper {

    int deviceHisSave(Map item);

}
