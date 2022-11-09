package com.systemk.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.systemk.VO.TfCommCodeVO;


@Mapper
public interface TfCommCodeMapper {

    List<TfCommCodeVO> commList();

    List<TfCommCodeVO> commTotalList();

    List<TfCommCodeVO> findList(HashMap item);

    List<TfCommCodeVO> commBList();

    List<TfCommCodeVO> commMList(String commCd);

    List<TfCommCodeVO> commSList(String code);

    List<TfCommCodeVO> commMSList(String code);

    int commSave(Map item);

    int commUpdate(Map item);

    int commDelete(Map item);

    TfCommCodeVO findCode(String code);

}
