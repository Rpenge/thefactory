package com.systemk.Mapper;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.systemk.VO.TfApplicationVO;


@Mapper
public interface TfApplicationMapper {

    TfApplicationVO appLastVs(Map item);

    int appSave(Map item);



}
