//package com.systemk.thefactor2.Controller;
//
//import java.io.File;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RestController
//public class PdaLogUploadController {
//	
//	/**
//	 * PDA 로그 파일 저장 위치
//	 */
//	private final String SAVE_PATH = "D:/theFactor2PdaLog/";
//	
//	/**
//	 * PDA 로그 파일 저장 성공 메세지
//	 */
//	private final String SUCCESS_MESSAGE_KR = "로그 파일 전송 성공\n저장 위치 : " + SAVE_PATH;
//	
//	/**
//	 * PDA 로그 파일 저장
//	 * 파일 이름 : 매장 위치 코드_시간_파일명(날짜 포함)
//	 * 테스트 완료 : 210507
//	 * @param mf
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value="/api/uploadLog", method = RequestMethod.POST)
//	public Map<String, String> upload(@RequestPart(value = "logFile", required = false) MultipartFile mf, HttpServletRequest request) {
//		Map<String, String> map = new HashMap<String, String>();
//		
//		try {
//			File realPathDir = new File(SAVE_PATH);
//			
//			if(!realPathDir.exists()) { //파일 저장 폴더 없을 경우 추가
//				realPathDir.mkdir();
//			}
//			
//			File fi = new File(SAVE_PATH + mf.getOriginalFilename());
//
//			mf.transferTo(fi);
//			
//			map.put("resultCode", "S");
//			map.put("resultMessage", SUCCESS_MESSAGE_KR);
//		}catch(Exception e){
//			e.printStackTrace();
//			map.put("resultCode", "E");
//			map.put("resultMessage", e.getMessage());
//		}
//		
//		return map;
//	}
//}
