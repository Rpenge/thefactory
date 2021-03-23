package com.systemk.thefactor2.Service;


import com.systemk.thefactor2.VO.TfBrandVO;

import java.util.List;
import java.util.Map;

public interface BrandService {

    public List<TfBrandVO> findBrand() throws Exception;

    public List<TfBrandVO> findBrandSub(String brandCd) throws Exception;

    public String codeToNm(String brandCd);

    public Map detailSearch(String brandCd);

}
