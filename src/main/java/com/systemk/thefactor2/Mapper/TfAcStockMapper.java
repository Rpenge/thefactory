package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfAcStockVO;
import com.systemk.thefactor2.VO.TfStockVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;


@Mapper
public interface TfAcStockMapper {

    TfAcStockVO findStockByTagId(String tagId);

    String stockCheck(String tagId);


}
