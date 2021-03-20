package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfCommCodeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface TfCommCodeMapper {

    List<TfCommCodeVO> commList();

    TfCommCodeVO findCode(String code);

    int commSave(Map item);

    int commUpdate(Map item);

    int commDelete(Map item);

}
