package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfBrandVO;
import com.systemk.thefactor2.VO.TfInputVO;
import com.systemk.thefactor2.VO.TfUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;


@Mapper
public interface TfInputMapper {

    List<TfInputVO> inputList(HashMap item);

    int inputNew(HashMap item);

}
