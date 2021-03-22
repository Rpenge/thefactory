package com.systemk.thefactor2.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfProductVO {
    // 상품 정보(브랜드 상품분류별) Value Object Class
    private long   prdRegSeq;       // 상품등록일련번호
    private String brandKindCd;     // 브랜드상품분류 코드
    private String ecPrdCd;         // 상품코드
    private String tfPrdCd;         // 자체상품코드
    private String ecPrdNm;         // 상품명(EC상품명)
    private String tfPrdNm;         // 자체상품명(상품명)
    private String prdModelNm;      // 모델병
    private String brandMakeNm;     // 제조사 명
    private String orgCountryNm;    // 원산지 명
    private String brandNm;         // 브랜드 명
    private Date   regDate;         // 등록일시
    private String regId;           // 등록자
    private Date   modDate;         // 수정일시
    private String modId;           // 수정자
}
