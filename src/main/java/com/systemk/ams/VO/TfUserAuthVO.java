package com.systemk.ams.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfUserAuthVO {

    private String pgmCd;

    private String grade;

    private String storeCd;

    private String pgmNm;

    private String authSchYn;

    private String authSaveYn;

    private String authUpdateYn;

    private String authDelYn;

    private String authExcelYn;

    private Date regDate;

    private String regId;

    private Date modDate;

    private String modId;

}
