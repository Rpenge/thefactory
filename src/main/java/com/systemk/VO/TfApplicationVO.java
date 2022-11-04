package com.systemk.VO;

import lombok.Data;

import java.util.Date;


@Data
public class TfApplicationVO {
    // 어플리케이션 관리
    private long   appSeq;      // SEQ
    private String appGub;      // 어플구분
    private String appNm;       // 어플명
    private String version;     // 버전
    private String appDownUrl;  // 다운로드 주소
    private Date   regDate;     // 등록일시
    private String regId;       // 등록자
    private Date   modDate;     // 수정일시
    private String modId;       // 수정자
    private String comment;     // 비고
}
