package com.systemk.thefactor2.Controller;


import com.systemk.thefactor2.Service.ExcelService;
import com.systemk.thefactor2.Service.FileService;
import com.systemk.thefactor2.VO.TfApplicationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class ExcelController {

	@Autowired
	private ExcelService excelService;

	//엑셀파일 재고 저장
	@RequestMapping("/stockUpload")
	public Map<String, String> stockUpload(@RequestParam(value = "excelFile", required = false) MultipartFile mf, HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		Map<String, String> map = new HashMap<String, String>();
		try {
			map = excelService.stockExcelUpload(mf, userId);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", "E");
		}
		return map;
	}




}
