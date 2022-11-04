package com.systemk.VO;

import java.util.Date;

import lombok.Data;

@Data
public class TempRfidTagVO {
    // 임시 태그 번호 발번 Value Object Class
    private String rfRfidTag;   // 태그번호
    private Date   regDate;     // 등록일시

}
