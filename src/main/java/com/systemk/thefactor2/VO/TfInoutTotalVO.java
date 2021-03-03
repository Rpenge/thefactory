package com.systemk.thefactor2.VO;

import lombok.Data;

import java.util.Date;

/**
 * Created by gglee.
 * User: GwanGyuLEE
 * Date: 2021-03-02
 * Time: 오후 2:00
 */
@Data
public class TfInoutTotalVO {
    // 입출고현황 마스터 정보 Value Object Class
    private long   inOutSeq;        // 입출고등록일련번호
    private Date   inOutDate;       // 일자
    private String storeCd;         // 입고 매장코드
    private int    inTotcnt;        // 총입고수량
    private int    outTotcnt;       // 총출고수량
    private int    stockTotcnt;     // 총재고수량
    private Date   regDate;         // 등록일시
    private String regId;           // 등록자
    private Date   modDate;         // 수정일시
    private String modId;           // 수정자
}
