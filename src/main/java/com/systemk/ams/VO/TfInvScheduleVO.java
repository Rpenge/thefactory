package com.systemk.ams.VO;

import lombok.Data;

import java.util.Date;

/**
 * Created by gglee.
 * User: GwanGyuLEE
 * Date: 2021-03-02
 * Time: 오후 2:20
 */
@Data
public class TfInvScheduleVO {
    // 재고실사 예정정보 Value Object Class - 사용X, 혹시 몰라서 만들어는 둠.
    private long   stSchSeq;    // 재고실사예정 일련번호
    private String stSchNm;     // 재고실사 명칭
    private int    stInvCnt;    // 재고실사 수량
    private String stInvStt;    // 재고실사 시작일
    private String stInvEnd;    // 재고실사 종료일
    private String stInvYn;     // 재고실사 완료여부
    private Date   regDate;     // 등록일시
    private String regId;       // 등록자
    private Date   modDate;     // 수정일시
    private String modId;       // 수정자
}
