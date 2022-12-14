package com.systemk.Mapper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.systemk.VO.TfInvStatusVO;


@Mapper
public interface TfInvStatusMapper {

    List<TfInvStatusVO> invStatusList(HashMap item);

    TfInvStatusVO findInvStatus(Map item);

    TfInvStatusVO createInvStatus(Map item);

    int updateInvStatus(TfInvStatusVO item);

    int invenDelete(int seq);

    List<LinkedHashMap<String, Object>> invInfoExcel(Map item); // 211201 재고실사 상세내역 엑셀 다운로드
}
