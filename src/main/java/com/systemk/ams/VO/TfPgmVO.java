package com.systemk.ams.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfPgmVO {
    // 프로그램관리(등급별메뉴) Value Object Class
    private String groupCd;     // 그룹코드
    private String pgmCd;       // 프로그램 코드
    private String pgmNm;       // 프로그램 명
    private String pgmUrl;      // 프로그램 주소
    private int    pgmLevel;    // 정렬 순서
    private Date   regDate;     // 등록일시
    private String regId;       // 등록자
    private Date   modDate;     // 수정일시
    private String modId;       // 수정자
}
