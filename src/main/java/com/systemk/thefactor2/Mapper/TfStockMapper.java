package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfStockVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TfStockMapper {

    TfStockVO findStockInfo(String barcode);


}
