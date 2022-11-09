package com.systemk.Mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.systemk.VO.TfAcStockVO;


@Mapper
public interface TfAcStockMapper {

    TfAcStockVO findStockByTagId(String tagId);

    Map stockCheck(String tagId);

    List<String> findTagId(String tagId);

    List<Map> findAcStock(Map item);

    List<Map> acStockList(Map item);

    List<LinkedHashMap<String, Object>> acStockExcel(Map item);

}
