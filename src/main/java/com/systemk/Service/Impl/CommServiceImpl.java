package com.systemk.Service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.systemk.Mapper.PageMapper;
import com.systemk.Mapper.TfCommCodeMapper;
import com.systemk.Service.CommService;
import com.systemk.Util.MybatisUtil;
import com.systemk.Util.StringUtil;
import com.systemk.VO.TfCommCodeVO;

@Service
public class CommServiceImpl implements CommService {

    @Autowired
    private TfCommCodeMapper tfCommCodeMapper;

    @Autowired
    private PageMapper pageMapper;

    public List<TfCommCodeVO> commList() throws Exception {
        return tfCommCodeMapper.commList();
    }

    public List<TfCommCodeVO> commTotList() throws Exception {
        return tfCommCodeMapper.commTotalList();
    }

    public Map<String, Object> findList(Map param) throws Exception {
        MybatisUtil mu = new MybatisUtil();
        mu.setTable("tf_comm_code");

        for (Object key : param.keySet()) {
            if (key.equals("word")) {
                mu.addLike("COMM_CD", (String) param.get(key));
                mu.addLike("COMM_CD_NM", (String) param.get(key));
            }
            if (key.equals("size") || key.equals("page") || key.equals("word")) {
                continue;
            } else {
                mu.addEqual(StringUtil.camelToSnake((String) key), (String) param.get(key));
            }
        }
        mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch()));
        if (param.get("page")!=null)
            mu.setPage(Integer.parseInt((String) param.get("page")));
        if (param.get("size")!=null)
            mu.setSize(Integer.parseInt((String) param.get("size")));
        mu.setContent(tfCommCodeMapper.findList(mu.getTableSearch()));
        return mu.getList();
    }

    public List<TfCommCodeVO> commBList( ) throws Exception {
        return tfCommCodeMapper.commBList();
    }

    public List<TfCommCodeVO> commMList(String commCd) throws Exception {
        return tfCommCodeMapper.commMList(commCd);
    }

    public List<TfCommCodeVO> commSList(String code) throws Exception {
        return tfCommCodeMapper.commSList(code);
    }

    public List<TfCommCodeVO> commMSList(String code) throws Exception {
        return tfCommCodeMapper.commMSList(code);
    }

    public Map<String, Object> commSave(Map param) throws Exception {
        Map map = new HashMap();
        if(tfCommCodeMapper.commSave(param) == 1) {
            map.put("resultCode", "S");
        }else{
            map.put("resultCode", "E");
        }
        return map;
    }

    public Map<String, Object> commUpdate(Map param) throws Exception {
        Map map = new HashMap();
        if(tfCommCodeMapper.commUpdate(param) == 1){
            map.put("resultCode", "S");
        }else{
            map.put("resultCode", "E");
        }
        return map;
    }

    public Map<String, Object> commDelete(Map param) throws Exception {
        Map map = new HashMap();
        if(tfCommCodeMapper.commDelete(param) == 1){
            map.put("resultCode", "S");
        }else{
            map.put("resultCode", "E");
        }
        return map;
    }

    public String codeToNm(String code) {
        TfCommCodeVO vo = tfCommCodeMapper.findCode(code);
        return vo.getCommCdNm();
    }

    public Map nmToCdKV() {
        Map map = new HashMap();
        List<TfCommCodeVO> list = tfCommCodeMapper.commList();
        for(TfCommCodeVO vo : list){
            map.put(vo.getCommCdNm() ,vo.getCommCd());
        }
        return map;
    }

    public Map codeToNmKV() {
        Map map = new HashMap();
        List<TfCommCodeVO> list = tfCommCodeMapper.commList();
        for(TfCommCodeVO vo : list){
            map.put(vo.getCommCd() ,vo.getCommCdNm());
        }
        return map;
    }
}
