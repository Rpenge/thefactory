package com.systemk.thefactor2.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TfDeviceHisVO {
    // RFID 리더기(고정형,휴대형) 운영상태 상세정보 Value Object Class
    private long   deviceHisSeq;    // SEQ
    private long   deviceSeq;       // 장비SEQ
    private Date   operDate;        // 운용일자
    private String deviceIp;        // 장비IP
    private String serialNo;        // 시리얼번호
    private String modelNo;         // 모델번호
    private int    rcvSeq;          // 수신SEQ
    private String deviceGub;       // 장비구분
    private String rcvResult;       // 수신결과
    private String rcvWorkGub;      // 수신작업
}
