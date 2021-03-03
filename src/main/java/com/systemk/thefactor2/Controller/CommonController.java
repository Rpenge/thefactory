//package com.systemk.ams.Controller;
//
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import com.systemk.ams.mapper.TfUserAuthMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//
//@RestController
//public class CommonController {
//
//
//
//	@Autowired
//	private TfUserAuthMapper menuMapper;
//	//공통코드, 메뉴 다시 가져오기
//	@RequestMapping("/reload")
//	public Map code(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		HashMap sessionMap = new HashMap<>();
//		String id = (String)session.getAttribute("userId");
//		String role = (String)session.getAttribute("role");
//
//		sessionMap.put("role", role);
////		sessionMap.put("menu", menuSort(menuMapper.menuSearch(sessionMap), "AMS"));
//
//
//		return sessionMap;
//	}
//
//	//메뉴 정리
//	public HashMap<String, Object> menuSort(List<HashMap<String, Object>> menu, String parent){
//		HashMap<String, Object> result = new HashMap<String, Object>();
//		List list = new ArrayList();
//		for (HashMap<String, Object> item : menu) {
//			if(item.get("parent_code").equals(parent)){
//				list.add(item);
//				if(item.get("menu_code").toString().length()!=2) {
//					HashMap<String, Object> reData = menuSort(menu, (String) item.get("menu_code"));
//					item.put("child", reData);
//				}
//			}
//		}
//		result.put("parent", list);
//		return result;
//	}
//
//}
