package com.systemk.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.systemk.VO.TfInventoryVO;


@Mapper
public interface TfInventoryMapper {

    List<TfInventoryVO> invenList(HashMap item);

    TfInventoryVO findInventory(Map item);

    int inventorySave(Map item);

    int invUpdate(Map item);

    int manualInvUpdate(Map item);

    List<Map> findInventoryCnt(Map item);



}
