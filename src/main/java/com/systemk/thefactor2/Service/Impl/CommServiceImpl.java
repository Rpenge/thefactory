package com.systemk.thefactor2.Service.Impl;




import com.systemk.thefactor2.Service.CommService;
import com.systemk.thefactor2.VO.TfCommCodeVO;
import com.systemk.thefactor2.Mapper.TfCommCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommServiceImpl implements CommService {

	@Autowired
	private TfCommCodeMapper tfCommCodeMapper;


	@Override
	public List<TfCommCodeVO> commList() throws Exception {
		return tfCommCodeMapper.commList();
	}

}
