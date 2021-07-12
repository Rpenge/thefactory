package com.systemk.thefactor2.Config;


import com.systemk.thefactor2.Mapper.TfLogMapper;
import com.systemk.thefactor2.Service.ApiService;
import com.systemk.thefactor2.Service.Impl.ApiServiceImpl;
import com.systemk.thefactor2.Util.StringUtil;
import com.systemk.thefactor2.VO.TfRequestLogVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
//@WebFilter(urlPatterns = "/api/*")
public class LogFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger(LogFilter.class);

    private TfLogMapper tfLogMapper;

    @Value("false")
    boolean requestLogEnabled;

    public LogFilter() { }

    public LogFilter(TfLogMapper tfLogMapper) {
        this.tfLogMapper = tfLogMapper;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        TfRequestLogVO reqVO = new TfRequestLogVO();

        final HttpServletRequest req = new RequestWrapper(request);
        final HttpServletResponse res = new ResponseWrapper(response);

        reqVO.setReqMethod(request.getMethod());
        reqVO.setDevice(request.getHeader("type") != null ? request.getHeader("type") : "PDA" );
        reqVO.setQueryString(request.getQueryString());
        reqVO.setReqUrl(request.getRequestURL().toString());
        reqVO.setUserId(request.getHeader("id"));

        try {
            Object requestBody = ((RequestWrapper) req).convertToObject();
            reqVO.setReqBody(StringUtil.convertJsonString(requestBody));

            filterChain.doFilter(req, res);

            Object responseBody = ((ResponseWrapper) res).convertToObject();
            reqVO.setResBody(StringUtil.convertJsonString(responseBody));
            reqVO.setResStatus(String.valueOf(res.getStatus()));
            ((ResponseWrapper) res).copyBodyToResponse();
        } catch (IOException ex) {
        }
        tfLogMapper.createReqLog(reqVO);
    }

    @Override
    public void destroy() {
        super.destroy();
    }



}
