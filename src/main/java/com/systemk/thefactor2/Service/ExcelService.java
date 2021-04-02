package com.systemk.thefactor2.Service;


import com.systemk.thefactor2.VO.TfApplicationVO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public interface ExcelService {

    Map stockExcelUpload(MultipartFile mf, String userId) throws Exception;

    Map productExcelUpload(MultipartFile mf, String userId) throws Exception;

}
