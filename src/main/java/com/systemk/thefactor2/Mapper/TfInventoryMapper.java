package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfInvStatusVO;
import com.systemk.thefactor2.VO.TfInventoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Mapper
public interface TfInventoryMapper {

    List<TfInventoryVO> invenList(HashMap item);

    int inventorySave(Map item);

    int invUpdate(Map item);

    int manualInvUpdate(Map item);



}
