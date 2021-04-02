package com.systemk.thefactor2.Service;


import com.systemk.thefactor2.VO.TfBrandVO;

import java.util.List;
import java.util.Map;

public interface BrandService {

    public List<TfBrandVO> findBrand() throws Exception;

    public List<TfBrandVO> findBrandSub(String brandCd) throws Exception;

    public String codeToNm(String brandCd);

    public Map detailSearch(String brandCd);
    // 브랜드 목록
    public Map<String, Object> brandAllList(Map param) throws Exception;

    public Map<String, Object> brandSave(Map param) throws Exception;

    public Map<String, Object> brandUpdate(Map param) throws Exception;

    public Map<String, Object> brandDelete(Map param) throws Exception;
}
