package com.systemk.thefactor2.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfDeviceStatVO {
    // RFID 리더기(고정형, 휴대형) 운영상탱 정보(C,U) Value Object Class
    private long   deviceSeq;       // 장비SEQ
    private Date   operDate;        // 운용일자
    private String deviceIp;        // 장비IP
    private String serialNo;        // 시리얼번호
    private String modelNo;         // 모델번호
    private String deviceGub;       // 장비구분
    private int    lastRcvSeq;      // 최종수신 SEQ
    private String lastRcvResult;   // 최종수신결과
    private String rcvWorkGub;      // 최종작업
}
