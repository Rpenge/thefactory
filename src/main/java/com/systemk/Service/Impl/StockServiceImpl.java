package com.systemk.Service.Impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.systemk.Mapper.PageMapper;
import com.systemk.Mapper.TfStockMapper;
import com.systemk.Service.BrandService;
import com.systemk.Service.StockService;
import com.systemk.Util.MybatisUtil;


@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private BrandService brandService;

    @Autowired
    private TfStockMapper tfStockMapper;

    @Autowired
    private PageMapper pageMapper;

    //재고현황관리 - 리스트 조회
    @Override
    public Map<String, Object> findList(Map param) throws Exception {
        MybatisUtil mu = new MybatisUtil();
        mu.setTable("TF_STOCK");
        if (param.get("word")!=null) {
//          mu.addLike("TF_PRD_NM", (String)param.get("word"));
//          mu.addORLike("TF_PRD_CD", (String)param.get("word"));
//          mu.addORLike("TF_PRD_BARCODE", (String)param.get("word"));
            List<String> columnList = new ArrayList<>();
            columnList.add("TF_PRD_NM");
            columnList.add("TF_PRD_CD");
            columnList.add("TF_PRD_BARCODE");
            mu.addWord(columnList, (String)param.get("word"));
        }
        if(param.get("STORE_CD")!=null){
            mu.addEqual("STOCK_STORE_CD", (String)param.get("STORE_CD"));
        }
        if(param.get("PRD_SIZE")!=null){
            mu.addEqual("PRD_SIZE", (String)param.get("PRD_SIZE"));
        }


        mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch())); // 수량조회
        if(param.get("page")!=null)
            mu.setPage(Integer.parseInt((String)param.get("page")));
        if(param.get("size")!=null)
            mu.setSize(Integer.parseInt((String)param.get("size")));

        List<Map> ListMap =  tfStockMapper.stockList(mu.getTableSearch());
        List<Map> resultList = new ArrayList<Map>();
        for(Map map : ListMap){
            HashMap searchMap = new HashMap();
            searchMap.put("barcode", map.get("TF_PRD_BARCODE"));
            searchMap.put("storeCd", map.get("STOCK_STORE_CD"));
            map.putAll(tfStockMapper.workCnt(searchMap));
            if(map.get("BRAND_KIND_CD") != null) {
                Map brandInfo = brandService.detailSearch((String) map.get("BRAND_KIND_CD"));
                map.putAll(brandInfo);
            }
            resultList.add(map);
        }
        mu.setContent(resultList);
        return mu.getList();
    }

    //재고현황관리 - 재고차이 리스트 조회
    @Override
    public Map<String, Object> findExList(Map param) throws Exception {
        MybatisUtil mu = new MybatisUtil();
        mu.setTable("TF_STOCK");

        if (param.get("word")!=null) {
//          mu.addLike("TF_PRD_NM", (String)param.get("word"));
//          mu.addORLike("TF_PRD_CD", (String)param.get("word"));
//          mu.addORLike("TF_PRD_BARCODE", (String)param.get("word"));
            List<String> columnList = new ArrayList<>();
            columnList.add("ts.TF_PRD_NM");
            columnList.add("ts.TF_PRD_CD");
            columnList.add("ts.TF_PRD_BARCODE");
            mu.addWord(columnList, (String)param.get("word"));
        }

        if(param.get("BRAND_KIND_CD")!=null){
            mu.addStr("BRAND_KIND_CD", (String)param.get("BRAND_KIND_CD"));
        }

        if(param.get("STORE_CD")!=null){
            mu.addEqual("STOCK_STORE_CD", (String)param.get("STORE_CD"));
        }
        if(param.get("PRD_SIZE")!=null){
            mu.addEqual("PRD_SIZE", (String)param.get("PRD_SIZE"));
        }

        mu.setTotalElements(pageMapper.stkExPageRecord(mu.getTableSearch())); // 수량조회
        if(param.get("page")!=null)
            mu.setPage(Integer.parseInt((String)param.get("page")));
        if(param.get("size")!=null)
            mu.setSize(Integer.parseInt((String)param.get("size")));

        List<Map> ListMap =  tfStockMapper.stockExList(mu.getTableSearch());
        List<Map> resultList = new ArrayList<Map>();
        for(Map map : ListMap){
            HashMap searchMap = new HashMap();
            searchMap.put("barcode", map.get("TF_PRD_BARCODE"));
            searchMap.put("storeCd", map.get("STOCK_STORE_CD"));
            map.putAll(tfStockMapper.workCnt(searchMap));
            if(map.get("BRAND_KIND_CD") != null) {
                Map brandInfo = brandService.detailSearch((String) map.get("BRAND_KIND_CD"));
                map.putAll(brandInfo);
            }
            resultList.add(map);
        }
        mu.setContent(resultList);
        return mu.getList();
    }

    //재고현황관리 - 보유재고관리 리스트 조회
    @Override
    public Map<String, Object> findRfidList(Map param) throws Exception {
        MybatisUtil mu = new MybatisUtil();
        mu.setTable("TF_STOCK");

        if (param.get("word")!=null) {
//          mu.addLike("ts.TF_PRD_NM", (String)param.get("word"));
//          mu.addORLike("ts.TF_PRD_CD", (String)param.get("word"));
//          mu.addORLike("ts.TF_PRD_BARCODE", (String)param.get("word"));
            List<String> columnList = new ArrayList<>();
            columnList.add("ts.TF_PRD_NM");
            columnList.add("ts.TF_PRD_CD");
            columnList.add("ts.TF_PRD_BARCODE");
            mu.addWord(columnList, (String)param.get("word"));
        }

        if(param.get("BRAND_KIND_CD")!=null){
            mu.addStr("BRAND_KIND_CD", (String)param.get("BRAND_KIND_CD"));
        }
        if(param.get("STORE_CD")!=null){
            mu.addEqual("STOCK_STORE_CD", (String)param.get("STORE_CD"));
        }
        if(param.get("PRD_SIZE")!=null){
            mu.addEqual("PRD_SIZE", (String)param.get("PRD_SIZE"));
        }

        mu.setTotalElements(pageMapper.stkRfidPageRecord(mu.getTableSearch())); // 수량조회
        if(param.get("page")!=null)
            mu.setPage(Integer.parseInt((String)param.get("page")));
        if(param.get("size")!=null)
            mu.setSize(Integer.parseInt((String)param.get("size")));

        List<Map> ListMap =  tfStockMapper.stockRfidList(mu.getTableSearch());
        List<Map> resultList = new ArrayList<Map>();
        for(Map map : ListMap){
            HashMap searchMap = new HashMap();
            searchMap.put("barcode", map.get("TF_PRD_BARCODE"));
            searchMap.put("storeCd", map.get("STOCK_STORE_CD"));
            map.putAll(tfStockMapper.workCnt(searchMap));
            if(map.get("BRAND_KIND_CD") != null) {
                Map brandInfo = brandService.detailSearch((String) map.get("BRAND_KIND_CD"));
                map.putAll(brandInfo);
            }
            resultList.add(map);
        }
        mu.setContent(resultList);
        return mu.getList();
    }

    @Override
    public Map<String, Object> findListSearch(Map param) throws Exception {
        MybatisUtil mu = new MybatisUtil();
        mu.setTable("TF_STOCK");
        if(param.get("BRAND_KIND_CD")!=null){
            mu.addStr("BRAND_KIND_CD", (String)param.get("BRAND_KIND_CD"));
        }
        if(param.get("PRD_SIZE")!=null){
            mu.addStr("PRD_SIZE", (String)param.get("PRD_SIZE"));
        }
        if(param.get("STORE_CD")!=null){
            mu.addStr("STORE_CD", (String)param.get("STORE_CD"));
        }

        mu.setTotalElements(pageMapper.stkPageRecord(mu.getTableSearch())); // 수량조회
        if(param.get("page")!=null)
            mu.setPage(Integer.parseInt((String)param.get("page")));
        if(param.get("size")!=null)
            mu.setSize(Integer.parseInt((String)param.get("size")));

        List<Map> ListMap =  tfStockMapper.stockListSearch(mu.getTableSearch());
        List<Map> listMap = new ArrayList<Map>();
        for(Map map : ListMap){
            HashMap searchMap = new HashMap();
            searchMap.put("barcode", map.get("TF_PRD_BARCODE"));
            searchMap.put("storeCd", map.get("STOCK_STORE_CD"));
            map.putAll(tfStockMapper.workCnt(searchMap));

            Map brandInfo = brandService.detailSearch((String)map.get("BRAND_KIND_CD"));
            map.putAll(brandInfo);
            listMap.add(map);
        }
        mu.setContent(listMap);
        return mu.getList();
    }
}
