package com.systemk.thefactor2.Service.Impl;

import com.systemk.thefactor2.Mapper.PageMapper;
import com.systemk.thefactor2.Service.CommService;
import com.systemk.thefactor2.Util.MybatisUtil;
import com.systemk.thefactor2.Util.StringUtil;
import com.systemk.thefactor2.VO.TfCommCodeVO;
import com.systemk.thefactor2.Mapper.TfCommCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommServiceImpl implements CommService {

	@Autowired
	private TfCommCodeMapper tfCommCodeMapper;

	@Autowired
	private PageMapper pageMapper;

	@Override
	public List<TfCommCodeVO> commList() throws Exception {
		return tfCommCodeMapper.commList();
	}

	@Override
	public Map<String, Object> findList(Map param) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("tf_comm_code");

		for (Object key : param.keySet()) {
			if (key.equals("word")) {
				mu.addLike("COMM_CD", (String) param.get(key));
				mu.addLike("COMM_CD_NM", (String) param.get(key));
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
		mu.setContent(tfCommCodeMapper.findList(mu.getTableSearch()));
		return mu.getList();
	}

	@Override
	public List<TfCommCodeVO> commBList( ) throws Exception {
		return tfCommCodeMapper.commBList();
	}

	@Override
	public List<TfCommCodeVO> commMList(String code) throws Exception {
		return tfCommCodeMapper.commMList(code);
	}

	@Override
	public List<TfCommCodeVO> commSList(String code) throws Exception {
		return tfCommCodeMapper.commSList(code);
	}

	@Override
	public List<TfCommCodeVO> commMSList(String code) throws Exception {
		return tfCommCodeMapper.commMSList(code);
	}

	@Override
	public Map<String, Object> commSave(Map param) throws Exception {
		Map map = new HashMap();
		if(tfCommCodeMapper.commSave(param) == 1) {
			map.put("resultCode", "S");
		}else{
			map.put("resultCode", "E");
		}
		return map;
	}

	@Override
	public Map<String, Object> commUpdate(Map param) throws Exception {
		Map map = new HashMap();
		if(tfCommCodeMapper.commUpdate(param) == 1){
			map.put("resultCode", "S");
		}else{
			map.put("resultCode", "E");
		}
		return map;
	}

	@Override
	public Map<String, Object> commDelete(Map param) throws Exception {
		Map map = new HashMap();
		if(tfCommCodeMapper.commDelete(param) == 1){
			map.put("resultCode", "S");
		}else{
			map.put("resultCode", "E");
		}
		return map;
	}

	@Override
	public String codeToNm(String code) {
		TfCommCodeVO vo = tfCommCodeMapper.findCode(code);
		return vo.getCommCdNm();
	}

	@Override
	public Map nmToCdKV() {
		Map map = new HashMap();
		List<TfCommCodeVO> list = tfCommCodeMapper.commList();
		for(TfCommCodeVO vo : list){
			map.put(vo.getCommCdNm() ,vo.getCommCd());
		}
		return map;
	}
}
