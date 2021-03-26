package com.systemk.thefactor2.Service;


import com.systemk.thefactor2.VO.TfProductVO;

import java.util.List;
import java.util.Map;

public interface ProductService {

    public List<TfProductVO> productList() throws Exception;

    public Map<String, Object> productSave(Map param) throws Exception;

    public Map<String, Object> productUpdate(Map param) throws Exception;

    public Map<String, Object> productDelete(Map param) throws Exception;
}
