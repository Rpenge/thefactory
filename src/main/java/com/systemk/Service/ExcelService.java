package com.systemk.Service;


import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {

    Map stockExcelUpload(MultipartFile mf, String userId) throws Exception;

    Map productExcelUpload(MultipartFile mf, String userId) throws Exception;

    Map brandExcelUpload(MultipartFile mf, String userId) throws Exception;

    void inoutExcelDown(Map map, HttpServletResponse response) throws Exception;

    void stockExcelDown(Map map, HttpServletResponse response) throws Exception;

    void stkBaseExcelDown(Map map, HttpServletResponse response) throws Exception;
    
    void invInfoExcelDown(Map map, HttpServletResponse response) throws Exception; // 211201 재고 실사 상세 내역 엑셀 다운로드
}
