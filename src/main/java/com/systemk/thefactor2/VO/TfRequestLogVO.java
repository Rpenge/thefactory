package com.systemk.thefactor2.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfRequestLogVO {
    private long   requestSeq;          // seq
    private String reqUrl;          // 요청 URL
    private String reqMethod;
    private String queryString;          // 에러메세지
    private String device;     // 장비
    private String reqBody;        // 요청 json 데이터
    private String resBody;        // 요청자
    private String resStatus;        // 요청자
    private String userId;        // 요청자
    private Date regDate;         // 등록일자

}
