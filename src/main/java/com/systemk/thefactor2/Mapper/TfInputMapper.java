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

    int inputRe(HashMap item);

    List<TfInputVO> inputDeleteList(List item);

    int inputDelete(HashMap item);

}
