package com.systemk.ams.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface FileActionService {

	public boolean assetExcelUpload(MultipartFile mf, String userId) throws SQLException;

	public void createExcel(List<Map<String, String>> list, HttpServletResponse response) throws Exception;
}
