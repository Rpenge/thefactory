package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfOutputVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;


@Mapper
public interface TfOutputMapper {

    List<TfOutputVO> outList(HashMap item);

    TfOutputVO outputSearch(String tagId);

    int outputAdd(HashMap item);

    List<TfOutputVO> outDeleteList(List item);

    int outDelete(HashMap item);

}
