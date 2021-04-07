package com.systemk.thefactor2.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfInvStatusVO {
    private long   stInvSeq;    //
    private String stInvDate;     //
    private String stInvStore;     //
    private int    stTarCnt;    // 재고실사 수량
    private int    stInvCnt;    // 재고실사 수량
    private String stPdaYn;     // PDA실사 작업 완료여부
    private String stInvYn;     // 재고실사 완료여부
    private Date   regDate;     // 등록일시
    private String regId;       // 등록자
    private Date   modDate;     // 수정일시
    private String modId;       // 수정자
}
