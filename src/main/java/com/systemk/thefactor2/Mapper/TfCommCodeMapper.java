package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfCommCodeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Mapper
public interface TfCommCodeMapper {

    List<TfCommCodeVO> commList();

    List<TfCommCodeVO> findList(HashMap item);

    List<TfCommCodeVO> commBList(String code);

    List<TfCommCodeVO> commMList(String code);

    List<TfCommCodeVO> commSList(String code);

    List<TfCommCodeVO> commMSList(String code);

    int commSave(Map item);

    int commUpdate(Map item);

    int commDelete(Map item);

    TfCommCodeVO findCode(String code);

}
