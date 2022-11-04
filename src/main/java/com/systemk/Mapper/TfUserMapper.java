package com.systemk.Mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.systemk.VO.TfUserVO;

@Mapper
public interface TfUserMapper {
	
    TfUserVO login(HashMap item);

}