package com.systemk.thefactor2.Service;


import com.systemk.thefactor2.VO.TfCommCodeVO;

import java.util.List;
import java.util.Map;

public interface CommService {
    // 코드목록
    public List<TfCommCodeVO> commList() throws Exception;

    public Map<String, Object> findList(Map param) throws Exception;
    // 대분류
    public List<TfCommCodeVO> commBList() throws Exception;
    // 중분류
    public List<TfCommCodeVO> commMList(String commCd) throws Exception;
    // 소분류
    public List<TfCommCodeVO> commSList(String code) throws Exception;
    // 중소분류
    public List<TfCommCodeVO> commMSList(String code) throws Exception;
    // 코드 저장
    public Map<String, Object> commSave(Map param) throws Exception;
    // 코드 수정
    public Map<String, Object> commUpdate(Map param) throws Exception;
    // 코드 삭제
    public Map<String, Object> commDelete(Map param) throws Exception;
    // 코드 -> 코드명
    public String codeToNm(String code);
}
