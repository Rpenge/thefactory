package com.systemk.ams.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfPgmVO {

    private String groupCd;

    private String pgmCd;

    private String pgmNm;

    private String pgmUrl;

    private int pgmLevel;

    private Date regDate;

    private String regId;

    private Date modDate;

    private String modId;

}
