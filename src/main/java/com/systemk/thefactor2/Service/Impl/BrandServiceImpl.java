package com.systemk.thefactor2.Service.Impl;


import com.systemk.thefactor2.Mapper.PageMapper;
import com.systemk.thefactor2.Mapper.TfBrandMapper;
import com.systemk.thefactor2.Service.BrandService;
import com.systemk.thefactor2.Util.MybatisUtil;
import com.systemk.thefactor2.Util.StringUtil;
import com.systemk.thefactor2.VO.TfBrandVO;
import com.systemk.thefactor2.VO.TfCommCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private TfBrandMapper tfBrandMapper;

	@Autowired
	private PageMapper pageMapper;

	@Override
	public List<TfBrandVO> findBrand() throws Exception {
		return tfBrandMapper.brandList();
	}

	@Override
	public List<TfBrandVO> findTotalBrand() throws Exception {
		return tfBrandMapper.brandTotalList();
	}

	@Override
	public List<TfBrandVO> findBrandSub(String brandCd) throws Exception {
		return tfBrandMapper.brandSubList(brandCd);
	}

	@Override
	public String codeToNm(String brandCd) {
		TfBrandVO vo = tfBrandMapper.findBrand(brandCd);
		return vo.getBrandNm();
	}

	@Override
	public Map detailSearch(String brandCd){
		Map map = tfBrandMapper.detailSearch(brandCd);
		map.put("brand", brandCd.substring(0,2)+"0000");
		map.put("gender", brandCd.substring(0,4)+"00");
		map.put("cls",brandCd);
		return map;
	}

	// 브랜드 전체 목록
	@Override
	public Map<String, Object> brandAllList(Map param) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("tf_brand");

		for (Object key : param.keySet()) {
			if (key.equals("word")) {
				mu.addLike("brand_kind_cd", (String) param.get(key));
				mu.addLike("brand_nm", (String) param.get(key));
			}
			if (key.equals("size") || key.equals("page") || key.equals("word")) {
				continue;
			} else {
				mu.addEqual(StringUtil.camelToSnake((String) key), (String) param.get(key));
			}
		}
		mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch()));
		if (param.get("page")!=null)
			mu.setPage(Integer.parseInt((String) param.get("page")));
		if (param.get("size")!=null)
			mu.setSize(Integer.parseInt((String) param.get("size")));
		mu.setContent(tfBrandMapper.brandAllList(mu.getTableSearch()));
		return mu.getList();
	}
	// 브랜드 추가
	@Override
	public Map<String, Object> brandSave(Map param) throws Exception {
		Map map = new HashMap();
		if (tfBrandMapper.brandSave(param) == 1) {
			map.put("resultCode", "S");
		} else {
			map.put("resultCode", "E");
		}
		return map;
	}
	// 브랜드 수정
	@Override
	public Map<String, Object> brandUpdate(Map param) throws Exception {
		Map map = new HashMap();
		if (tfBrandMapper.brandUpdate(param) == 1) {
			map.put("resultCode", "S");
		} else {
			map.put("resultCode", "E");
		}
		return map;
	}
	// 브랜드 삭제
	@Override
	public Map<String, Object> brandDelete(Map param) throws Exception {
		Map map = new HashMap();
		if (tfBrandMapper.brandDelete(param) == 1) {
			map.put("resultCode", "S");
		} else {
			map.put("resultCode", "E");
		}
		return map;
	}

	@Override
	public Map brandMap() throws Exception {
		Map map = new HashMap();
		List<TfBrandVO> list = tfBrandMapper.brandList();
		for(TfBrandVO vo : list){
			map.put(vo.getBrandKindCd() ,vo.getBrandNm());
		}
		return map;
	}

}
