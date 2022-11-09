package com.systemk.Mapper;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TfUserAuthMapper {

    //사용권한 + 메뉴 조회
    List<HashMap> authSearch(HashMap item);


}
