package com.systemk.thefactor2.mapper;

import com.systemk.thefactor2.VO.AssetMgmtVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface AssetMgmtMapper {
    List<AssetMgmtVO> assetMgmtList(HashMap item);
}
