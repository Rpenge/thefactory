package com.systemk.thefactor2.Service;


import com.systemk.thefactor2.VO.TfInoutTotalVO;

import java.util.List;
import java.util.Map;

public interface InoutTotService {

    public TfInoutTotalVO todayWork();

    public List<Map> monthWork();

}
