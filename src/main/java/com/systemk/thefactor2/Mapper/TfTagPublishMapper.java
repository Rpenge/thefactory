package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfDeviceVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;


@Mapper
public interface TfTagPublishMapper {

    String selectLastNum(String item);


}
