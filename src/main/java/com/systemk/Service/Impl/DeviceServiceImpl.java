package com.systemk.Service.Impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.systemk.Mapper.PageMapper;
import com.systemk.Mapper.TfDeviceMapper;
import com.systemk.Service.CommService;
import com.systemk.Service.DeviceService;
import com.systemk.Util.MybatisUtil;
import com.systemk.Util.StringUtil;
import com.systemk.VO.TfDeviceVO;


@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private TfDeviceMapper tfDeviceMapper;

    @Autowired
    private PageMapper pageMapper;

    @Autowired
    private CommService commService;
    // 디바이스 목록
    @Override
    public Map<String, Object> deviceList(Map param) throws Exception {

        MybatisUtil mu = new MybatisUtil();
        mu.setTable("TF_DEVICE");

        for(Object key : param.keySet()) {    //분류 처리
            if(key.equals("startDate") || key.equals("endDate") || key.equals("sort") || key.equals("direct") || key.equals("size")|| key.equals("page")){
                continue;
            }else {
                mu.addEqual(StringUtil.camelToSnake((String) key), (String) param.get(key));
            }
        }
//        if(param.get("sort")!= null) {
//            mu.setSort(StringUtil.camelToSnake((String)param.get("sort")), (String) param.get("direct"));
//        }

        mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch())); // 수량조회
        if(param.get("page")!=null)
            mu.setPage(Integer.parseInt((String)param.get("page")));
        if(param.get("size")!=null)
            mu.setSize(Integer.parseInt((String)param.get("size")));

        List<TfDeviceVO> voList =  tfDeviceMapper.deviceList(mu.getTableSearch());
        List<Map> listMap = new ArrayList<Map>();
        mu.setContent(tfDeviceMapper.deviceList(mu.getTableSearch())); //리스트 조회
        return mu.getList();
    }
    // 디바이스 추가
    @Override
    public Map<String, Object> deviceSave(Map param) throws Exception {
        Map map = new HashMap();
        String storeNm = commService.codeToNm((String) param.get("storeCd"));
        param.put("storeNm", storeNm);
        if (tfDeviceMapper.deviceSave(param) == 1) {
            map.put("resultCode", "S");
        } else {
            map.put("resultCode", "E");
        }
        return map;
    }
    // 디바이스 정보 수정
    @Override
    public Map<String, Object> deviceUpdate(Map param) throws Exception {
        Map map = new HashMap();
        String storeNm = commService.codeToNm((String) param.get("storeCd"));
        param.put("storeNm", storeNm);
        System.out.println(param);
        if (tfDeviceMapper.deviceUpdate(param) == 1) {
            map.put("resultCode", "S");
        } else {
            map.put("resultCode", "E");
        }
        return map;
    }
    // 디바이스 삭제
    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> deviceDelete(Map param) throws Exception {
        List<Integer> list = (List) param.get("list");
        Map map = new HashMap();
        for(int seq : list){
            if (tfDeviceMapper.deviceDelete(seq) == 1) {
                map.put("resultCode", "S");
            } else {
                map.put("resultCode", "E");
            }
        }
        return map;
    }
}
