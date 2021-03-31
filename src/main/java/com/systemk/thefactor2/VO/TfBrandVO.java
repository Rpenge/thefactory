package com.systemk.thefactor2.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfBrandVO {
    // 상품 분류코드(브랜드별) Value Object Class
    private String brandKindCd; // 브랜드상품분류 코드
    private String brandNm;     // 브랜드 명
    private int    prdCnt;      // 상품갯수
    private String codeLevel;   // 코드레벨
    private Date   regDate;     // 등록일시
    private String regId;       // 등록자
    private Date   modDate;     // 수정일시
    private String modId;       // 수정자
    private String useYn;       // 브랜드 사용여부
}
