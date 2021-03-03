package com.systemk.thefactor2.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface TfUserAuthMapper {

    //사용권한 + 메뉴 조회
    List<HashMap> authSearch(HashMap item);


}
