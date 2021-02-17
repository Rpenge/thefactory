package com.systemk.ams.VO;

import lombok.Data;

import java.util.Date;

@Data
public class MenuTableVO {

    private long menuSeq;

    private String menuName;

    private String parentCode;

    private String menuCode;

    private String url;

    private String useYn;

    private String rank;

    private  String roles;

}
