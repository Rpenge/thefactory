package com.systemk.thefactor2.Service;


import com.systemk.thefactor2.VO.TfApplicationVO;

import java.util.Map;

public interface FileService {

    public Map<String, Object> appSave(Map param) throws Exception;

    public TfApplicationVO appInfo() throws Exception;

}
