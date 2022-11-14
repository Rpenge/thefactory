package com.systemk.Service.Impl;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.systemk.Mapper.TfApplicationMapper;
import com.systemk.Service.FileService;
import com.systemk.Util.ResultUtil;
import com.systemk.VO.TfApplicationVO;


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
        param.put("comment", param.get("comment"));
        param.put("appDownUrl", path + file);

        tfApplicationMapper.appSave(param);
        return ResultUtil.setCommonResult("S","성공하였습니다");
    }

    @Override
    public TfApplicationVO appInfo(Map param) throws Exception {
        return tfApplicationMapper.appLastVs(param);
    }
}
