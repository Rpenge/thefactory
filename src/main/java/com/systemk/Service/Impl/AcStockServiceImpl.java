package com.systemk.Service.Impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.systemk.Mapper.PageMapper;
import com.systemk.Mapper.TfAcStockMapper;
import com.systemk.Mapper.TfProductMapper;
import com.systemk.Service.AcStockService;
import com.systemk.Service.BrandService;
import com.systemk.Util.MybatisUtil;
import com.systemk.VO.TfAcStockVO;


@Service
public class AcStockServiceImpl implements AcStockService {

    @Autowired
    private TfAcStockMapper tfAcStockMapper;

    @Autowired
    private TfProductMapper tfProductMapper;

    @Autowired
    private BrandService brandService;

    @Autowired
    private PageMapper pageMapper;


    @Override
    public Map<String, Object> findStock(Map param) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        TfAcStockVO vo = tfAcStockMapper.findStockByTagId((String)param.get("tagId"));
        if(vo != null){
            map.put("barcode", vo.getTfPrdBarcode());
            Map mapData = tfProductMapper.prdAndStk(map);
            Map<String, String> brandInfo = brandService.detailSearch((String)mapData.get("BRAND_KIND_CD"));
            map.put("btPrdBarcode",mapData.get("TF_PRD_BARCODE"));
            map.put("tfPrdTagid", param.get("tagId"));
            map.put("outStoreCd", vo.getStoreCd());
            map.put("prdSize",mapData.get("PRD_SIZE"));
            map.put("ecPrdCd",mapData.get("EC_PRD_CD"));
            map.put("tfPrdCd",mapData.get("TF_PRD_CD"));
            map.putAll(brandInfo);
        }
        return map;
    }

    @Override
    public Map<String, Object> findAcStock(Map param) throws Exception {
        MybatisUtil mu = new MybatisUtil();
        mu.setTable("TF_AC_STOCK");

        for(Object key : param.keySet()) {    //분류 처리
            if(key.equals("startDate") || key.equals("endDate") || key.equals("sort") || key.equals("direct") || key.equals("size")|| key.equals("page")|| key.equals("STORE_CD")){
                continue;
            }
            if (key.equals("word")) {
                List<String> columnList = new ArrayList<>();
                columnList.add("TF_PRD_BARCODE");
                columnList.add("TF_PRD_TAGID");
                mu.addWord(columnList, (String)param.get(key));
                break;
            }
            if (key.equals("PRD_SIZE")) {
                mu.addEqual("AC_PRD_SIZE", (String)param.get(key));
            }else if(key.equals("BRAND_KIND_CD")){
                mu.addStartLike((String)key, (String)param.get(key));
            }else{
                mu.addEqual((String)key, (String)param.get(key));
            }
        }

        if(param.get("STORE_CD")!= null){
            mu.addEqual("STORE_CD",(String)param.get("STORE_CD"));
        }
        if(param.get("startDate")!= null && param.get("endDate")!= null){
            mu.addBetween("ST_IN_DATE",(String)param.get("startDate"), (String)param.get("endDate"));
        }

        mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch())); // 수량조회
        if(param.get("page")!=null)
            mu.setPage(Integer.parseInt((String)param.get("page")));
        if(param.get("size")!=null)
            mu.setSize(Integer.parseInt((String)param.get("size")));

        List<Map> acList =  tfAcStockMapper.acStockList(mu.getTableSearch());

        mu.setContent(acList);
        return mu.getList();
    }


}
