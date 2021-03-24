package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfAcStockVO;
import com.systemk.thefactor2.VO.TfStockVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TfAcStockMapper {

    TfAcStockVO findStockByTagId(String tagId);


}
