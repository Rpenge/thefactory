package com.systemk.Mapper;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TempRfidTagMapper {

    String tempTagCheck(String rfidTag);

    int tempTagSave(String rfidTag);

    String selectLastNum(String item);

}
