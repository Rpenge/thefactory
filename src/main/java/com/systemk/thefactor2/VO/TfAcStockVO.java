package com.systemk.thefactor2.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfAcStockVO {
    // 실제 재고 정보 Value Object Class
    private long   acStockSeq;     // 실재고 일련번호
    private String tfPrdBarcode;    // 자체상품 바코드
    private String tfPrdTagid;      // 태그ID
    private String storeCd;         // 매장
    private String stInType;        // 작업구분
    private Date   regDate;         // 등록일시
    private String regId;           // 등록자
    private Date   modDate;         // 수정일시
    private String modId;           // 수정자


}
