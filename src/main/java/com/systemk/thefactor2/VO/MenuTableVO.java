package com.systemk.thefactor2.VO;

import lombok.Data;

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
