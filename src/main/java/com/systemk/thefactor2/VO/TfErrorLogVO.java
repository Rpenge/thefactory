package com.systemk.thefactor2.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfErrorLogVO {
    private long   errSeq;          // seq
    private String errUrl;          // 요청 URL
    private String errMsg;          // 에러메세지
    private String queryString;     // 파라미터
    private String device;           // 장비
    private String userId;        // 요청자
    private Date regDate;         // 등록일자

}
