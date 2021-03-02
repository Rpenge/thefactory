package com.systemk.ams.VO;

import lombok.Data;

import java.util.Date;

/**
 * Created by gglee.
 * User: GwanGyuLEE
 * Date: 2021-03-02
 * Time: 오후 2:32
 */
@Data
public class TfStockVO {
    // 재고 정보 Value Object Class
    private long   stockRegSeq;     // 재고등록일련번호
    private String stockStoreCd;    // 매장코드
    private String stockStoreNm;    // 실사 매장명
    private String brandKindCd;     // 브랜드상품분류 코드
    private String ecPrdCd;         // 상품코드
    private String tfPrdCd;         // 자체상품코드
    private String ecPrdNm;         // 상품명
    private String tfPrdNm;         // 자체상품명
    private String tfPrdBarcode;    // 자체상품 바코드
    private String tfPrdTagid;      // 자체상품 태그아이디
    private String prdSize;         // 사이즈
    private String locationCd;      // 로케이션코드
    private int    curStockCnt;     // 현재 재고 수량
    private int    realStockCnt;    // 실제 재고 수량
    private int    stdStockCnt;     // 적정 재고 수량
    private int    inventoryCnt;    // 재고조사 수량
    private String sellYn;          // 판매(재고여부)
    private Date   regDate;         // 등록일시
    private String regId;           // 등록자
    private Date   modDate;         // 수정일시
    private String modId;           // 수정자
}
