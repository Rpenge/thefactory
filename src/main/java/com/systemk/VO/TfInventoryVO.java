package com.systemk.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfInventoryVO {
    // 재고실사 정보 Value Object Class
    private long   stInvenSeq;      // 재고실사 일련번호
    private long   stInvSeq;        // 실사현황 일련번호(foreign)
    private String tfPrdTagid;      // 자체상품 태그아이디
    private String stInvDate;       // 실사 일자
    private String invStoreCd;      // 실사 매장코드
    private String brandKindCd;     // 브랜드상품분류 코드
    private String tfPrdCd;         // 자체상품코드
    private String tfPrdNm;         // 자체상품코드
    private String btPrdBarcode;    // 자체상품 바코드
    private String prdSize;         // 사이즈
    private String misWork;         // 불일치 작업
    private String invComment;      // 실사처리내용
    private String invYn;           // 확정여분
    private Date   regDate;         // 등록일시
    private String regId;           // 등록자
    private Date   modDate;         // 수정일시
    private String modId;           // 수정자
    private String deviceGub;       //등록장비
}
