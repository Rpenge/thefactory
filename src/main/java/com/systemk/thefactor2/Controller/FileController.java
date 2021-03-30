package com.systemk.thefactor2.Controller;


import java.io.File;
import java.util.HashMap;
import java.util.Map;


import com.systemk.thefactor2.Service.FileService;
import com.systemk.thefactor2.VO.TfApplicationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class FileController {

	@Autowired
	private FileService fileService;

	@RequestMapping(value="/appInfo", method = RequestMethod.GET)
	public TfApplicationVO appInfo(@RequestBody(required = false) Map map) throws Exception{
		return fileService.appInfo();
	}


	//어플리케이션 업로드
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public Map<String, String> upload(@RequestPart(value = "uploadApp", required = false) MultipartFile mf,
									  HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("");
		String addPath = "resources/APP/";
		Map<String, String> map = new HashMap<String, String>();
		try {
			String originalFileName = mf.getOriginalFilename();
			File fi = new File(path + addPath + originalFileName);
			mf.transferTo(fi);
			map.put("fileName", originalFileName);
			map.put("path", addPath);
			map.put("resultCode", "S");
		}catch(Exception e){
			map.put("resultCode", "E");
		}
		return map;
	}
	//어플리케이션 업로드 DB등록
	@RequestMapping(value="/uploadReg", method = RequestMethod.POST)
	public Map<String, Object> uploadReg(@RequestBody(required = false) Map map, HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		map.put("userId", userId);
		return fileService.appSave(map);
	}





}
