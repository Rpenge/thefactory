package com.systemk.thefactor2.VO;

import lombok.Data;

import java.util.Date;

/**
 * Created by gglee.
 * User: GwanGyuLEE
 * Date: 2021-03-02
 * Time: 오후 1:45
 */
@Data
public class TfPrdSizeVO {
    // 상품분류별 사이즈(규격) Value Object Class
    private String brandKindCd; // 상품분류코드
    private String sizeCd;      // 사이즈(규격)코드
    private String sizeNm;      // 사이즈(규격)코드명
    private int    useYn;       // 사용여부
    private Date   regDate;     // 등록일시
    private String regId;       // 등록자
    private Date   modDate;     // 수정일시
    private String modId;       // 수정자
}