package com.systemk.thefactor2.Controller;

import com.systemk.thefactor2.Service.CommService;
import com.systemk.thefactor2.Service.UserService;
import com.systemk.thefactor2.Util.RequestUtil;
import com.systemk.thefactor2.VO.TfCommCodeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/base")
public class BaseController {

	@Autowired
	private CommService commService;

	//기초정보관리 - 코드관리 - 코드 리스트 조회(페이지 처리)
	@RequestMapping(value="/commList", method = RequestMethod.GET)
	public List<TfCommCodeVO> commList(HttpServletRequest request) throws Exception{
		return commService.commList();
	}

	/*//기초정보관리 - 코드관리 - 코드 추가
	@RequestMapping(value="/commSave", method = RequestMethod.POST)
	public Map commSave(@RequestBody(required = false) Map<String, String> map) throws Exception{
		return userService.userSave(map);
	}

	//기초정보관리 - 코드관리 - 코드 수정
	@RequestMapping(value="/commUpdate", method = RequestMethod.POST)
	public Map commUpdate(@RequestBody(required = false) Map<String, String> map) throws Exception{
		return userService.userUpdate(map);
	}

	//기초정보관리 - 코드관리 - 코드 삭제(update처리)
	@RequestMapping(value="/commDelete", method = RequestMethod.POST)
	public Map commDelete(@RequestBody(required = false) Map<String, Object> map) throws Exception{
		List<Integer> list = (List)map.get("prList");
		return userService.userWd(list);
	}*/

}
