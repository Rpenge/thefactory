package com.systemk.ams.mapper;

import com.systemk.ams.VO.AssetMgmtVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface AssetMgmtMapper {
    List<AssetMgmtVO> assetMgmtList(HashMap item);
}
