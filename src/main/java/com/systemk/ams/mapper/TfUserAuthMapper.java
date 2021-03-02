package com.systemk.ams.mapper;

import com.systemk.ams.VO.TfUserAuthVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface TfUserAuthMapper {

    //사용권한 + 메뉴 조회
    List<HashMap> authSearch(HashMap item);


}
