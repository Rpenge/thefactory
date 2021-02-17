package com.systemk.ams.Service.Impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.systemk.ams.Util.MybatisUtil;
import com.systemk.ams.Util.StringUtil;
import com.systemk.ams.VO.AssetMgmtVO;
import com.systemk.ams.mapper.AssetMgmtMapper;
import com.systemk.ams.mapper.PageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.systemk.ams.DAO.AssetManagementDAO;
import com.systemk.ams.Entity.Main.AssetManagement;
import com.systemk.ams.Entity.Main.AssetMgJoinChange;
import com.systemk.ams.Entity.Main.AssetRepair;
import com.systemk.ams.Repository.Main.specification.assetManagementSpecification;
import com.systemk.ams.Repository.Main.AssetManagementRepository;
import com.systemk.ams.Repository.Main.AssetMgJoinChangeRepository;
import com.systemk.ams.Repository.Main.AssetRepairLogRepository;
import com.systemk.ams.Repository.Main.AssetRepairRepository;
import com.systemk.ams.Repository.Main.CompanyRepository;
import com.systemk.ams.Service.AssetManagementService;
import com.systemk.ams.Util.ObjectUtil;

@Service
public class AssetManagementServiceImpl implements AssetManagementService{

	@Autowired
	private AssetManagementDAO assetManagementDAO;

	@Autowired
	private AssetManagementRepository assetManagementRepository;

	@Autowired
	private AssetMgJoinChangeRepository assetMgJoinChangeRepository;


	//자산 조회 및 검색  JPA
//	@Override
//	public Page<AssetManagement> findList(Map<String, String> search, Pageable pageable) throws Exception {
//		if(search.isEmpty()) {
//			return assetManagementRepository.findAll(pageable);
//		}
//		Specifications<AssetManagement> specifications = Specifications.where(assetManagementSpecification.assetIsNotEmpty());
//		if(search.get("startDate") != null)
//			specifications = specifications.and(assetManagementSpecification.assetRegDateBetween(strToDate(search.get("startDate")), strToDate(search.get("endDate"))));
//		if(search.get("status")!=null)
//			specifications = specifications.and(assetManagementSpecification.assetStatusEqual(search.get("status")));
//		if(search.get("division")!=null)
//			specifications = specifications.and(assetManagementSpecification.assetDivisionEqual(search.get("division")));
//		if(search.get("location")!=null)
//			specifications = specifications.and(assetManagementSpecification.assetLocationEqual(search.get("location")));
//		if(search.get("dept") != null)
//			specifications = specifications.and(assetManagementSpecification.assetDeptEqual(search.get("dept")));
//		if(search.get("mappingYn") != null)
//			specifications = specifications.and(assetManagementSpecification.TagMappingYn(search.get("mappingYn")));
//		if(search.get("text") != null)
//			specifications = specifications.and(assetManagementSpecification.assetOptionContain(search.get("option"), search.get("text")));
//		return assetManagementRepository.findAll(specifications, pageable);
//	}

	@Autowired
	private PageMapper mapper;

	@Autowired
	private AssetMgmtMapper AMMapper;

	//자산 조회 및 검색
	@Override
	public Map<String, Object> findList(Map<String, String> search, Pageable pageable) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("asset_management");
		if(search.get("startDate")!=null && search.get("endDate")!=null){	//기간 처리
			mu.addBetween("chg_dt", search.get("startDate"), search.get("endDate"));
		}
		if(search.get("option")!=null && search.get("text") != ""){	//검색어 처리
			mu.addLike(StringUtil.camelToSnake(search.get("option")), search.get("text"));
		}
		for(String key : search.keySet()){	//분류 처리
			if(key.equals("size") || key.equals("page") || key.equals("text") || key.equals("startDate") || key.equals("endDate")
					|| key.equals("option") || key.equals("sort")) {
				continue;
			} else{
				mu.addEqual(StringUtil.camelToSnake(key), search.get(key));
			}
		}
		int count = mapper.pageRecord(mu.getTableSearch());
		mu.pager(search); // 수량, 페이지 설정
		mu.setTotalElements(count);
		mu.setSort(search.get("sort"));
		mu.setContent(AMMapper.assetMgmtList(mu.getTableSearch()));

		return mu.getList();
	}

	@Override
	public List<Map<String, String>> findList(Map<String, String> search) throws Exception {
		List<AssetManagement> list = null;
		if(search.isEmpty()) {
			list = assetManagementRepository.findAll();
		}
		Specifications<AssetManagement> specifications = Specifications.where(assetManagementSpecification.assetIsNotEmpty());
		if(search.get("startDate") != null)
			specifications = specifications.and(assetManagementSpecification.assetRegDateBetween(strToDate(search.get("startDate")), strToDate(search.get("endDate"))));
		if(search.get("status")!=null)
			specifications = specifications.and(assetManagementSpecification.assetStatusEqual(search.get("status")));
		if(search.get("division")!=null)
			specifications = specifications.and(assetManagementSpecification.assetDivisionEqual(search.get("division")));
		if(search.get("location")!=null)
			specifications = specifications.and(assetManagementSpecification.assetLocationEqual(search.get("location")));
		if(search.get("dept") != null)
			specifications = specifications.and(assetManagementSpecification.assetDeptEqual(search.get("dept")));
		if(search.get("mappingYn") != null)
			specifications = specifications.and(assetManagementSpecification.TagMappingYn(search.get("mappingYn")));
		if(search.get("text") != null)
			specifications = specifications.and(assetManagementSpecification.assetOptionContain(search.get("option"), search.get("text")));
		list =  assetManagementRepository.findAll(specifications);

		return ObjectUtil.ConvertListObjectToMap(list);
	}

	//자산등록
	@Override
	public AssetManagement assetReg(AssetManagement asset, String userId) throws Exception {
		String formDate = dateToStr(asset.getAssetRegDate());
		String controlCode = "SK"; //임시 회사 코드

		controlCode += asset.getAssetDivision().substring(0, 2);
		controlCode += formDate.substring(2,4) + formDate.substring(5,7);

		String preCtrlCd = assetManagementRepository.findctrlCd(controlCode+"%");
		if(preCtrlCd == null) {
			controlCode += "0001";
		}else {
			int seqNo = Integer.parseInt(preCtrlCd.substring(8)) + 1;
			controlCode += String.format("%04d", seqNo);
		}
		asset.setDateForm(formDate);
		asset.setAssetControlCode(controlCode);
		asset.setMappingYn("N");

		assetManagementDAO.insertAsset(asset);
		assetManagementDAO.assetChangeInsert(asset, userId, "WRG");

		return asset;
	}

	//excel자산 등록
	@Override
	public void assetReg(AssetManagement asset, String userId, int num) throws Exception {
		String formDate = dateToStr(asset.getAssetRegDate());
		String controlCode = "SK"; //임시 회사 코드

		controlCode += asset.getAssetDivision().substring(0, 2);
		controlCode += formDate.substring(2,4) + formDate.substring(5,7);

		String preCtrlCd = assetManagementRepository.findctrlCd(controlCode+"%");
		if(preCtrlCd == null && num == 1) {
			controlCode += "0001";
		}else if(preCtrlCd == null && num > 1){
			int seqNo = num;
			controlCode += String.format("%04d", seqNo);
		}else {
			int seqNo = Integer.parseInt(preCtrlCd.substring(8)) + num;
			controlCode += String.format("%04d", seqNo);
		}
		asset.setDateForm(formDate);
		asset.setAssetControlCode(controlCode);
		asset.setMappingYn("N");

		assetManagementDAO.insertAsset(asset);
		assetManagementDAO.assetChangeInsert(asset, userId, "WRG");
	}

	//자산 삭제
	@Override
	public void assetDelete(List<Integer> list, String userId) throws Exception {
		for(int i=0;i<list.size();i++){
			AssetManagement asset = assetManagementRepository.findByAssetManagementSeq((Integer)list.get(i));
			assetManagementDAO.deleteAsset(asset);
			assetManagementDAO.assetChangeInsert(asset, userId, "WDE");
		}
	}

	//자산 상세 조회
	@Override
	public AssetMgJoinChange findAssetDetail(String code) throws Exception {
		return assetMgJoinChangeRepository.findByAssetControlCode(code);
	}

	//자산 정보 수정
	@Override
	public void assetModi(AssetManagement asset, String userId) throws Exception {
			assetManagementRepository.save(asset);
			assetManagementDAO.assetChangeInsert(asset, userId, "WMO");
	}

	//자산 상태 수정
	@Transactional
	@Override
	public void statusUpdate(List list, String command, String userId) {
		for(int i=0;i<list.size();i++){
			AssetManagement asset = assetManagementRepository.findByAssetManagementSeq((Integer)list.get(i));
			asset.setAssetStatus(command);
			assetManagementRepository.save(asset);
			assetManagementDAO.assetChangeInsert(asset, userId, "WMO");
		}
	}

	//태그 상태 수정
	@Override
	public void assetTagUpdate(List list, String command, String userId) {
		for(int i=0;i<list.size();i++){
			AssetManagement asset = assetManagementRepository.findByAssetManagementSeq((Integer)list.get(i));
			if(command.equals("TagY")) {
				asset.setMappingYn("Y");
			}else if(command.equals("TagN")) {
				asset.setMappingYn("N");
			}
			assetManagementRepository.save(asset);
			assetManagementDAO.assetChangeInsert(asset, userId, "WTU");
		}
	}


	@Autowired
	private AssetRepairRepository assetRepairRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private AssetRepairLogRepository assetRepairLogRepository;


	@Override
	public List<AssetRepair> createAssetRepair(Map<String, Object> map) {
		List<AssetRepair> resultList = new ArrayList<AssetRepair>();
		List itemList = (List) map.get("item");

		for(int i=0;i < itemList.size();i++) {
			//수리등록
			AssetRepair ar = new AssetRepair();
			Map item = (Map) itemList.get(i);

			ar.setAstCtrlCd((String)item.get("assetControlCode"));
			ar.setRpCpn((String)map.get("rpCpn"));
			ar.setRpCtt((String)map.get("rpCtt"));
			ar.setRpQtt(Integer.parseInt((String)map.get("rpQtt")));
			ar.setRpEm(Integer.parseInt((String)map.get("rpEm")));
			ar.setRpDt((String)map.get("rpDt"));
			ar.setRpRegPrsn((String)map.get("rpRegPrsn"));
			ar.setRpSt((String)map.get("rpSt"));
			try {
				ar.setStDt(strToDate((String)map.get("stDt")));
				ar.setEdDt(strToDate((String)map.get("edDt")));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			//resultList.add(ar);
			assetRepairRepository.save(ar);

//			//수리 등록 로그 저장
//			//업체코드는 회사테이블 가서 정보 가져오고, 나머지는 코드변환
//			AssetRepairLog arl = new AssetRepairLog();
			AssetManagement am = assetManagementRepository.findByAssetControlCode(ar.getAstCtrlCd());
//			Company cpn = companyRepository.findByCpnCd((String)map.get("rpCpn")); // 수리업체 정보 조회
//
//			arl.setAstCtrlCd(ar.getAstCtrlCd());
//			arl.setAstDiv(am.getAssetDivision());
//			arl.setAstNm(am.getAssetName());
//			arl.setEdDt(ar.getEdDt());
//			arl.setRegDt(new Date());
//			arl.setRpCpn(cpn.getCpnNm());
//			arl.setRpCpnAdr(cpn.getCpnAdr());
//			arl.setrpCpnCnn(cpn.getcpnCnn());
//			arl.setRpCpnPic(cpn.getCpnPic());
//			arl.setRpCtt(ar.getRpCtt());
//			arl.setRpDt(ar.getRpDt());
//			arl.setRpEm(ar.getRpEm());
//			arl.setRpQtt(ar.getRpQtt());
//			arl.setRpRegPrsn(ar.getRpRegPrsn());
//			arl.setRpSt(ar.getRpSt());
//			arl.setStDt(ar.getStDt());
//			assetRepairLogRepository.save(arl);

			//자산 수리 상태변경
			am.setAssetStatus("REP");
			assetManagementRepository.save(am);
		}
		return resultList;
	}

	//public boolean repairLog()

	//util
	public Date strToDate(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		if(date.length() > 8) {
			format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		}
		return format.parse(date);
	}

	//util
	public String dateToStr(Date date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}




}
