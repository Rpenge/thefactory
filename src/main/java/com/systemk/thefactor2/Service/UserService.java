package com.systemk.thefactor2.Service;


import com.systemk.thefactor2.VO.TfUserVO;

import java.util.List;
import java.util.Map;

public interface UserService {

    public Map<String, Object> findList(Map param) throws Exception;

    public Map<String, Object> userSave(Map param) throws Exception;

    public Map<String, Object> userUpdate(Map param) throws Exception;

    public Map<String, Object> userPwUpdate(Map param) throws Exception;

    public Map<String, Object> userWd(List param) throws Exception;

    public Map<String, Object> PdaLogin(Map param) throws Exception;

}
