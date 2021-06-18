package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfOutputVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Mapper
public interface TfOutputMapper {

    List<TfOutputVO> outList(HashMap item);

    TfOutputVO outWorkSearch(Map item);

    TfOutputVO outputSearch(String tagId);

    int outputAdd(HashMap item);

    List<TfOutputVO> outDeleteList(List item);

    boolean outDeleteCheck(Integer item);

    int outDelete(HashMap item);

    List<Map> outSubList(HashMap item);

}
