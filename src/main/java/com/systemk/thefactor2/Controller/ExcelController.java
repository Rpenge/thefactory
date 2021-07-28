package com.systemk.thefactor2.Controller;


import com.systemk.thefactor2.Service.ExcelService;
import com.systemk.thefactor2.Util.FileUploadUtil;
import com.systemk.thefactor2.Util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		FileUploadUtil.createFolder(realPath);
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
		FileUploadUtil.createFolder(realPath);
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

	// brand excel 파일 업로드
	@RequestMapping("/brandUpload")
	public Map<String, String> brandUpload(@RequestParam(value = "excelFile", required = false) MultipartFile mf, HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		Map<String, String> map = new HashMap<String, String>();
		String realPath = "D:/theFactor2RFID/EXCEL/";
		FileUploadUtil.createFolder(realPath);
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

	//입출고 엑셀 파일 다운로드
	@RequestMapping("/excelDown")
	public void excelDown(@RequestBody(required = false) Map<String, Object> map, HttpServletResponse response) throws Exception {
		excelService.inoutExcelDown(map, response);
	}

	//보유 재고 엑셀 파일 다운로드
	@RequestMapping("/stockExcelDown")
	public void stockExcelDown(@RequestBody(required = false) Map<String, Object> map, HttpServletResponse response) throws Exception {
		excelService.stockExcelDown(map, response);
	}

	//전체 재고 엑셀 다운로드
	@RequestMapping("/stkBaseExcelDown")
	public void stkBaseExcelDown(@RequestBody(required = false) Map<String, Object> map, HttpServletResponse response) throws Exception {
		excelService.stkBaseExcelDown(map, response);
	}

}
