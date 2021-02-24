package com.systemk.ams.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfUserVO {

    private long perMemberNo;

    private String userId;

    private String userPwd;

    private String userNm;

    private String storeCd;

    private String userDeptCd;

    private String userRankCd;

    private String grade;

    private String reqJoinDate;

    private String pdaUseYn;

    private String userStat;

}
