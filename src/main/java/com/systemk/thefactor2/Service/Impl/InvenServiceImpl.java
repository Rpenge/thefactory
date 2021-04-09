package com.systemk.thefactor2.Service.Impl;


import com.systemk.thefactor2.Mapper.PageMapper;
import com.systemk.thefactor2.Mapper.TfInvStatusMapper;
import com.systemk.thefactor2.Mapper.TfInventoryMapper;
import com.systemk.thefactor2.Service.*;
import com.systemk.thefactor2.Util.MybatisUtil;
import com.systemk.thefactor2.Util.ResultUtil;
import com.systemk.thefactor2.Util.StringUtil;
import com.systemk.thefactor2.VO.TfInvStatusVO;
import com.systemk.thefactor2.VO.TfInventoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class InvenServiceImpl implements InvenService {

	@Autowired
	private PageMapper pageMapper;

	@Autowired
	private TfInvStatusMapper tfInvStatusMapper;

	@Autowired
	private TfInventoryMapper tfInventoryMapper;

	@Autowired
	private OutputService outputService;


	@Override
	public Map<String, Object> findInvStatus(Map param) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("TF_INV_STATUS");

		for(Object key : param.keySet()) {
			if(key.equals("startDate") || key.equals("endDate")){
				continue;
			}else{
				mu.addEqual(StringUtil.camelToSnake((String)key), (String)param.get(key));
			}
		}

		if(param.get("startDate")!= null && param.get("endDate")!= null){
			mu.addBetween("ST_INV_DATE",(String)param.get("startDate"), (String)param.get("endDate"));
		}

		mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch())); // 수량조회
		if(param.get("page")!=null)
			mu.setPage(Integer.parseInt((String)param.get("page")));
		if(param.get("size")!=null)
			mu.setSize(Integer.parseInt((String)param.get("size")));

		List<Map> listMap = new ArrayList<Map>();
		mu.setContent(tfInvStatusMapper.invStatusList(mu.getTableSearch())); //리스트 조회
		return mu.getList();
	}


	@Override
	public Map<String, Object> findInvList(Map param) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("TF_INVENTORY");

		for(Object key : param.keySet()) {
			if(key.equals("startDate") || key.equals("endDate") || key.equals("sort") || key.equals("direct") || key.equals("size")|| key.equals("page")){
				continue;
			}else{
				mu.addEqual(StringUtil.camelToSnake((String)key), (String)param.get(key));
			}
		}
		mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch())); // 수량조회
		if(param.get("page")!=null)
			mu.setPage(Integer.parseInt((String)param.get("page")));
		if(param.get("size")!=null)
			mu.setSize(Integer.parseInt((String)param.get("size")));

		List<Map> listMap = new ArrayList<Map>();
		mu.setContent(tfInventoryMapper.invenList(mu.getTableSearch())); //리스트 조회

		return mu.getList();
	}

	@Override
	public Map<String, Object> invenUpdate(Map param) throws Exception {
		Map map = new HashMap();
		if(tfInventoryMapper.manualInvUpdate(param) == 1) {
			Map searchMap = new HashMap();
			searchMap.put("stInvDate", param.get("stInvDate"));
			searchMap.put("storeCd", param.get("invStoreCd"));
			TfInvStatusVO vo = tfInvStatusMapper.findInvStatus(searchMap);
			int StInvCnt = vo.getStInvCnt()+1;
			if(param.get("misWork") != null) {
				Map outMap = new HashMap();
				outMap.put("userId", param.get("modId"));
				outMap.put("barcode", param.get("btPrdBarcode"));
				outMap.put("tfPrdTagid", param.get("tfPrdTagid"));
				outMap.put("outStoreCd", param.get("invStoreCd"));
				outMap.put("stOutType", param.get("misWork"));
				outMap.put("deviceGub", "020103");
				outputService.outputAdd(outMap);
			}

			if(vo.getStTarCnt() == StInvCnt){
				vo.setStInvYn("Y");
			}
			vo.setStInvCnt(StInvCnt);
			vo.setModId("modId");
			tfInvStatusMapper.updateInvStatus(vo);
			map.put("resultCode", "S");
		}else{
			map.put("resultCode", "E");
		}
		return map;
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public Map<String, Object> invenUpdateList(Map param) throws Exception {
		Map searchMap = new HashMap();
		searchMap.put("stInvDate", param.get("stInvDate"));
		searchMap.put("storeCd", param.get("invStoreCd"));
		TfInvStatusVO vo = tfInvStatusMapper.findInvStatus(searchMap);

		List<Integer> list = (List) param.get("list");
		System.out.println(list);
		int addCnt = vo.getStInvCnt();
		for(int stInvenSeq : list){
			Map map = new HashMap();
			map.put("stInvenSeq", stInvenSeq);
			map.put("misWork", param.get("misWork"));
			map.put("modId", param.get("modId"));
			if(tfInventoryMapper.manualInvUpdate(map) == 1){
				if(param.get("misWork") != null) {
					TfInventoryVO invenVO = tfInventoryMapper.findInventory(map);
					Map outMap = new HashMap();
					outMap.put("userId", param.get("modId"));
					outMap.put("barcode", invenVO.getBtPrdBarcode());
					outMap.put("tfPrdTagid", invenVO.getTfPrdTagid());
					outMap.put("outStoreCd", invenVO.getInvStoreCd());
					outMap.put("stOutType", param.get("misWork"));
					outMap.put("deviceGub", "020103");
					outputService.outputAdd(outMap);
				}
				addCnt+=1;
			}
		}
		if(vo.getStTarCnt() == addCnt){
			vo.setStInvYn("Y");
		}
		vo.setStInvCnt(addCnt);
		vo.setModId((String) param.get("modId"));
		vo.setModDate(new Date());
		tfInvStatusMapper.updateInvStatus(vo);
		return ResultUtil.setCommonResult("S","성공하였습니다");
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public Map<String, Object> invenDelete(Map param) throws Exception {
		List<Integer> list = (List) param.get("list");
		Map map = new HashMap();
		for(int seq : list){
			if (tfInvStatusMapper.invenDelete(seq) == 1) {
				map.put("resultCode", "S");
			} else {
				map.put("resultCode", "E");
			}
		}
		return map;
	}


}
