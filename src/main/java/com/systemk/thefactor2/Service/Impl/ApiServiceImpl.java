package com.systemk.thefactor2.Service.Impl;


import com.systemk.thefactor2.Config.ConstansConfig;
import com.systemk.thefactor2.Mapper.*;
import com.systemk.thefactor2.Service.ApiService;
import com.systemk.thefactor2.Service.BrandService;
import com.systemk.thefactor2.Service.CommService;
import com.systemk.thefactor2.Service.InoutTotService;
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
	private InoutTotService inoutTotService;

	@Autowired
	private CommService commService;

	@Autowired
	private BrandService brandService;


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
		item.put("userNm", user.getUserNm());
		item.put("storeCd", user.getStoreCd());
		item.put("gradeCd", user.getGrade());
		item.put("storeNm", commService.codeToNm(user.getStoreCd()));
		item.put("grade", commService.codeToNm(user.getGrade()));
		if(user == null){																				// ID 등록 여부
			return ResultUtil.setCommonResult("E",ConstansConfig.NOT_FIND_USER_MSG);
		} else if(!passwordEncoder.matches((CharSequence) param.get("userPwd"), user.getUserPwd())){ 	// 비밀번호 검증 실패
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
	public Map<String, Object> searchPrd(Map param) throws Exception {
		Map map = new HashMap();

		String barcode = (String)param.get("barcode");
		String preTagId = "T" + barcode.substring(barcode.length()-11, barcode.length());
		String tagId ="";
		String lastNum = tfTagPublishMapper.selectLastNum(preTagId);
		if(lastNum == null){
			tagId = preTagId + "0001";
		}else{
			tagId = preTagId + String.format("%04d", Integer.parseInt(lastNum) + 1);
		}
		TfStockVO vo = tfStockMapper.findStockInfo(barcode);
		map.put("prdCd", vo.getTfPrdCd());
		map.put("prdName", vo.getTfPrdNm());
		map.put("prdSeason", vo.getTfPrdNm().split(" ")[0]);
		map.put("tagId", tagId);
		map.put("brand", brandService.codeToNm(vo.getBrandKindCd()));
		map.put("brandCd", vo.getBrandKindCd());
		map.put("prdSize", vo.getPrdSize());
		map.put("prdSizeCd", vo.getPrdSizeCd());
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
		map.put("size", 	mapData.get("PRD_SIZE_CD"));		//재고정보에서
		map.put("tagId", 	param.get("tagId"));		//  가져온 태그id
		map.put("storeCd", 	param.get("storeCd"));	//  입고매장
		map.put("storeNm", 	commService.codeToNm((String)param.get("storeCd")));	//  입고매장명
		map.put("tagStat", 	"040101");	//신규발행 코드 : 고정
		map.put("deviceGub",param.get("deviceGub"));	//장비값 : PDA코드 : 헤더값
		map.put("inType", 	"060101");	//신규입고 코드 : 고정

		System.out.println(map);

		tfInputMapper.inputNew((HashMap) map);
		return ResultUtil.setCommonResult("S","성공하였습니다");
	}


}
