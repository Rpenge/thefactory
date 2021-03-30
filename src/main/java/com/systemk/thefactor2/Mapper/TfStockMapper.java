package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfStockVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Mapper
public interface TfStockMapper {

    List<Map> stockList(HashMap item);

    List<Map> stockExList(HashMap item);

    List<Map> stockListSearch(HashMap item);

    TfStockVO findStockInfo(String barcode);

    Map workCnt(HashMap item);

    int stockCheck(Map item);

    int stockSave(Map item);

    int stockUpdate(Map item);

}
