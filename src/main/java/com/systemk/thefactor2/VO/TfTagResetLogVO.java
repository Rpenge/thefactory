package com.systemk.thefactor2.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfTagResetLogVO {
    // 태그 초기화 내역 Value Object Class
    private long   resetSeq;        // 일련번호
    private String tagId;           // 태그ID
    private String comment;         // 비고
    private Date   regDate;         // 등록일시
    private String regId;           // 등록자
}
