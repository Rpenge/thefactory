package com.systemk.thefactor2.Service.Impl;


import com.systemk.thefactor2.Mapper.TfApplicationMapper;
import com.systemk.thefactor2.Service.FileService;
import com.systemk.thefactor2.Util.ResultUtil;
import com.systemk.thefactor2.VO.TfApplicationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private TfApplicationMapper tfApplicationMapper;


    @Override
    public Map<String, Object> appSave(Map param) throws Exception {
        String path = (String)param.get("path");
        String file = (String)param.get("fileName");
        param.put("appGub",param.get("appGub"));
        param.put("appNm",file);
        param.put("version", param.get("version"));
        param.put("appDownUrl", path + file);

        tfApplicationMapper.appSave(param);
        return ResultUtil.setCommonResult("S","성공하였습니다");
    }

    @Override
    public TfApplicationVO appInfo(Map param) throws Exception {
        System.out.println(param);
        return tfApplicationMapper.appLastVs(param);
    }
}
