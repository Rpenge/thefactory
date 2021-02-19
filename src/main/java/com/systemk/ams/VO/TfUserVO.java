package com.systemk.ams.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfUserVO {

    private long perMemberNo;

    private String userId;

    private String userPwd;

    private String userNm;

    private String reqJoinDate;

    private String joinStatus;

    private String grade;

    private String userDeptCd;

    private String userRankCd;

    private String storeCd;

}
