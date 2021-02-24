package com.systemk.ams.Controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.systemk.ams.Service.AssetManagementService;
import com.systemk.ams.Util.ParamUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;

//import com.systemk.ams.Entity.Main.AssetManagement;
//import com.systemk.ams.Entity.Main.AssetRepair;
//import com.systemk.ams.Service.AssetManagementService;
import com.systemk.ams.Util.FileUploadUtil;
import com.systemk.ams.Util.QRCode;


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


//	//자산상세 조회
//	@RequestMapping(value = "/assetDetail", method = RequestMethod.GET)
//	public Map<String, Object> getAssetDetail(@RequestParam(value="controlCode") String code) throws Exception {
//		QRCode qr = new QRCode();
//		String qrCodePath = qr.QrCreate(code, qrPath);
//		FileUploadUtil fu = new FileUploadUtil();
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		resultMap.put("qrPath", qrPathRel + qrCodePath);
//		resultMap.put("list", assetManagementService.findAssetDetail(code));
//		String fileName = fu.findFileNameExt(assetPath, code);
//		if(fileName != null) {
//			fileName = assetPathRel + fileName;
//			resultMap.put("imgPath", fileName );
//		}
//		return resultMap;
//	}

//	//자산 등록
//	@RequestMapping(value="/assetManagement", method = RequestMethod.POST)
//	public Map<String, String> assetReg(@RequestBody(required = false) AssetManagement list, HttpServletRequest request) throws Exception{
//		Map<String, String> map = new HashMap<String, String>();
//		String userId = (String) request.getSession().getAttribute("userId");
//		AssetManagement asset = assetManagementService.assetReg(list, userId);
//		map.put("controlCode", asset.getAssetControlCode());
//		return map;
//	}
//
//	//자산 정보 수정
//	@RequestMapping(value="/assetManagement", method = RequestMethod.PUT)
//	public void assetModi(@RequestBody(required = false) AssetManagement list, HttpServletRequest request) throws Exception{
//		String userId = (String) request.getSession().getAttribute("userId");
//		assetManagementService.assetModi(list, userId);
//	}
//
//	//자산 삭제 , 상태정보 변경, 태그발행 정보 변경
//	@RequestMapping(value="/assetManagement/status", method = RequestMethod.PUT)
//	public void assetDelete(@RequestBody(required = false) Map<String, Object> map, HttpServletRequest request) throws Exception{
//		String userId = (String) request.getSession().getAttribute("userId");
//		List<Integer> list = (List)map.get("list");
//		String command = (String)map.get("command");
//		if(command.equals("DEL")) {
//			assetManagementService.assetDelete(list, userId);
//		}else if(command.equals("TagY") || command.equals("TagN")) {
//			assetManagementService.assetTagUpdate(list, command, userId);
//		}else {
//			assetManagementService.statusUpdate(list, command, userId);
//		}
//	}
//	//수리 등록
//	@RequestMapping(value="/assetManagement/repairReg", method = RequestMethod.PUT)
//	public void repairReg(@RequestBody(required = false) Map<String, Object> map){
//		List<AssetRepair> repairList = assetManagementService.createAssetRepair(map);
//	}


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
