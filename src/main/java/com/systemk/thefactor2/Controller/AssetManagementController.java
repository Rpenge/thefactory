package com.systemk.thefactor2.Controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.systemk.thefactor2.Service.AssetManagementService;
import com.systemk.thefactor2.Util.ParamUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;

//import com.systemk.ams.Entity.Main.AssetManagement;
//import com.systemk.ams.Entity.Main.AssetRepair;
//import com.systemk.ams.Service.AssetManagementService;
import com.systemk.thefactor2.Util.QRCode;


@RestController
public class AssetManagementController {

	@Autowired
	private AssetManagementService assetManagementService;

	@Value("${asset_img_path}")
	private String assetPath;

	@Value("${asset_img_path_rel}")
	private String assetPathRel;

	@Value("${qr_img_path}")
	private String qrPath;

	@Value("${qr_img_path_rel}")
	private String qrPathRel;


	//자산목록 조회
	@RequestMapping(value = "/assetManagementList", method = RequestMethod.GET)
	public Map<String, Object> getAssetMgList(
												HttpServletRequest request) throws Exception {
		return assetManagementService.findList(new ParamUtil().requestGetParam(request));
	}


	//qr코드 인쇄
	@RequestMapping(value="/assetQRList", method = RequestMethod.POST)
	public List<Map<String, String>> assetQRList(@RequestBody(required = false) List<Map<String, String>> list) throws Exception{
		QRCode qr = new QRCode();
		for(int i=0;i<list.size();i++) {
			String qrCodePath = qr.QrCreate(list.get(i).get("assetControlCode"), qrPath);
			list.get(i).put("qr", qrPathRel + qrCodePath);
		}
		return list;
	}
}
