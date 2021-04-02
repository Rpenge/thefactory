package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfAcStockVO;
import com.systemk.thefactor2.VO.TfStockVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface TfAcStockMapper {

    TfAcStockVO findStockByTagId(String tagId);

    String stockCheck(String tagId);

    List<String> findTagId(String barcode);

    List<Map> findAcStock(String storeCd);


}
