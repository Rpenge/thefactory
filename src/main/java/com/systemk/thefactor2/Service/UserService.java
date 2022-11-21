package com.systemk.thefactor2.Service;




import java.util.List;
import java.util.Map;

public interface UserService {

    public Map<String, Object> findList(Map param) throws Exception;
    
    public Map<String, Object> userInfo(String param) throws Exception; // 211126 아이디로 계정 조회

    public Map<String, Object> userSave(Map param) throws Exception;

    public Map<String, Object> userUpdate(Map param) throws Exception;

    public Map<String, Object> userPwUpdate(Map param) throws Exception;

    public Map<String, Object> userWd(List param) throws Exception;

}
