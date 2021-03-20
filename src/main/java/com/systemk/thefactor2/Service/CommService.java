package com.systemk.thefactor2.Service;


import com.systemk.thefactor2.VO.TfCommCodeVO;

import java.util.List;
import java.util.Map;

public interface CommService {


    public List<TfCommCodeVO> commList() throws Exception;

    public String codeToNm(String code);

    public Map<String, Object> commSave(Map param) throws Exception;

    public Map<String, Object> commUpdate(Map param) throws Exception;

    public Map<String, Object> commDelete(Map param) throws Exception;

}
