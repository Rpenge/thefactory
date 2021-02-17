package com.systemk.ams.VO;

import lombok.Data;

import java.util.Date;

@Data
public class CommonCodeVO {

    private String cmmnSeq;

    private String code;

    private String codeName;

    private String parentCode;

    private String codeDepth;


}
