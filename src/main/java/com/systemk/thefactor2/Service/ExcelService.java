package com.systemk.thefactor2.Service;


import com.systemk.thefactor2.VO.TfApplicationVO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public interface ExcelService {

    Map stockExcelUpload(MultipartFile mf, String userId) throws Exception;

    Map productExcelUpload(MultipartFile mf, String userId) throws Exception;

    Map brandExcelUpload(MultipartFile mf, String userId) throws Exception;

    void inoutExcelDown(Map map, HttpServletResponse response) throws Exception;

    void stockExcelDown(Map map, HttpServletResponse response) throws Exception;

    void stkBaseExcelDown(Map map, HttpServletResponse response) throws Exception;
    
    void invInfoExcelDown(Map map, HttpServletResponse response) throws Exception; // 211201 재고 실사 상세 내역 엑셀 다운로드
}
