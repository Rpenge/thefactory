package com.systemk.ams.Service;

import java.util.List;
import java.util.Map;

import com.systemk.ams.VO.AssetChangeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.systemk.ams.Entity.Main.AssetChange;

 

public interface AssetChangeService {
	
	public Page<AssetChange> findRfidUse(Map<String, String> search, Pageable pageable) throws Exception;

	Map<String, Object> findRfidUse(Map<String, String> search) throws Exception;
	
}
 