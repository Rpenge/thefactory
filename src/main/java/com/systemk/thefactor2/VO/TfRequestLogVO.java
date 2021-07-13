package com.systemk.thefactor2.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfRequestLogVO {
    // API요청 로그 Value Object Class
    private long   requestSeq;      // seq
    private String reqUrl;          // 요청 URL
    private String reqMethod;       // 요청 METHOD
    private String queryString;     // 에러메세지
    private String device;          // 장비
    private String reqBody;         // 요청 데이터
    private String resBody;         // 응답 데이터
    private String resStatus;       // 상태코드
    private String userId;          // 요청자
    private Date   regDate;         // 등록일자

}
