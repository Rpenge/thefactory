package com.systemk.Mapper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.systemk.VO.TfStockVO;


@Mapper
public interface TfStockMapper {

    List<Map> stockList(HashMap item);

    List<Map> stockExList(HashMap item);

    List<Map> stockRfidList(HashMap item);

    List<Map> stockListSearch(HashMap item);

    TfStockVO findStockInfo(String barcode);

    Map workCnt(HashMap item);

    int stockCheck(Map item);

    int stockSave(Map item);

    int stockUpdate(Map item);
    
    int invUpdate(Map item); // 211102 엑셀업로드 시 재고실사내역 정보 동시 수정
    
    int inputUpdate(Map item); // 211102 엑셀업로드 시 입고 정보 동시 수정
    
    int outputUpdate(Map item); // 211102 엑셀업로드 시 출고 정보 동시 수정

    int stockInvUpdate(Map item);

    List<LinkedHashMap<String, Object>> stockExcel(Map item);

}
