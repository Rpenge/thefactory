package com.systemk.thefactor2.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface PageMapper {

    Integer pageRecord(HashMap item);

    Integer inPageRecord(HashMap item);

    Integer outPageRecord(HashMap item);

}
