package com.systemk.thefactor2.Service;


import com.systemk.thefactor2.VO.TfCommCodeVO;

import java.util.List;

public interface CommService {

    public List<TfCommCodeVO> findList() throws Exception;

}
