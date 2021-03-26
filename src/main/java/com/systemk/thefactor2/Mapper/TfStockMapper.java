package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfStockVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;


@Mapper
public interface TfStockMapper {

    List<TfStockVO> stockList(HashMap item);

    TfStockVO findStockInfo(String barcode);


}
