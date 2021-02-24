package com.systemk.ams.Service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.systemk.ams.VO.AssetChangeVO;
import com.systemk.ams.mapper.AssetchangeMapper;
import com.systemk.ams.mapper.PageMapper;
import com.systemk.ams.Util.BoardPager;
import com.systemk.ams.Util.MybatisUtil;
import com.systemk.ams.Util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.systemk.ams.Entity.Main.AssetChange;
import com.systemk.ams.Repository.Main.specification.assetChangeSpecification;
import com.systemk.ams.Repository.Main.AssetChangeRepository;
import com.systemk.ams.Service.AssetChangeService;

@Slf4j
@Service
public class AssetChangeServiceImpl implements AssetChangeService{

	@Autowired
	private AssetChangeRepository AssetChangeRepository;

	@Override
	public Page<AssetChange> findRfidUse(Map<String, String> search, Pageable pageable) throws Exception {

		if(search.isEmpty()) {
			return AssetChangeRepository.findByWkEnv("PDA", pageable);
		}
		Specifications<AssetChange> specifications = Specifications.where(assetChangeSpecification.isNotEmpty());
		if(search.get("startDate") != null)
			specifications = specifications.and(assetChangeSpecification.changeDateBetween(StrToDate(search.get("startDate")), StrToDate(search.get("endDate"))));
		if(search.get("status") != null)
			specifications = specifications.and(assetChangeSpecification.assetStatusEqual(search.get("status")));
		if(search.get("work") != null)
			specifications = specifications.and(assetChangeSpecification.assetWorkEqual(search.get("work")));
		if(search.get("location") != null)
			specifications = specifications.and(assetChangeSpecification.workLocationEqual(search.get("location")));
		if(search.get("div") != null)
			specifications = specifications.and(assetChangeSpecification.assetDiv(search.get("div")));
		if(search.get("dept") != null)
			specifications = specifications.and(assetChangeSpecification.assetDept(search.get("dept")));
		if(search.get("wiYn") != null)
			specifications = specifications.and(assetChangeSpecification.wealthYn(search.get("wiYn")));
		if(search.get("updateYn") != null)
			specifications = specifications.and(assetChangeSpecification.updateYn(search.get("updateYn")));
		if(search.get("text") != null)
			specifications = specifications.and(assetChangeSpecification.search(search.get("option"), search.get("text")));
		specifications = specifications.and(assetChangeSpecification.workEnv("PDA"));
		return AssetChangeRepository.findAll(specifications, pageable);
	}

	@Autowired
	private PageMapper mapper;

	@Autowired
	private AssetchangeMapper ACMapper;

	@Override
	public Map<String, Object> findRfidUse(Map<String, String> search) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("asset_change");
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
		mu.setContent(ACMapper.assetChangeList(mu.getTableSearch())); //조회

		return mu.getList();
	}



	//util
	public Date StrToDate(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.parse(date);
	}
}
