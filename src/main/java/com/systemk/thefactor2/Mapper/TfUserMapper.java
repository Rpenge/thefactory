package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface TfUserMapper {

    TfUserVO login(HashMap item);

    List<TfUserVO> userList(HashMap item);
    
    Map<String, Object> userInfo(String item); // 211125 아이디로 계정정보 조회

    int userSave(Map item);

    int userUpdate(Map item);

    int userPwUpdate(Map item);

    int userWd(int item);

}
