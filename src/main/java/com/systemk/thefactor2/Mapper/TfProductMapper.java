package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfInputVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Mapper
public interface TfProductMapper {

    Map prdAndStk(Map item);



}
