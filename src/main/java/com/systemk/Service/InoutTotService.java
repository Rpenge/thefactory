package com.systemk.Service;


import java.util.List;
import java.util.Map;
import com.systemk.VO.TfInoutTotalVO;

public interface InoutTotService {

    public Map<String, Object> findList(Map param) throws Exception;

    public Map<String, Object> findInSubList(Map param) throws Exception;

    public Map<String, Object> findOutSubList(Map param) throws Exception;

    public TfInoutTotalVO todayWork(Map param);

    public Map todayWorkAll();

    public List<Map> monthWork();

}
