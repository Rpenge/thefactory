package com.systemk.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.systemk.VO.TfOutputVO;


@Mapper
public interface TfOutputMapper {

    List<TfOutputVO> outList(HashMap item);

    TfOutputVO outWorkSearch(Map item);
    
    TfOutputVO outAndSaleSearch(Map item); // 210915 추가

    TfOutputVO outputSearch(String tagId);
    
    TfOutputVO outputMoveSearch(String tagId);

    int outputAdd(HashMap item);

    List<TfOutputVO> outDeleteList(List item);

    boolean outDeleteCheck(Integer item);

    int outDelete(HashMap item);

    List<Map> outSubList(HashMap item);

}
