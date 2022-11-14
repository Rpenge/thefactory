package com.systemk.Config;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import com.systemk.Mapper.TfLogMapper;
import com.systemk.Util.ResultUtil;
import com.systemk.VO.TfErrorLogVO;

//exception 로그 저장
@ControllerAdvice(basePackages = "com.systemk.thefactor2.Controller.Api")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandler {

    @Autowired
    private TfLogMapper tfLogMapper;

    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleException(HttpServletRequest request, Exception ex) throws Exception{

        TfErrorLogVO errorLog = new TfErrorLogVO();

        errorLog.setErrUrl(request.getRequestURL().toString());
        errorLog.setDevice(request.getHeader("type") != null ? request.getHeader("type") : "PDA" );
        errorLog.setQueryString(request.getQueryString());
        errorLog.setUserId(request.getHeader("id"));

        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        if(errors.toString().length()>= 4800){
            String errMsg = errors.toString().substring(0, 4800);
            errorLog.setErrMsg(errMsg);
        }else{
            errorLog.setErrMsg(errors.toString());
        }
        tfLogMapper.createErrLog(errorLog);

        return new ResponseEntity<Map<String, Object>>(ResultUtil.setCommonResult("E", "에러가 발생했습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
