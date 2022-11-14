package com.systemk.Service;

import java.util.Map;
import com.systemk.VO.TfApplicationVO;

public interface FileService {

    public Map<String, Object> appSave(Map param) throws Exception;

    public TfApplicationVO appInfo(Map param) throws Exception;

}
