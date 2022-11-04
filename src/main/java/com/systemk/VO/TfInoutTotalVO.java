package com.systemk.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfInoutTotalVO {
    // 입출고현황 마스터 정보 Value Object Class
    private long   inOutSeq;        // 입출고등록일련번호
    private String inOutDate;       // 일자
    private String storeCd;         // 입고 매장코드
    private int    inTotcnt;        // 총입고수량
    private int    outTotcnt;       // 총출고수량
    private int    sellTotcnt;      // 총판매
    private int    stockTotcnt;     // 총재고수량
    private int    inNewcnt;        // 신규입고
    private int    inMovcnt;        // 점간입고
    private int    inIncnt;         // 입고
    private int    inRetcnt;        // 반품입고
    private int    outOutcnt;       // 출고
    private int    outMovcnt;       // 점간출고
    private int    sellStcnt;       // 매장판매
    private int    sellOnlcnt;      // 온라인판매
    private Date   regDate;         // 등록일시
    private String regId;           // 등록자
    private Date   modDate;         // 수정일시
    private String modId;           // 수정자
    private String deleteYn;        // 삭제여부
}
