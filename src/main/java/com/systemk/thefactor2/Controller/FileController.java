package com.systemk.thefactor2.Controller;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.systemk.thefactor2.Service.FileService;
import com.systemk.thefactor2.VO.TfApplicationVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FileController {

	@Autowired
	private FileService fileService;

	@RequestMapping(value="/appInfo", method = RequestMethod.POST)
	public TfApplicationVO appInfo(@RequestBody(required = false) Map<String, Object> map) throws Exception{
		return fileService.appInfo(map);
	}

	//어플리케이션 업로드
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public Map<String, String> upload(@RequestPart(value = "uploadApp", required = false) MultipartFile mf,
									  HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("");
		String addPath = "resources/APP/";
		String realPath = "D:/theFactor2RFID/APP/";
		createFolder(realPath);
		Map<String, String> map = new HashMap<String, String>();
		try {
			String originalFileName = mf.getOriginalFilename();
			File fi = new File(realPath + originalFileName);

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
