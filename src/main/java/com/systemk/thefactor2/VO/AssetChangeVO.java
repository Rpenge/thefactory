package com.systemk.thefactor2.VO;

import lombok.Data;

import java.util.Date;

@Data
public class AssetChangeVO {

    private long astChgSeq;

    private String astCtrlCd;

    private String astNm;

    private Date chgDt;

    private String astSt;

    private String astWk;

    private String wkPrsn;

    private String wkLoc;

    private String wkEnv;

    private String astDiv;

    private String astDp;

    private String wiYn;

    private String updateYn;

}
