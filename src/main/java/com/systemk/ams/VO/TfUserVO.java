package com.systemk.ams.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfUserVO {
    // 사용자 정보 Value Object Class
    private long   perMemberNo;     // 회원번호
    private String userId;          // 회원ID
    private String userPwd;         // 패스워드
    private String userNm;          // 회원이름
    private String storeCd;         // 매장코드
    private String userDeptCd;      // 부서
    private String userRankCd;      // 직급
    private String grade;           // 등급
    private Date   reqJoinDate;     // 가입신청 일시
    private String pdaUseYn;        // PDA사용여부
    private String userStat;        // 사용자 상태
}
