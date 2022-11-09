package com.systemk.Mapper;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TfTagPublishMapper {

    String selectLastNum(String item);


}
