package com.systemk.thefactor2.Service.Impl;

import com.systemk.thefactor2.Service.CommService;
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

	@Override
	public List<TfCommCodeVO> commList() throws Exception {
		return tfCommCodeMapper.commList();
	}

	@Override
	public List<TfCommCodeVO> commBList(String code) throws Exception {
		return tfCommCodeMapper.commBList(code);
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
}
