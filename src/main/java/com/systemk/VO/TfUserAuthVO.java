package com.systemk.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfUserAuthVO {
    // 등급별 권한관리(프로그램 사용권한) Value Object Class
    private String pgmCd;           // 프로그램 코드
    private String grade;           // 등급
    private String storeCd;         // 매장코드
    private String authSchYn;       // 조회
    private String authSaveYn;      // 저장
    private String authUpdateYn;    // 수정
    private String authDelYn;       // 삭제
    private String authExcelYn;     // 엑셀
    private Date   regDate;         // 등록일시
    private String regId;           // 등록자
    private Date   modDate;         // 수정일시
    private String modId;           // 수정자
}
