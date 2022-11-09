package com.systemk.Mapper;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.systemk.VO.TfErrorLogVO;
import com.systemk.VO.TfRequestLogVO;


//errorLog, requestLog, resetLog
@Mapper
public interface TfLogMapper {

    void createErrLog(TfErrorLogVO item);

    void createReqLog(TfRequestLogVO item);

    void createResetLog(Map item);


}
