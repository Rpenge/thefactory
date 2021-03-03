package com.systemk.thefactor2.mapper;

import com.systemk.thefactor2.VO.TfUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface TfUserMapper {

    TfUserVO login(HashMap item);

}
