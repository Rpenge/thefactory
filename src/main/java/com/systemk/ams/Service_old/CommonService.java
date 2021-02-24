package com.systemk.ams.Service;

import java.util.List;

import com.systemk.ams.Entity.Main.CommonCode;


public interface CommonService {

	
	
//	public List<Object> codeSearch(String code) throws Exception;
//	
//	public Map<String, Object> codeSearches(List<String> code) throws Exception;

	public List<CommonCode> codeSearchAll() throws Exception;
	
	public void codeUpdate(CommonCode code) throws Exception;
	
	public void codeDelete(List<String> list) throws Exception;

	String codeToName(String code);

	String nameToCode(String name);
}
