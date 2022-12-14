package com.systemk.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.systemk.VO.TfBrandVO;


@Mapper
public interface TfBrandMapper {

    // 브랜드 전체목록
    List<TfBrandVO> brandAllList(HashMap item);

    // 브랜드 추가
    int brandSave(Map item);
    // 브랜드 수정
    int brandUpdate(Map item);
    // 브랜드 삭제
    int brandDelete(Map item);

    List<TfBrandVO> brandList();

    List<TfBrandVO> brandTotalList();

    List<TfBrandVO> brandSubList(String item);

    List<TfBrandVO> genderList(String item);

    List<TfBrandVO> clsList(String item);

    TfBrandVO findBrand(String code);

    Map detailSearch(String item);

}
