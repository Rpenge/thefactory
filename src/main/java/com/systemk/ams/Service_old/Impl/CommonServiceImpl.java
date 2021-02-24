package com.systemk.ams.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemk.ams.DAO.CommonCodeDAO;
import com.systemk.ams.Entity.Main.CommonCode;
import com.systemk.ams.Repository.Main.CommonCodeRepository;
import com.systemk.ams.Service.CommonService;


@Service
public class CommonServiceImpl implements CommonService{

	static private List<CommonCode> codeList;
	
	@Autowired
	private CommonCodeRepository commonCodeRepository;
	
	@Autowired
	private CommonCodeDAO commonCodeDAO;
	
	//코드 전체 조회
	@Override
	public List<CommonCode> codeSearchAll() throws Exception {
		this.codeList = commonCodeRepository.findAll();
		return codeList;
	}

	//코드 수정, 추가
	@Override
	public void codeUpdate(CommonCode code) throws Exception{
		if(code.getCmmnSeq()!=null) {
			commonCodeRepository.save(code);
		}else {
			commonCodeDAO.insert(code);
		}
		
	}

	//코드 삭제
	@Override
	public void codeDelete(List<String> list) throws Exception {
		commonCodeDAO.delete(list);
	}
	
	@Override
	public String codeToName(String code) {
		for(int i=0;i<codeList.size();i++) {
			if(codeList.get(i).getCode().equals(code)) {
				return codeList.get(i).getCodeName();
			}
		}
		return null;
	}
	
	@Override
	public String nameToCode(String name) {
		for(int i=0;i<codeList.size();i++) {
			if(codeList.get(i).getCodeName().equals(name)) {
				return codeList.get(i).getCode();
			}
		}
		return null;
	}
}
