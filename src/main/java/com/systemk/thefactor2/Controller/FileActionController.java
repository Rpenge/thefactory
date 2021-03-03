//package com.systemk.ams.Controller;
//
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
////import com.systemk.ams.Service.AssetManagementService;
////import com.systemk.ams.Service.FileActionService;
//import com.systemk.ams.Util.FileUploadUtil;
//
//@RestController
//public class FileActionController {
//
//
//
//	@Value("${asset_img_path}")
//	String path;
//
//	//이미지 업로드
//	@RequestMapping("/upload")
//	public Map<String, String> upload(@RequestPart("uploadImg") MultipartFile mf, @RequestParam(value = "name", required = false) String name) {
//		Map<String, String> map = new HashMap<String, String>();
//		name = startStrCheck(name);
//		try {
//			FileUploadUtil fu = new FileUploadUtil();
//			fu.upload(mf, name, path);
//			map.put("resultCode", "s");
//		}catch(Exception e){
//			map.put("resultCode", "e");
//		}
//		return map;
//	}
//
//
//
//
//	public String startStrCheck(String str) {
//		if(str.charAt(0) == ',') {
//			str = str.substring(1);
//		}
//		return str;
//	}
//
//}
