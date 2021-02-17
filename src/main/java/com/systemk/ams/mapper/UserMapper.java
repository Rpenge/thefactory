package com.systemk.ams.mapper;

import com.systemk.ams.VO.UserInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface UserMapper {

//    Integer pageRecord(HashMap item);

    UserInfoVO login(HashMap item);


}
