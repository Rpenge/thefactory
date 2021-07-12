package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfAcStockVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Mapper
public interface TfAcStockMapper {

    TfAcStockVO findStockByTagId(String tagId);

    Map stockCheck(String tagId);

    List<String> findTagId(String tagId);

    List<Map> findAcStock(String storeCd);

    List<Map> acStockList(Map item);

    List<LinkedHashMap<String, Object>> acStockExcel(Map item);

}
