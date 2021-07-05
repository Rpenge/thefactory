package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfErrorLogVO;
import com.systemk.thefactor2.VO.TfRequestLogVO;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface TfLogMapper {

    void createErrLog(TfErrorLogVO item);

    void createReqLog(TfRequestLogVO item);


}
