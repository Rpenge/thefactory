package com.systemk.VO;

import java.util.Date;

import lombok.Data;

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
    private String brandKindCd;     // 브랜드
    private String acPrdSize;         // 사이즈
    private Date   modDate;         // 수정일시
    private String modId;           // 수정자


}
