package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfInputVO;
import com.systemk.thefactor2.VO.TfOutputVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;


@Mapper
public interface TfOutputMapper {

    TfOutputVO outputSearch(String tagId);

}
