package com.systemk.thefactor2.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfOutputVO {
    // 출고정보 Value Object Class
    private long   stOutSeq;        // 매장 출고등록 일련번호
    private String stOutDate;       // 출고일자
    private String outStoreCd;      // 출고 매장코드
    private String outStoreNm;      // 출고 매장명
    private String brandKindCd;     // 브랜드상품분류 코드
    private String ecPrdCd;         // 상품코드
    private String tfPrdCd;         // 자체상품코드
    private String btPrdBarcode;    // 자체상품 바코드
    private String tfPrdTagid;      // 자체상품 태그아이디
    private String prdSize;         // 사이즈(규격)
    private String stOutType;       // 출고유형
    private String inStoreCd;       // 입고 매장코드(창고)
    private String inStoreNm;       // 입고 매장명
    private String regDeviceGub;    // 등록장비구분
    private Date   regDate;         // 등록일시
    private String regId;           // 등록자
    private Date   modDate;         // 수정일시
    private String modId;           // 수정자
    private String tfPrdNm;           // 상품명
}
