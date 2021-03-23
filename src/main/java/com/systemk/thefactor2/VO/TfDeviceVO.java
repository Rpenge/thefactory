package com.systemk.thefactor2.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfDeviceVO {
    // RFID 운영장비 정보 Value Object Class
    private long   deviceSeq;   // SEQ
    private String storeCd;     // 매장코드
    private String storeNm;     // 매장명
    private String deviceGub;   // 장비구분
    private String serialNo;    // 시리얼번호
    private String modelNo;     // 모델번호
    private String setIp;       // 등록IP
    private String macNo;       // MAC주소
    private String setName;     // 운영명칭(별명)
    private Date   setDate;     // 설치일시
    private String deviceStat;  // 운용상태
    private String commType;    // 통신방식
    private String manufact;    // 제조사
    private Date   regDate;     // 등록일시
    private String regId;       // 등록자
    private Date   modDate;     // 수정일시
    private String modId;       // 수정자
}
