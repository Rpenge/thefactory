package com.systemk.thefactor2.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfStockVO {
    // 재고 정보 Value Object Class
    private long   stockRegSeq;     // 재고등록일련번호
    private String stockStoreCd;    // 매장코드
    private String stockStoreNm;    // 실사 매장명
    private String brandKindCd;     // 브랜드상품분류 코드
    private String tfPrdCd;         // 자체상품코드
    private String tfPrdNm;         // 상품명
    private String tfPrdBarcode;    // 자체상품 바코드
    private String prdSize;         // 사이즈
    private String prdSizeCd;       // 사이즈코드
    private int    curStockCnt;     // EC현재 재고 수량
    private int    realStockCnt;    // EC실제 재고 수량
    private int    stdStockCnt;     // EC적정 재고 수량
    private int    rfidStockCnt;    // RFID재고 수량
    private int    inventoryCnt;    // 재고조사 수량
    private Date   regDate;         // 등록일시
    private String regId;           // 등록자
    private Date   modDate;         // 수정일시
    private String modId;           // 수정자
}
