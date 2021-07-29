package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfErrorLogVO;
import com.systemk.thefactor2.VO.TfRequestLogVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;


//errorLog, requestLog, resetLog
@Mapper
public interface TfLogMapper {

    void createErrLog(TfErrorLogVO item);

    void createReqLog(TfRequestLogVO item);

    void createResetLog(Map item);


}
