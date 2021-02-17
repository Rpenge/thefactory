package com.systemk.ams.VO;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfoVO {

    private long userSeq;

    private String id;

    private String password;

    private String name;

    private String useYn;

    private String roleCode;

}
