package com.systemk.thefactor2.Service.Impl;


import com.systemk.thefactor2.Mapper.PageMapper;
import com.systemk.thefactor2.Mapper.TfInputMapper;
import com.systemk.thefactor2.Service.InputService;
import com.systemk.thefactor2.Util.MybatisUtil;
import com.systemk.thefactor2.Util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class InputServiceImpl implements InputService {

	@Autowired
	private PageMapper pageMapper;

	@Autowired
	private TfInputMapper tfInputMapper;


	@Override
	public Map<String, Object> findList(Map param) throws Exception {
		MybatisUtil mu = new MybatisUtil();
		mu.setTable("tf_input");

		for(Object key : param.keySet()) {    //분류 처리
			if (key.equals("word")) {
				mu.addLike("USER_ID", (String)param.get(key));
				mu.addORLike("USER_NM", (String)param.get(key));
			}
			if (key.equals("size") || key.equals("page") || key.equals("word")) {
				continue;
			} else {
				mu.addEqual(StringUtil.camelToSnake((String)key), (String)param.get(key));
			}
		}
		mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch())); // 수량조회
		if(param.get("page")!=null)
			mu.setPage(Integer.parseInt((String)param.get("page")));
		if(param.get("size")!=null)
			mu.setSize(Integer.parseInt((String)param.get("size")));
		mu.setContent(tfInputMapper.inputList(mu.getTableSearch())); //리스트 조회

		return mu.getList();
	}


}
