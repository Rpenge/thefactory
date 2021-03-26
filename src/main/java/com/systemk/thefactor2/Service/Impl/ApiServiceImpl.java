package com.systemk.thefactor2.Service.Impl;


import com.systemk.thefactor2.Config.ConstansConfig;
import com.systemk.thefactor2.Mapper.*;
import com.systemk.thefactor2.Service.*;
import com.systemk.thefactor2.Util.MybatisUtil;
import com.systemk.thefactor2.Util.ResultUtil;
import com.systemk.thefactor2.Util.StringUtil;
import com.systemk.thefactor2.VO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service
public class ApiServiceImpl implements ApiService {

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Autowired
	private TfUserMapper tfUserMapper;

	@Autowired
	private TfDeviceMapper tfDeviceMapper;

	@Autowired
	private TfApplicationMapper tfApplicationMapper;

	@Autowired
	private TfInputMapper tfInputMapper;

	@Autowired
	private TfTagPublishMapper tfTagPublishMapper;

	@Autowired
	private TfStockMapper tfStockMapper;

	@Autowired
	private TfProductMapper tfProductMapper;

	@Autowired
	private TfAcStockMapper tfAcStockMapper;

	@Autowired
	private TfOutputMapper tfOutputMapper;


	@Autowired
	private InoutTotService inoutTotService;

	@Autowired
	private CommService commService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private OutputService outputService;


	@Override
	public void apiReg(HttpServletRequest request) {
		Enumeration headers = request.getHeaderNames();
		while ( headers.hasMoreElements() ){
			String  name = (String) headers.nextElement();
			String  value = request.getHeader(name);
			if (value != null)
				System.out.println(name + " : " + value);
		}
	}

	@Override
	public Map<String, Object> pdaLogin(Map param) throws Exception {
		HashMap item = new HashMap();
		item.put("userId", param.get("userId"));

		TfDeviceVO device = tfDeviceMapper.serialSearch(param);
		TfApplicationVO lastVer = tfApplicationMapper.appLastVs();

		if(device == null){																				// 장비 등록 여부
			return ResultUtil.setCommonResult("E",ConstansConfig.NOT_FIND_DEVICE_MSG);
		}
		if(!lastVer.getVersion().equals(param.get("version"))){											//어플 버전 확인
			item = new HashMap();
			item.put("appDownUrl", "http://"+param.get("appDownUrl")+"/#/member/appDown");
			return ResultUtil.setCommonResult("U",ConstansConfig.VERSION_UPDATE_MSG, item);
		}

		TfUserVO user = tfUserMapper.login(item);
		try {
			item.put("userNm", user.getUserNm());
			item.put("storeCd", user.getStoreCd());
			item.put("gradeCd", user.getGrade());
			item.put("storeNm", commService.codeToNm(user.getStoreCd()));
			item.put("grade", commService.codeToNm(user.getGrade()));
		}catch(Exception e){
			return ResultUtil.setCommonResult("E",ConstansConfig.NOT_FIND_USER_MSG);
		}
		if(!passwordEncoder.matches((CharSequence) param.get("userPwd"), user.getUserPwd())){ 	// 비밀번호 검증 실패
			return ResultUtil.setCommonResult("E",ConstansConfig.NOT_VALID_PASSWORD_MSG);
		} else if(user.getUserStat().equals("N")){ 														// 계정 사용가능 여부
			return ResultUtil.setCommonResult("E",ConstansConfig.NOT_USE_USER_MSG);
		} else if(user.getPdaUseYn().equals("N")){ 														// PDA 사용가능 여부
			return ResultUtil.setCommonResult("E",ConstansConfig.NOT_CHECK_ADMIN_MSG);
		}

		return ResultUtil.setCommonResult("S",ConstansConfig.LOGIN_SUCCESS_MSG, item);
	}



	@Override
	public Map<String, Object> workCount(Map param) throws Exception {


		Date date = new Date();

		Map map = new HashMap();
		if(param.get("storeCd")!=null){
			TfInoutTotalVO vo = inoutTotService.todayWork(param);
			map.put("inputCount", vo.getInTotcnt());
			map.put("outputCount", vo.getOutTotcnt());
			map.put("stockCount", vo.getStockTotcnt());
		}else{
			map = inoutTotService.todayWorkAll();
		}
		map.put("currentDate", StringUtil.dateFormat(date));
		return ResultUtil.setCommonResult("S","성공하였습니다", map);
	}


	@Override
	public Map<String, Object> inputList(Map param) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		List list = new ArrayList();
		mu.addBetween("ST_IN_DATE", (String)param.get("startDate"), (String)param.get("endDate"));
		if(param.get("state") != null){
			mu.addEqual("ST_IN_TYPE", (String)param.get("state"));
		}
		mu.setTotal();
		List<TfInputVO> voList = tfInputMapper.inputList(mu.getTableSearch()); //리스트 조회
		for(TfInputVO vo : voList){
			Map map = new HashMap();
			map.put("seq", vo.getStInSeq());
			map.put("name", vo.getTfPrdNm());
			map.put("tagId", vo.getTfPrdTagid());
			map.put("regDate", StringUtil.dateFormat(vo.getRegDate()));
			list.add(map);
		}

		return ResultUtil.setCommonResult("S","성공하였습니다",list);
	}



	@Override
	public Map<String, Object> searchPrd(Map param) throws Exception{
		Map map = new HashMap();
		Date date = new Date();
		String ymd = StringUtil.dateFormatYMD(date);

		String barcode = (String)param.get("barcode");
		String preTagId = "T" + ymd.substring(2,4)+ barcode.substring(barcode.length()-10, barcode.length());
		String tagId ="";
		String lastNum = tfTagPublishMapper.selectLastNum(preTagId);
		if(lastNum == null || lastNum == ""){
			tagId = preTagId + "001";
		}else{
			tagId = preTagId + String.format("%03d", Integer.parseInt(lastNum) + 1);
		}
		TfStockVO vo = tfStockMapper.findStockInfo(barcode);
		try {
			map.put("prdCd", vo.getTfPrdCd());
			map.put("prdName", vo.getTfPrdNm());
			map.put("prdSeason", vo.getTfPrdNm().split(" ")[0]);
			map.put("tagId", tagId);
			map.put("brand", brandService.codeToNm(vo.getBrandKindCd().substring(0, 2) + "0000"));
			map.put("brandCd", vo.getBrandKindCd());
			map.put("prdSize", vo.getPrdSize());
			map.put("prdSizeCd", vo.getPrdSizeCd());
		}catch (Exception e){
			return ResultUtil.setCommonResult("E",ConstansConfig.NOT_FIND_STOCK_MSG);
		}
		return ResultUtil.setCommonResult("S","성공하였습니다",map);
	}

	@Override
	public Map<String, Object> inputWork(Map param) throws Exception {
		Map map = new HashMap();
		Date date = new Date();
		Map mapData = tfProductMapper.prdAndStk(param);

		map.put("ymd", StringUtil.dateFormatYMD(date));		//오늘날짜
		map.put("userId", 	param.get("userId"));		//현재유저
		map.put("brandCd", 	mapData.get("BRAND_KIND_CD"));	//상품정보에서
		map.put("prdNm", 	mapData.get("TF_PRD_NM"));		//상품정보에서
		map.put("ecPrdCd", 	mapData.get("EC_PRD_CD"));	//상품정보에서
		map.put("prdCd", 	mapData.get("TF_PRD_CD"));		//상품정보에서
		map.put("barcode", 	param.get("barcode"));	//  가져온 바코드
		map.put("size", 	mapData.get("PRD_SIZE"));		//재고정보에서
		map.put("tagId", 	param.get("tagId"));		//  가져온 태그id
		map.put("storeCd", 	param.get("storeCd"));	//  입고매장
		map.put("storeNm", 	commService.codeToNm((String)param.get("storeCd")));	//  입고매장명
		map.put("tagStat", 	"040101");	//신규발행 코드 : 고정
		map.put("deviceGub",param.get("deviceGub"));	//장비값 : PDA코드 : 헤더값
		map.put("inType", 	param.get("state"));	//신규입고 코드 : 신규/입고

		tfInputMapper.inputNew((HashMap) map);
		return ResultUtil.setCommonResult("S","성공하였습니다");
	}


//	@Override
//	public Map<String, Object> inputAddResult(Map param) throws Exception {
//
//		Map map = new HashMap();
//		Map outputData = outputService.outputSearch((String) param.get("tagId"));
//		map.put("barcode", outputData.get("BT_PRD_BARCODE"));
//		Map mapData = tfProductMapper.prdAndStk(param);
//
//		Date date = new Date();
//		map.put("ymd", StringUtil.dateFormatYMD(date));		//오늘날짜
//		map.put("userId", 	param.get("userId"));		//현재유저
//		map.put("brandCd", 	mapData.get("BRAND_KIND_CD"));	//상품정보에서
//		map.put("prdNm", 	mapData.get("TF_PRD_NM"));		//상품정보에서
//		map.put("ecPrdCd", 	mapData.get("EC_PRD_CD"));	//상품정보에서
//		map.put("prdCd", 	mapData.get("TF_PRD_CD"));		//상품정보에서
//		map.put("barcode", 	mapData.get("TF_PRD_BARCODE"));	//  가져온 바코드
//		map.put("size", 	mapData.get("PRD_SIZE"));		//재고정보에서
//		map.put("tagId", 	param.get("tagId"));		//  가져온 태그id
//		map.put("storeCd", 	param.get("inStoreCd"));	//  입고매장
//		map.put("storeNm", 	commService.codeToNm((String)param.get("inStoreCd")));	//  입고매장명
//		map.put("outStoreCd", 	outputData.get("outStoreCd"));	//  출고매장
//		map.put("outStoreNm", 	outputData.get("outStoreNm"));	//  출고매장명
//		map.put("deviceGub",param.get("deviceGub"));	//장비값 :
//		map.put("inType", 	param.get("stInType"));	//입고 코드
//
//		tfInputMapper.inputRe((HashMap) map);
//		return ResultUtil.setCommonResult("S","성공하였습니다");
//	}

	@Override
	public Map<String, Object> outDataSearch(List<Map<String, String>> param) throws Exception {

		List<Map<String, String>> reusltList = new ArrayList<Map<String, String>>();
		for(Map<String, String> map : param){
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("tagId",map.get("tagId"));
			String prdNm = tfAcStockMapper.stockCheck(map.get("tagId"));
			if(prdNm != null){
				resultMap.put("prdNm",prdNm);
				resultMap.put("mappingYn","Y");
			}else{
				resultMap.put("mappingYn","N");
			}
			reusltList.add(resultMap);

		}
		return ResultUtil.setCommonResult("S","성공하였습니다", reusltList);
	}



	@Override
	public Map<String, Object> outputList(Map param) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		List list = new ArrayList();
		mu.addBetween("ST_OUT_DATE", (String)param.get("startDate"), (String)param.get("endDate"));
		mu.setTotal();

		String state = (String)param.get("state");
		if (state.substring(4,6).equals("00")){
			mu.addEqual("substring(ST_OUT_TYPE,1,4)", state.substring(0, 4));
		}else {
			mu.addEqual("ST_OUT_TYPE", state);
		}


		List<TfOutputVO> voList = tfOutputMapper.outList(mu.getTableSearch()); //리스트 조회
		for(TfOutputVO vo : voList){
			Map map = new HashMap();
			map.put("seq", vo.getStOutSeq());
			map.put("name", vo.getTfPrdNm());
			map.put("regDate", StringUtil.dateFormat(vo.getRegDate()));
			list.add(map);
		}

		return ResultUtil.setCommonResult("S","성공하였습니다",list);
	}

	@Override
	public Map<String, Object> outputAdd(List<Map<String, String>> paramList, String userId, String device) throws Exception {

		for(Map param : paramList){
			TfAcStockVO vo = tfAcStockMapper.findStockByTagId((String)param.get("tagId")); // 실재고 정보 조회
			Map map = new HashMap();
			map.put("barcode", vo.getTfPrdBarcode());
			Map mapData = tfProductMapper.prdAndStk(map);

			Date date = new Date();
			map.put("ymd", StringUtil.dateFormatYMD(date));
			map.put("userId", 	userId);
			map.put("brandCd", 	mapData.get("BRAND_KIND_CD"));
			map.put("prdNm", 	mapData.get("TF_PRD_NM"));
			map.put("ecPrdCd", 	mapData.get("EC_PRD_CD"));
			map.put("prdCd", 	mapData.get("TF_PRD_CD"));
//			map.put("barcode", 	vo.getTfPrdBarcode());  //
			map.put("size", 	mapData.get("PRD_SIZE"));
			map.put("tagId", 	param.get("tagId"));
			if(param.get("state").equals("060202")) {
				map.put("inStoreCd", param.get("storeCd"));
				map.put("inStoreNm", commService.codeToNm((String)param.get("storeCd")));
			}
			map.put("outStoreCd", vo.getStoreCd());	//  출고매장
			map.put("outStoreNm", commService.codeToNm(vo.getStoreCd()));
			map.put("deviceGub",device);
			map.put("outType", 	param.get("state"));

 			tfOutputMapper.outputAdd((HashMap) map);
		}

		return ResultUtil.setCommonResult("S","성공하였습니다");
	}


}
