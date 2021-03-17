package com.systemk.thefactor2.VO;

import lombok.Data;

import java.util.Date;

/**
 * Created by gglee.
 * User: GwanGyuLEE
 * Date: 2021-03-02
 * Time: 오후 1:41
 */
@Data
public class TfCommCodeVO {
    // 공통코드 Value Object Class
    private String commCd;      // 코드
    private String commCdNm;    // 코드명
    private String codeLevel;   // 코드레벨
    private int    dispSeq;     // 조회순서
    private String comment;     // 부연설명
    private String useYn;       // 사용여부
    private Date   regDate;     // 등록일시
    private String regId;       // 등록자
    private Date   modDate;     // 수정일시
    private String modId;       // 수정자
}
