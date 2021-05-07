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
import org.springframework.transaction.annotation.Transactional;

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
	private TfInoutTotalMapper tfInoutTotalMapper;

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
	private TfInventoryMapper tfInventoryMapper;

	@Autowired
	private TfInvStatusMapper tfInvStatusMapper;


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
		TfApplicationVO lastVer = tfApplicationMapper.appLastVs(param);

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
//		if(param.get("storeCd")!=null){
		TfInoutTotalVO vo = inoutTotService.todayWork(param);
		map.put("inputCount", vo.getInTotcnt());
		map.put("outputCount", vo.getOutTotcnt());
		map.put("stockCount", vo.getStockTotcnt());
		map.put("offlineSellCount", vo.getSellStcnt());
		map.put("onlineSellCount", vo.getSellOnlcnt());
//		}else{
//			map = inoutTotService.todayWorkAll();
//		}
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
		Map mapData = tfProductMapper.prdAndStk(param);
		String brand = (String)mapData.get("BRAND_KIND_CD");
		String prdNm = (String)mapData.get("TF_PRD_NM");
		try {
			map.put("prdCd", mapData.get("TF_PRD_CD"));
			map.put("prdName", prdNm);
			map.put("prdSeason", prdNm.split(" ")[0]);
			map.put("tagId", tagId);
			map.put("brand", brandService.codeToNm(brand.substring(0, 2) + "0000"));
			map.put("brandCd", brand);
			map.put("prdSize", mapData.get("PRD_SIZE"));
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.setCommonResult("E",ConstansConfig.NOT_FIND_STOCK_MSG);
		}
		return ResultUtil.setCommonResult("S","성공하였습니다",map);
	}

	@Override
	public Map<String, Object> inputWork(Map param) throws Exception {
		Map map = new HashMap();
		Date date = new Date();
		Map mapData = tfProductMapper.prdAndStk(param);

		if(mapData == null){
			return ResultUtil.setCommonResult("E",ConstansConfig.NOT_FIND_STOCK_MSG);
		}

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

	//반품, 점간이동입고
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Map<String, Object> inputReWork(Map param) throws Exception {
		Map map = new HashMap();
		Map outputData = outputService.outputSearch((String) param.get("tagId"));

		if(outputData == null){
			throw new Exception(ConstansConfig.NOT_FIND_OUTPUT_MSG);
//			return ResultUtil.setCommonResult("E",ConstansConfig.NOT_FIND_RELEASE_RFID_TAG_MSG);
		}

		param.put("barcode", outputData.get("btPrdBarcode"));
		Map mapData = tfProductMapper.prdAndStk(param);
		if(mapData == null){
//			return ResultUtil.setCommonResult("E",ConstansConfig.NOT_FIND_STOCK_MSG);
			throw new Exception(ConstansConfig.NOT_FIND_STOCK_MSG);
		}

		Date date = new Date();
		map.put("ymd", StringUtil.dateFormatYMD(date));
		map.put("userId", 	param.get("userId"));
		map.put("brandCd", 	mapData.get("BRAND_KIND_CD"));
		map.put("prdNm", 	mapData.get("TF_PRD_NM"));
		map.put("ecPrdCd", 	mapData.get("EC_PRD_CD"));
		map.put("prdCd", 	mapData.get("TF_PRD_CD"));
		map.put("barcode", 	param.get("barcode"));
		map.put("size", 	mapData.get("PRD_SIZE"));
		map.put("tagId", 	param.get("tagId"));
		map.put("storeCd", 	param.get("storeCd"));
		map.put("storeNm", 	commService.codeToNm((String)param.get("storeCd")));
		map.put("outStoreCd", 	outputData.get("outStoreCd"));
		map.put("outStoreNm", 	outputData.get("outStoreNm"));
		map.put("deviceGub",param.get("deviceGub"));
		map.put("inType", 	param.get("state"));

		tfInputMapper.inputRe((HashMap) map);
		return ResultUtil.setCommonResult("S","성공하였습니다");
	}

	//출고테이블 tagId로 판매 데이터 조회
	@Override
	public Map<String, Object> saleDataSearch(List<Map<String, String>> param) throws Exception {
		List<Map> resultList = new ArrayList<Map>();
		for(Map paramMap : param){
			Map map = new HashMap();
			map.put("tagId", paramMap.get("tagId"));
			map.put("stOutType", "0603");
			TfOutputVO vo = tfOutputMapper.outWorkSearch(map);
			Map resultMap = new HashMap();
			if(vo == null){
				resultMap.put("tagId", paramMap.get("tagId"));
				resultMap.put("mappingYn", "N");
				resultList.add(resultMap);
				continue;
			}
			resultMap.put("prdNm", vo.getTfPrdNm());
			resultMap.put("tagId", vo.getTfPrdTagid());
			resultMap.put("regDate", StringUtil.dateFormat(vo.getRegDate()));
			resultMap.put("mappingYn", "Y");
			resultList.add(resultMap);
		}

		return ResultUtil.setCommonResult("S","성공하였습니다", resultList);
	}

	//출고테이블 tagId로 점간출고 데이터 조회
	@Override
	public Map<String, Object> moveOutDataSearch(List<Map<String, String>> param) throws Exception {
		List<Map> resultList = new ArrayList<Map>();
		for(Map paramMap : param){
			Map map = new HashMap();
			map.put("tagId", paramMap.get("tagId"));
			map.put("stOutType", "060202");
			TfOutputVO vo = tfOutputMapper.outWorkSearch(map);
			Map resultMap = new HashMap();
			if(vo == null){
				resultMap.put("tagId", paramMap.get("tagId"));
				resultMap.put("mappingYn", "N");
				resultList.add(resultMap);
				continue;
			}
			resultMap.put("prdNm", vo.getTfPrdNm());
			resultMap.put("tagId", vo.getTfPrdTagid());
			resultMap.put("regDate", StringUtil.dateFormat(vo.getRegDate()));
			resultMap.put("mappingYn", "Y");
			resultList.add(resultMap);
		}

		return ResultUtil.setCommonResult("S","성공하였습니다", resultList);
	}

	//반품, 점간이동입고
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Map<String, Object> inputReWorkList(List<Map> paramList, Map data) throws Exception {

		String resultCode = "";
		String resultMessage = "";

		for(Map param : paramList){
			Map outputData = outputService.outputSearch((String) param.get("tagId"));
			if(outputData == null){
				throw new Exception(ConstansConfig.NOT_FIND_RELEASE_RFID_TAG_MSG);
			}
			param.put("barcode", outputData.get("btPrdBarcode"));
			Map mapData = tfProductMapper.prdAndStk(param);
			if(mapData == null){
				throw new Exception(ConstansConfig.NOT_FIND_STOCK_MSG);
			}
			Map map = new HashMap();
			Date date = new Date();
			map.put("ymd", StringUtil.dateFormatYMD(date));
			map.put("userId", 	data.get("userId"));
			map.put("brandCd", 	mapData.get("BRAND_KIND_CD"));
			map.put("prdNm", 	mapData.get("TF_PRD_NM"));
			map.put("ecPrdCd", 	mapData.get("EC_PRD_CD"));
			map.put("prdCd", 	mapData.get("TF_PRD_CD"));
			map.put("barcode", 	param.get("barcode"));
			map.put("size", 	mapData.get("PRD_SIZE"));
			map.put("tagId", 	param.get("tagId"));
			map.put("storeCd", 	param.get("storeCd"));
			map.put("storeNm", 	commService.codeToNm((String)param.get("storeCd")));
			map.put("outStoreCd", 	outputData.get("outStoreCd"));
			map.put("outStoreNm", 	outputData.get("outStoreNm"));
			map.put("deviceGub",data.get("deviceGub"));
			map.put("inType", 	param.get("state"));

			tfInputMapper.inputRe((HashMap) map);

		}
		return ResultUtil.setCommonResult("S","성공하였습니다");
	}

	@Override
	public Map<String, Object> outDataSearch(List<Map<String, String>> param) throws Exception {

		List<Map<String, String>> reusltList = new ArrayList<Map<String, String>>();
		for(Map<String, String> map : param){
			Map resultMap = new HashMap<String, String>();
			resultMap.put("tagId",map.get("tagId"));
			Map stMap = tfAcStockMapper.stockCheck(map.get("tagId"));
			if(stMap != null){
				resultMap.put("prdNm", stMap.get("tfPrdNm"));
				resultMap.put("regDate",StringUtil.dateFormat((Date) stMap.get("regDate")));
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
			map.put("tagId", vo.getTfPrdTagid());
			list.add(map);
		}
		return ResultUtil.setCommonResult("S","성공하였습니다",list);
	}

	@Override
	public Map<String, Object> outputAdd(List<Map<String, String>> paramList, String userId, String device) throws Exception {

		for(Map param : paramList){
			TfAcStockVO vo = tfAcStockMapper.findStockByTagId((String)param.get("tagId")); // 실재고 정보 조회
			if(vo == null){
				return ResultUtil.setCommonResult("E",ConstansConfig.NOT_FIND_RFID_TAG_MSG);
			}
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

	@Override
	public Map<String, Object> findAcStkList(Map param) throws Exception {
		List<Map> acList = tfAcStockMapper.findAcStock((String)param.get("storeCd"));

		Map resultMap = new HashMap();
		for(Map map : acList){
			if(resultMap.containsKey(map.get("TF_PRD_BARCODE"))){
				Map mapData = (Map) resultMap.get(map.get("TF_PRD_BARCODE"));
				List tagList = (List) mapData.get("tagList");
				tagList.add(map.get("TF_PRD_TAGID"));
				mapData.put("tagList", tagList);
				mapData.put("stockCount", tagList.size());
			}else {
				Map tempMap = new HashMap();
				List tagList = new ArrayList<>();
				tempMap.put("name", map.get("TF_PRD_NM"));
				tempMap.put("size", map.get("PRD_SIZE"));
				tempMap.put("barcode", map.get("TF_PRD_BARCODE"));
				tagList.add(map.get("TF_PRD_TAGID"));
				tempMap.put("stockCount", tagList.size());
				tempMap.put("tagList", tagList);
				resultMap.put(map.get("TF_PRD_BARCODE"), tempMap);
			}
		}
		List list = new ArrayList<>(resultMap.values());
		return ResultUtil.setCommonResult("S","성공하였습니다", list);
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public Map<String, Object> addInven(Map param) throws Exception {
		Date date = new Date();
		param.put("stInvDate", StringUtil.dateFormatYMD(date));
		List<String> tagList = (List) param.get("tagList");
		param.remove("tagList");

		TfInvStatusVO vo = tfInvStatusMapper.findInvStatus(param);
		int stInvCnt = 0;

		if(vo == null){
			List<Map> acList = tfAcStockMapper.findAcStock((String)param.get("storeCd")); // 선택 매장의 실재고 데이터
			param.put("stTarCnt", acList.size());
			vo = tfInvStatusMapper.createInvStatus(param); //재고실사 시작시 신규현황을 생성하고 키값 리턴

			for(Map map : acList){
				Map invMap = new HashMap();
				invMap.put("stInvSeq", vo.getStInvSeq());
				invMap.put("tfPrdTagid", map.get("TF_PRD_TAGID"));
				invMap.put("stInvDate", param.get("stInvDate"));
				invMap.put("invStoreCd", map.get("STORE_CD"));
				invMap.put("tfPrdCd", map.get("TF_PRD_CD"));
				invMap.put("tfPrdNm", map.get("TF_PRD_NM"));
				invMap.put("btPrdBarcode", map.get("TF_PRD_BARCODE"));
				invMap.put("prdSize", map.get("PRD_SIZE"));
				invMap.put("deviceGub", param.get("deviceGub"));
				if(tagList.contains(map.get("TF_PRD_TAGID"))){
					stInvCnt += 1;
					invMap.put("invYn", "Y");
				}else{
					invMap.put("invYn", "N");
				}
				invMap.put("regId", param.get("regId"));
				tfInventoryMapper.inventorySave(invMap);
			}
		}else{	//재고실사 현황이 생성되어있을때 실사정보 업데이트
			stInvCnt = vo.getStInvCnt();

			for(String tagId : tagList){
				Map invMap = new HashMap();
				invMap.put("stInvSeq", vo.getStInvSeq());
				invMap.put("tfPrdTagid", tagId);
				invMap.put("modDate", new Date());
				invMap.put("modId", param.get("regId"));
				invMap.put("deviceGub", param.get("deviceGub"));
				if(tfInventoryMapper.invUpdate(invMap) == 1) {
					stInvCnt += 1;
				}
			}
			vo.setModDate(new Date());
			vo.setModId((String) param.get("regId"));
		}

		if(vo.getStTarCnt() == stInvCnt){	//완료시 작업(현재 실사한 정보로  group by 로 제품과 매장별로 카운트해서 재고현황 업데이트)
			vo.setStInvYn("Y");

			List<Map> cntList = tfInventoryMapper.findInventoryCnt(param);
			for(Map cntMap : cntList){
				cntMap.put("modId", param.get("regId"));
				cntMap.put("modDate", new Date());
				cntMap.put("storeCd", param.get("storeCd"));
				tfStockMapper.stockInvUpdate(cntMap);
			}
		}
		vo.setStInvCnt(stInvCnt);
		tfInvStatusMapper.updateInvStatus(vo);

		//현황이 생성되있을시 태그id와 seq를 가지고 업데이트
		return ResultUtil.setCommonResult("S","성공하였습니다");
	}

	@Override
	public Map<String, Object> findStock(Map param) throws Exception {
		String barcode = (String)param.get("barcode");
		List<Map> prdList = tfProductMapper.prdAndStkDetail(barcode.substring(0,10));

		for(Map prdMap : prdList){
			List tagList = tfAcStockMapper.findTagId((String)prdMap.get("barcode"));
			prdMap.put("stockCount", tagList.size());
			if(tagList.size()!= 0) {
				prdMap.put("tagList", tagList);
			}
		}
		List<Map> listMap = new ArrayList<Map>();
		return ResultUtil.setCommonResult("S","성공하였습니다", prdList);
	}

	@Override
	public Map<String, Object> commonCd(Map param) throws Exception {
		String cd = (String)param.get("commCd");

		List<TfCommCodeVO> voList = commService.commSList(cd.substring(0,4));
		List<Map> listMap = new ArrayList<Map>();
		for(TfCommCodeVO vo : voList){
			Map map = new HashMap();
			map.put("commCd", vo.getCommCdNm());
			map.put("commCdNm", vo.getCommCd());
			listMap.add(map);
		}

		return ResultUtil.setCommonResult("S","성공하였습니다", listMap);
	}


}
