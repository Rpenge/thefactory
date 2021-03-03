package com.systemk.thefactor2.VO;

import lombok.Data;

import java.util.Date;

/**
 * Created by gglee.
 * User: GwanGyuLEE
 * Date: 2021-03-02
 * Time: 오후 2:25
 */
@Data
public class TfInventoryVO {
    // 재고실사 정보 Value Object Class
    private long   stInvSeq;        // 재고실사등록 일련번호
    private Date   stInvDate;       // 실사 일자
    private int    stInvprdSeq;     // 실사 차수
    private String invStoreCd;      // 실사 매장코드
    private String invStoreNm;      // 실사 매장명
    private String brandKindCd;     // 브랜드상품분류 코드
    private String ecPrdCd;         // 상품코드
    private String tfPrdCd;         // 자체상품코드
    private String btPrdBarcode;    // 자체상품 바코드
    private String tfPrdTagid;      // 자체상품 태그아이디
    private String prdSize;         // 사이즈
    private String prdLocCd;        // 로케이션코드
    private String regDeviceGub;    // 등록장비구분
    private Date   regDate;         // 등록일시
    private String regId;           // 등록자
    private Date   modDate;         // 수정일시
    private String modId;           // 수정자
    private long   stSchSeq;        // 재고실사예정 일련번호
}
