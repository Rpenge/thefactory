package com.systemk.thefactor2.Mapper;

import com.systemk.thefactor2.VO.TfInputVO;
import com.systemk.thefactor2.VO.TfProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Mapper
public interface TfProductMapper {
    // 상품 마스터 전체 목록
    List<TfProductVO> productList();
    // 상품 마스터 추가
    int productSave(Map item);
    // 상품 마스터 수정
    int productUpdate(Map item);
    // 상품 마스터 삭제
    int productDelete(Map item);

    Map prdAndStk(Map item);

    List<Map> prdAndStkDetail(String prdAndStkDetail);



}
