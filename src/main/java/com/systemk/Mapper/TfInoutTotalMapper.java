package com.systemk.Mapper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.systemk.VO.TfInoutTotalVO;

@Mapper
public interface TfInoutTotalMapper {

    TfInoutTotalVO todayWork(Map item);

    TfInoutTotalVO todayWorkAllVO();

    Map todayWorkAll();

    Map monthWork(int item);

    List<TfInoutTotalVO> inoutList(HashMap item);

    int inoutCreate();

    List<LinkedHashMap<String, Object>> inExcel(Map item);

    List<LinkedHashMap<String, Object>> outExcel(Map item);

    List<LinkedHashMap<String, Object>> totExcel(Map item);

}
