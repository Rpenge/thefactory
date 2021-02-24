package com.systemk.ams.Controller;


import javax.servlet.http.HttpServletRequest;

import com.systemk.ams.Util.JsonUtil;
import com.systemk.ams.Util.ParamUtil;
import com.systemk.ams.VO.AssetChangeVO;
import net.minidev.json.JSONArray;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;

//import com.systemk.ams.Service.AssetChangeService;

import java.util.List;
import java.util.Map;


@RestController
public class AssetChangeController {

//	@Autowired
//	private AssetChangeService assetChangeService;


//	//RFID 사용 내역 조회   JPA변경
//	@RequestMapping(value = "/rfidChange", method = RequestMethod.GET)
//	public Page<AssetChange> getRfidChange(@PageableDefault(sort = {"chgDt","astChgSeq"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable,
//											HttpServletRequest request) throws Exception {
//		return assetChangeService.findRfidUse(new ParamUtil().requestGetParam(request), pageable);
//	}


//	//RFID 사용 내역 조회
//	@RequestMapping(value = "/rfidChange", method = RequestMethod.GET)
//	public Map<String, Object> getRfidChange(HttpServletRequest request, Pageable pageable) throws Exception {
//		return assetChangeService.findRfidUse(new ParamUtil().requestGetParam(request));
//	}


}
