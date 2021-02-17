package com.systemk.ams.mapper;

import com.systemk.ams.VO.AssetChangeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface AssetchangeMapper {

    List<AssetChangeVO> assetChangeList();
    
    List<AssetChangeVO> assetChangeList(String userKey);

    List<AssetChangeVO> assetChangeList(HashMap item);
    
}
