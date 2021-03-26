package com.systemk.thefactor2.Service;


import com.systemk.thefactor2.VO.TfInoutTotalVO;

import java.util.List;
import java.util.Map;

public interface InoutTotService {

    public Map<String, Object> findList(Map param) throws Exception;

    public Map<String, Object> findInSubList(Map param) throws Exception;

    public Map<String, Object> findOutSubList(Map param) throws Exception;

    public TfInoutTotalVO todayWork(Map param);

    public Map todayWorkAll();

    public List<Map> monthWork();

}
