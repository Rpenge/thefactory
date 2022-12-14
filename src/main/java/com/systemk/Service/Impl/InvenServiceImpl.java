package com.systemk.Service.Impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.systemk.Mapper.PageMapper;
import com.systemk.Mapper.TfInvStatusMapper;
import com.systemk.Mapper.TfInventoryMapper;
import com.systemk.Mapper.TfStockMapper;
import com.systemk.Service.InvenService;
import com.systemk.Service.OutputService;
import com.systemk.Util.MybatisUtil;
import com.systemk.Util.ResultUtil;
import com.systemk.Util.StringUtil;
import com.systemk.VO.TfInvStatusVO;
import com.systemk.VO.TfInventoryVO;


@Service
public class InvenServiceImpl implements InvenService {

    @Autowired
    private PageMapper pageMapper;

    @Autowired
    private TfInvStatusMapper tfInvStatusMapper;

    @Autowired
    private TfInventoryMapper tfInventoryMapper;

    @Autowired
    private TfStockMapper tfStockMapper;

    @Autowired
    private OutputService outputService;


    @Override
    public Map<String, Object> findInvStatus(Map param) throws Exception {
        MybatisUtil mu = new MybatisUtil();
        mu.setTable("TF_INV_STATUS");

        for(Object key : param.keySet()) {
            if(key.equals("startDate") || key.equals("endDate")){
                continue;
            }else{
                mu.addEqual(StringUtil.camelToSnake((String)key), (String)param.get(key));
            }
        }

        if(param.get("startDate")!= null && param.get("endDate")!= null){
            mu.addBetween("ST_INV_DATE",(String)param.get("startDate"), (String)param.get("endDate"));
        }

        mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch())); // ์๋์กฐํ
        if(param.get("page")!=null)
            mu.setPage(Integer.parseInt((String)param.get("page")));
        if(param.get("size")!=null)
            mu.setSize(Integer.parseInt((String)param.get("size")));

        mu.setContent(tfInvStatusMapper.invStatusList(mu.getTableSearch())); //๋ฆฌ์คํธ ์กฐํ
        return mu.getList();
    }


    @Override
    public Map<String, Object> findInvList(Map param) throws Exception {
        MybatisUtil mu = new MybatisUtil();
        mu.setTable("TF_INVENTORY");

        if (param.get("word")!=null) {
            List<String> columnList = new ArrayList<>();
            columnList.add("ti.TF_PRD_NM");
            columnList.add("ti.TF_PRD_CD");
            columnList.add("ti.TF_PRD_TAGID");
            columnList.add("ti.BT_PRD_BARCODE");
            mu.addWord(columnList, (String)param.get("word"));
        }

        for(Object key : param.keySet()) {
            if(key.equals("startDate") || key.equals("endDate") || key.equals("size")|| key.equals("page") || key.equals("word")){
                continue;
            }else if(key.equals("brandKindCd")){
                mu.addStartLike(StringUtil.camelToSnake((String)key) ,(String)param.get(key));
                continue;
            }
            mu.addEqual(StringUtil.camelToSnake((String)key), (String)param.get(key));
        }

//      if (param.get("word") != null) {
//          mu.addLike("TF_PRD_NM", (String)param.get("word"));
//          mu.addORLike("TF_PRD_CD", (String)param.get("word"));
//          mu.addORLike("TF_PRD_TAGID", (String)param.get("word"));
//          mu.addORLike("BT_PRD_BARCODE", (String)param.get("word"));
//      }

//      if(param.get("startDate")!= null && param.get("endDate")!= null){
//          mu.addBetween("ST_INV_DATE",(String)param.get("startDate"), (String)param.get("endDate"));
//      }
        mu.setTotalElements(pageMapper.invPageRecord(mu.getTableSearch())); // ์๋์กฐํ
        if(param.get("page")!=null)
            mu.setPage(Integer.parseInt((String)param.get("page")));
        if(param.get("size")!=null)
            mu.setSize(Integer.parseInt((String)param.get("size")));

        mu.setContent(tfInventoryMapper.invenList(mu.getTableSearch())); //๋ฆฌ์คํธ ์กฐํ
        return mu.getList();
    }

    @Override
    public Map<String, Object> invenUpdate(Map param) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if(tfInventoryMapper.manualInvUpdate(param) == 1) {
            Map<String, Object> searchMap = new HashMap<String, Object>();
            searchMap.put("stInvDate", param.get("stInvDate"));
            searchMap.put("storeCd", param.get("invStoreCd"));
            TfInvStatusVO vo = tfInvStatusMapper.findInvStatus(searchMap);
            int StInvCnt = vo.getStInvCnt() + 1;
            if(param.get("misWork") != null) {
                Map<String, Object> outMap = new HashMap<String, Object>();
                outMap.put("userId", param.get("modId"));
                outMap.put("barcode", param.get("btPrdBarcode"));
                outMap.put("tfPrdTagid", param.get("tfPrdTagid"));
                outMap.put("outStoreCd", param.get("invStoreCd"));
                outMap.put("stOutType", param.get("misWork"));
                outMap.put("comment", param.get("invComment"));
                outMap.put("deviceGub", "020103");
                outputService.outputAdd(outMap);
            }
            if(vo.getStTarCnt() == StInvCnt){
                vo.setStInvYn("Y");
            }
            vo.setStInvCnt(StInvCnt);
            vo.setModId("modId");
            tfInvStatusMapper.updateInvStatus(vo);
            map.put("resultCode", "S");
        }else{
            map.put("resultCode", "E");
        }
        return map;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> invenUpdateList(Map param) throws Exception {
        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("stInvDate",  param.get("stInvDate"));
        searchMap.put("storeCd",    param.get("invStoreCd"));
        TfInvStatusVO vo = tfInvStatusMapper.findInvStatus(searchMap);

        List<Integer> list = (List) param.get("list");
        int addCnt = vo.getStInvCnt();
        for(int stInvenSeq : list){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("stInvenSeq", stInvenSeq);
            map.put("misWork", param.get("misWork"));
            map.put("modId", param.get("modId"));
            map.put("deviceGub", "020103");
            if(tfInventoryMapper.manualInvUpdate(map) == 1){
                if(param.get("misWork") != null) {
                    TfInventoryVO invenVO = tfInventoryMapper.findInventory(map);
                    Map<String, Object> outMap = new HashMap<String, Object>();
                    outMap.put("userId", param.get("modId"));
                    outMap.put("barcode", invenVO.getBtPrdBarcode());
                    outMap.put("tfPrdTagid", invenVO.getTfPrdTagid());
                    outMap.put("outStoreCd", invenVO.getInvStoreCd());
                    outMap.put("stOutType", param.get("misWork"));
                    outMap.put("deviceGub", "020103");
                    outputService.outputAdd(outMap);
                }
                addCnt+=1;
            }
        }
        if(vo.getStTarCnt() == addCnt){ //์๋ฃ์ ์์(ํ์ฌ ์ค์ฌํ ์?๋ณด๋ก  group by ๋ก ์?ํ๊ณผ ๋งค์ฅ๋ณ๋ก ์นด์ดํธํด์ ์ฌ๊ณ?ํํฉ ์๋ฐ์ดํธ)
            vo.setStInvYn("Y");
            List<Map> cntList = tfInventoryMapper.findInventoryCnt(searchMap);
            for(Map cntMap : cntList){
                cntMap.put("modId", param.get("modId"));
                cntMap.put("modDate", new Date());
                cntMap.put("storeCd", param.get("invStoreCd"));
                tfStockMapper.stockInvUpdate(cntMap);
            }
        }
        vo.setStInvCnt(addCnt);
        vo.setModId((String) param.get("modId"));
        vo.setModDate(new Date());
        tfInvStatusMapper.updateInvStatus(vo);
        return ResultUtil.setCommonResult("S","์ฑ๊ณตํ์์ต๋๋ค");
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> invenDelete(Map param) throws Exception {
        List<Integer> list = (List) param.get("list");
        Map<String, Object> map = new HashMap<String, Object>();
        for(int seq : list){
            if (tfInvStatusMapper.invenDelete(seq) == 1) {
                map.put("resultCode", "S");
            } else {
                map.put("resultCode", "E");
            }
        }
        return map;
    }

}
