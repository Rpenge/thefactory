package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfBrandVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface TfBrandMapper {

    List<TfBrandVO> brandList();

    List<TfBrandVO> brandSubList(String item);

    List<TfBrandVO> genderList(String item);

    List<TfBrandVO> clsList(String item);

    TfBrandVO findBrand(String code);

    Map detailSearch(String item);

}
