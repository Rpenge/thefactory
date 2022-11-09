package com.systemk.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.systemk.VO.TfProductVO;


@Mapper
public interface TfProductMapper {
    // 상품 마스터 전체 목록
    List<TfProductVO> productList(HashMap item);
    // 상품 마스터 추가
    int productSave(Map item);
    // 상품 마스터 수정
    int productUpdate(Map item);
    // 210909 상품마스터 수정 시 재고상품정보 동시 수정
    int prdAndStkUpdate(Map item);
    // 210909 상품마스터 수정 시 실사내역정보 동시 수정
    int prdAndInvUpdate(Map item);
    // 211102 상품마스터 수정 시 입고내역정보 동시 수정
    int prdAndInpUpdate(Map item);
    // 211102 상품마스터 수정 시 출고내역정보 동시 수정
    int prdAndOutpUpdate(Map item);
    
    // 상품 마스터 삭제
    // int productDelete(int item);
    
    // 210902 상품 삭제 (update)
    int productDelYn1(int item);
    
    int productDelYn2(int item);
    
    int productDelYn3(int item);

    Map prdAndStk(Map item);

    List<Map> prdAndStkDetail(String prdAndStkDetail);

    int productCheck(Map item);
    // excel 상품 마스터 수정
    int prdExcelUpdate(Map item);

}
