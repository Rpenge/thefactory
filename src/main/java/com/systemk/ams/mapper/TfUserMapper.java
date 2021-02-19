package com.systemk.ams.mapper;

import com.systemk.ams.VO.TfUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface TfUserMapper {

    TfUserVO login(HashMap item);

}
