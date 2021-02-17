package com.systemk.ams.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface PageMapper {

    Integer pageRecord(HashMap item);
    

}
