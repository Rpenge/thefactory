package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfInoutTotalVO;
import com.systemk.thefactor2.VO.TfUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface TfInoutTotalMapper {

    TfInoutTotalVO todayWork();

    Map monthWork(int item);

}