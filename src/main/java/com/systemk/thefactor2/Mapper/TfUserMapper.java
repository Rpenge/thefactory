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

    void userSave(Map item);

}
