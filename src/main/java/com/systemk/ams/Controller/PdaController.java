package com.systemk.ams.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import com.systemk.ams.Entity.Main.AssetManagement;
//import com.systemk.ams.Entity.Main.UserInfo;
//import com.systemk.ams.Service.CommonService;
//import com.systemk.ams.Service.PdaService;



@RestController
@RequestMapping("/pda")
public class PdaController {

//	@Autowired
//	private PdaService pdaService;
//
//	@Autowired
//	private CommonService commonService;



//	//PDA 로그인
//	@RequestMapping(value="/login", method = RequestMethod.POST)
//	public Map<String, Object> login(@RequestBody(required=false) UserInfo user)throws Exception {
//		Map<String, Object> obj = new HashMap<String, Object>();
//		Map<String, Object> data = new HashMap<String, Object>();
//		if(user.getUserId()==null && user.getPassword()==null) {
//			return null;
//		}
//		UserInfo loginUser = pdaService.loginService(user);
//
//		if(loginUser != null) {
//			data.put("userId", loginUser.getUserId());
//			data.put("userName", loginUser.getEmpName());
//			data.put("role", loginUser.getEmpAuthorization());
//			data.put("roleList", pdaService.menuService(loginUser.getEmpAuthorization()));
//			obj.put("data", data);
//			obj.put("resultMessage", "로그인");
//			obj.put("resultCode", "s");
//			return obj;
//		}else {
//			obj.put("resultCode", "e");
//			obj.put("resultMessage", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다");
//			return obj;
//		}
//	}

//	//자산 데이터 가져오기
//	@RequestMapping(value="/asset", method = RequestMethod.POST)
//	public Map<String, Object> asset(@RequestBody(required = false) Map<String, Object> rdata) throws Exception {
//		Map<String, Object> obj = new HashMap<String, Object>();
//		Map<String, Object> data = new HashMap<String, Object>();
//		List<AssetManagement> masterData = pdaService.assetList((String)rdata.get("userId"));
//
//		if(masterData != null) {
//			data.put("masterData", masterData);
//			data.put("commonCode", commonService.codeSearchAll());
//			obj.put("data", data);
//			obj.put("resultCode", "s");
//			obj.put("resultMessage", "데이터 전송 성공");
//		}else {
//			obj.put("resultCode", "e");
//			obj.put("resultMessage", "데이터 조회 오류");
//		}
//		return obj;
//	}


//	@RequestMapping(value="/asset", method = RequestMethod.PUT)
//	public Map<String, Object> assetUpdate(@RequestBody(required = false) List<Map<String, String>> data) throws Exception {
//		Map<String, Object> obj = new HashMap<String, Object>();
//		Boolean result = false;
//		Map<String, Object> dataMap = pdaService.dataCheck(data);
//		List<String> failList = (List) dataMap.get("failList");
//		List<Map<String, String>> assetList = (List) dataMap.get("assetList");
//		if(failList.isEmpty()) {
//			result = pdaService.assetProcess(assetList);
//		}else {
//			pdaService.updateFail(assetList);
//			obj.put("failList", failList);
//			obj.put("resultCode", "e");
//			obj.put("resultMessage", "자산데이터 조회 실패");
//			return obj;
//		}
//
//		if(result) {
//			obj.put("resultCode", "s");
//			obj.put("resultMessage", "업데이트 완료");
//		}else {
//			obj.put("resultCode", "e");
//			obj.put("resultMessage", "DB업데이트 실패");
//		}
//		return obj;
//	}
}
