package com.systemk.thefactor2.Controller;


import com.systemk.thefactor2.Service.ExcelService;
import com.systemk.thefactor2.Util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.util.calendar.CalendarUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
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
		String realPath = "D:/theFactor2RFID/EXCEL/";
		createFolder(realPath);
		try {
			map = excelService.stockExcelUpload(mf, userId);	//엑셀 파일 데이터 저장

			String originalFileName = mf.getOriginalFilename();
			String[] ofn = originalFileName.split("\\.");
			String name = ofn[0];
			String extension = ofn[1];
			File fi = new File(realPath + name+"_S"+ StringUtil.dateFormat("yyyyMMddHHmmss", new Date()) +"."+extension);
			mf.transferTo(fi);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", "E");
		}
		return map;
	}

	//엑셀파일 상품정보 저장
	@RequestMapping("/productUpload")
	public Map<String, String> productUpload(@RequestParam(value = "excelFile", required = false) MultipartFile mf, HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		Map<String, String> map = new HashMap<String, String>();
		String realPath = "D:/theFactor2RFID/EXCEL/";
		createFolder(realPath);
		try {
			map = excelService.productExcelUpload(mf, userId);

			String originalFileName = mf.getOriginalFilename();
			String[] ofn = originalFileName.split("\\.");
			String name = ofn[0];
			String extension = ofn[1];
			File fi = new File(realPath + name+"_P"+ StringUtil.dateFormat("yyyyMMddHHmmss", new Date()) +"."+extension);
			mf.transferTo(fi);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", "E");
		}
		return map;
	}

	@RequestMapping("/brandUpload")
	public Map<String, String> brandUpload(@RequestParam(value = "excelFile", required = false) MultipartFile mf, HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		Map<String, String> map = new HashMap<String, String>();
		String realPath = "D:/theFactor2RFID/EXCEL/";
		createFolder(realPath);
		try {
			map = excelService.brandExcelUpload(mf, userId);

			String originalFileName = mf.getOriginalFilename();
			String[] ofn = originalFileName.split("\\.");
			String name = ofn[0];
			String extension = ofn[1];
			File fi = new File(realPath + name+"_B"+ StringUtil.dateFormat("yyyyMMddHHmmss", new Date()) +"."+extension);
			mf.transferTo(fi);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", "E");
		}
		return map;
	}



	public void createFolder(String path){
		File folder = new File(path);
		if (folder.exists()) {
			return;
		}
		String[] sPath = path.split("/");
		File folder1 = new File(sPath[0]+"/"+sPath[1]);
		if(!folder1.exists()){
			folder1.mkdir();
		}
		if(sPath.length>=3) {
			File folder2 = new File(sPath[0] + "/" + sPath[1] + "/" + sPath[2]);
			folder2.mkdir();
		}
	}



}
