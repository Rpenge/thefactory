package com.systemk.Service.Impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.systemk.Mapper.PageMapper;
import com.systemk.Mapper.TfOutputMapper;
import com.systemk.Mapper.TfProductMapper;
import com.systemk.Service.BrandService;
import com.systemk.Service.CommService;
import com.systemk.Service.OutputService;
import com.systemk.Util.MybatisUtil;
import com.systemk.Util.ResultUtil;
import com.systemk.Util.StringUtil;
import com.systemk.VO.TfOutputVO;


@Service
public class OutputServiceImpl implements OutputService {

    @Autowired
    private TfOutputMapper tfOutputMapper;

    @Autowired
    private PageMapper pageMapper;

    @Autowired
    private TfProductMapper tfProductMapper;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CommService commService;



    @Override
    public Map<String, Object> findList(Map param) throws Exception {
        MybatisUtil mu = new MybatisUtil();
        mu.setTable("TF_OUTPUT");
        for(Object key : param.keySet()) {
            if(key.equals("startDate") || key.equals("endDate") || key.equals("sort") || key.equals("direct")|| key.equals("size")|| key.equals("page")){
                continue;
            }
            if (key.equals("word")) {
                mu.addLike("TF_PRD_NM", (String)param.get(key));
                mu.addORLike("TF_PRD_CD", (String)param.get(key));
                mu.addORLike("BT_PRD_BARCODE", (String)param.get(key));
                mu.addORLike("TF_PRD_TAGID", (String)param.get(key));
                break;
            }
            if (key.equals("PRD_SIZE")) {
                mu.addLike((String)key, (String)param.get(key));
            }else if(key.equals("BRAND_KIND_CD")){
                mu.addStartLike((String)key, (String)param.get(key));
            }else{
                mu.addEqual((String)key, (String)param.get(key));
            }
        }
        if(param.get("sort")!= null) {
            mu.setSort(StringUtil.camelToSnake((String)param.get("sort")), (String) param.get("direct"));
        }

        if(param.get("startDate")!= null && param.get("endDate")!= null){
            mu.addBetween("ST_OUT_DATE",(String)param.get("startDate"), (String)param.get("endDate"));
        }
        mu.addEqual("substring(ST_OUT_TYPE,1,4)", "0602");
        mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch())); // ????????????
        if(param.get("page")!=null)
            mu.setPage(Integer.parseInt((String)param.get("page")));
        if(param.get("size")!=null)
            mu.setSize(Integer.parseInt((String)param.get("size")));

        List<TfOutputVO> voList =  tfOutputMapper.outList(mu.getTableSearch());
        List<Map> listMap = new ArrayList<Map>();
        for(TfOutputVO vo : voList){
            ObjectMapper objectMapper = new ObjectMapper();
            Map map = objectMapper.convertValue(vo, Map.class);
            Map brandInfo = brandService.detailSearch(vo.getBrandKindCd());
            map.putAll(brandInfo);
            listMap.add(map);
        }
        mu.setContent(listMap);
        return mu.getList();
    }

    @Override
    public Map<String, Object> findSalseList(Map param) throws Exception {
        MybatisUtil mu = new MybatisUtil();
        mu.setTable("TF_OUTPUT");
        for(Object key : param.keySet()) {
            if(key.equals("startDate") || key.equals("endDate") || key.equals("sort") || key.equals("direct")|| key.equals("size")|| key.equals("page")){
                continue;
            }
            if (key.equals("word")) {
                mu.addLike("TF_PRD_NM", (String)param.get(key));
                mu.addORLike("TF_PRD_CD", (String)param.get(key));
                mu.addORLike("BT_PRD_BARCODE", (String)param.get(key));
                mu.addORLike("TF_PRD_TAGID", (String)param.get(key));
                break;
            }
            if (key.equals("PRD_SIZE")) {
                mu.addLike((String)key, (String)param.get(key));
            }else if(key.equals("BRAND_KIND_CD")){
                mu.addStartLike((String)key, (String)param.get(key));
            }else{
                mu.addEqual((String)key, (String)param.get(key));
            }
        }
        if(param.get("sort")!= null) {
            mu.setSort(StringUtil.camelToSnake((String)param.get("sort")), (String) param.get("direct"));
        }

        if(param.get("startDate")!= null && param.get("endDate")!= null){
            mu.addBetween("ST_OUT_DATE",(String)param.get("startDate"), (String)param.get("endDate"));
        }
        mu.addEqual("substring(ST_OUT_TYPE,1,4)", "0603");
        mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch())); // ????????????
        if(param.get("page")!=null)
            mu.setPage(Integer.parseInt((String)param.get("page")));
        if(param.get("size")!=null)
            mu.setSize(Integer.parseInt((String)param.get("size")));

        List<TfOutputVO> voList =  tfOutputMapper.outList(mu.getTableSearch());
        List<Map> listMap = new ArrayList<Map>();
        for(TfOutputVO vo : voList){
            ObjectMapper objectMapper = new ObjectMapper();
            Map map = objectMapper.convertValue(vo, Map.class);
            Map brandInfo = brandService.detailSearch(vo.getBrandKindCd());
            map.putAll(brandInfo);
            listMap.add(map);
        }
        mu.setContent(listMap);
        return mu.getList();
    }

    @Override
    public Map<String, Object> outputSearch(String tagId) throws Exception {
        TfOutputVO vo = tfOutputMapper.outputSearch(tagId);
            ObjectMapper objectMapper = new ObjectMapper();
            Map map = objectMapper.convertValue(vo, Map.class);
        if(vo!=null) {
            map.putAll(brandService.detailSearch(vo.getBrandKindCd()));
        }
        return map;
    }
    
    // 211015 ??????
    @Override
    public Map<String, Object> outputMoveSearch(String tagId) throws Exception {
        TfOutputVO vo = tfOutputMapper.outputMoveSearch(tagId);
            ObjectMapper objectMapper = new ObjectMapper();
            Map map = objectMapper.convertValue(vo, Map.class);
        if(vo!=null) {
            map.putAll(brandService.detailSearch(vo.getBrandKindCd()));
        }
        return map;
    }

    @Override
    public Map<String, Object> outputAdd(Map param) throws Exception {

        //output????????? ??????, inouttotal?????? +1 ????????? -1, ??????????????? -1, ??????????????? ??????
        Map map = new HashMap();
        Map mapData = tfProductMapper.prdAndStk(param);

        map.put("ymd", StringUtil.dateFormatYMD(new Date()));       //????????????
        map.put("userId",   param.get("userId"));       //????????????
        map.put("brandCd",  mapData.get("BRAND_KIND_CD"));  //??????????????????
        map.put("prdNm",    mapData.get("TF_PRD_NM"));      //??????????????????
        map.put("ecPrdCd",  mapData.get("EC_PRD_CD"));  //??????????????????
        map.put("prdCd",    mapData.get("TF_PRD_CD"));      //??????????????????
        map.put("barcode",  param.get("barcode"));  //  ????????? ?????????
        map.put("size",     mapData.get("PRD_SIZE"));       //??????????????????
        map.put("tagId",    param.get("tfPrdTagid"));       //  ????????? ??????id
        if(param.get("stOutType").equals("060202") && param.get("inStoreCd") != null) {
            map.put("inStoreCd", param.get("inStoreCd"));    //  ??????????????????
            map.put("inStoreNm", commService.codeToNm((String) param.get("inStoreCd")));    //  ?????????????????????
        }
        map.put("outStoreCd", param.get("outStoreCd")); //  ????????????
        map.put("outStoreNm", commService.codeToNm((String)param.get("outStoreCd")));   //  ???????????????
        map.put("deviceGub",param.get("deviceGub"));    //????????? : PC
        map.put("outType",  param.get("stOutType"));    //?????? ??????
        map.put("outComment", param.get("comment"));

        tfOutputMapper.outputAdd((HashMap) map);
        return ResultUtil.setCommonResult("S","?????????????????????");
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> outputDelete(Map param) throws Exception {
        List<Integer> dList = (List)param.get("list");
        List<TfOutputVO> voList = tfOutputMapper.outDeleteList(dList);

        for(Integer seq : dList){
            if(!tfOutputMapper.outDeleteCheck(seq)){
                return ResultUtil.setCommonResult("E",seq + "?????? ????????? ???????????? ?????? ???????????? ????????????");
            }
        }
        for(TfOutputVO vo : voList){
            Map map = new HashMap();
            map.put("stOutSeq",vo.getStOutSeq());
            map.put("stOutDate",vo.getStOutDate());
            map.put("userId", param.get("userId"));
            map.put("barcode", vo.getBtPrdBarcode());
            map.put("tagId", vo.getTfPrdTagid());
            map.put("storeCd", vo.getOutStoreCd());
            map.put("stOutType", vo.getStOutType());
            map.put("brandCd", vo.getBrandKindCd());
            tfOutputMapper.outDelete((HashMap) map);
        }
        return ResultUtil.setCommonResult("S","?????????????????????");
    }


}
