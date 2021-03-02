package com.systemk.ams.VO;

import lombok.Data;

import java.util.Date;

/**
 * Created by gglee.
 * User: GwanGyuLEE
 * Date: 2021-03-02
 * Time: 오후 2:39
 */
@Data
public class TfTagPublishVO {
    // RFID TAG 발행정보 Value Object Class
    private long   tagWriteSeq;     // 태그발행등록 일련번호
    private int    writeSeq;        // WRITE 순번
    private String brandKindCd;     // 브랜드상품분류 코드
    private String ecPrdCd;         // 상품코드
    private String tfPrdCd;         // 자체상품코드
    private String tfPrdBarcode;    // 자체상품 바코드
    private String tfPrdTagid;      // RFID 태그 인코딩 ID
    private String writeStoreCd;    // WRITE 매장코드
    private String writeStoreNm;    // WRITE 매장명
    private String tagWriteStat;    // 태그상태
    private Date   regDate;         // 등록일시
    private String regId;           // 등록자
    private Date   modDate;         // 수정일시
    private String modId;           // 수정자
}
