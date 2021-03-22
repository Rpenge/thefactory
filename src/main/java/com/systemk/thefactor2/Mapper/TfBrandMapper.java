package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfBrandVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface TfBrandMapper {

    // 브랜드 전체목록
    List<TfBrandVO> brandAllList();
    // 브랜드 추가
    int brandSave(Map item);
    // 브랜드 수정
    int brandUpdate(Map item);
    // 브랜드 삭제
    int brandDelete(Map item);

    List<TfBrandVO> brandList();

    List<TfBrandVO> brandSubList(String item);

    List<TfBrandVO> genderList(String item);

    List<TfBrandVO> clsList(String item);

    TfBrandVO findBrand(String code);


}
