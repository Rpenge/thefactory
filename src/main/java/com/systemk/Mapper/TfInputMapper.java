package com.systemk.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.systemk.VO.TfInputVO;


@Mapper
public interface TfInputMapper {

    List<TfInputVO> inputList(HashMap item);

    int inputNew(HashMap item);

    int inputRe(HashMap item);

    List<TfInputVO> inputDeleteList(List item);

    boolean inputDeleteCheck(Integer item);

    int inputDelete(HashMap item);

    List<Map> inSubList(HashMap item);
}
