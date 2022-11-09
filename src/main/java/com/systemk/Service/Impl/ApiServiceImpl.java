package com.systemk.Service.Impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.systemk.Config.ConstansConfig;
import com.systemk.Mapper.TempRfidTagMapper;
import com.systemk.Mapper.TfAcStockMapper;
import com.systemk.Mapper.TfApplicationMapper;
import com.systemk.Mapper.TfDeviceMapper;
import com.systemk.Mapper.TfInoutTotalMapper;
import com.systemk.Mapper.TfInputMapper;
import com.systemk.Mapper.TfInvStatusMapper;
import com.systemk.Mapper.TfInventoryMapper;
import com.systemk.Mapper.TfLogMapper;
import com.systemk.Mapper.TfOutputMapper;
import com.systemk.Mapper.TfProductMapper;
import com.systemk.Mapper.TfStockMapper;
import com.systemk.Mapper.TfTagPublishMapper;
import com.systemk.Mapper.TfUserMapper;
import com.systemk.Service.ApiService;
import com.systemk.Service.CommService;
import com.systemk.Service.UserService;
import com.systemk.Util.ResultUtil;
import com.systemk.VO.TfApplicationVO;
import com.systemk.VO.TfDeviceVO;
import com.systemk.VO.TfUserVO;

@Service
public class ApiServiceImpl implements ApiService {


  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private TfUserMapper tfUserMapper;

  @Autowired
  private TfDeviceMapper tfDeviceMapper;

  @Autowired
  private TfApplicationMapper tfApplicationMapper;

  @Autowired
  private TfInputMapper tfInputMapper;

  @Autowired
  private TfInoutTotalMapper tfInoutTotalMapper;

  @Autowired
  private TfTagPublishMapper tfTagPublishMapper;

  @Autowired
  private TfStockMapper tfStockMapper;

  @Autowired
  private TfProductMapper tfProductMapper;

  @Autowired
  private TfAcStockMapper tfAcStockMapper;

  @Autowired
  private TfOutputMapper tfOutputMapper;

  @Autowired
  private TfInventoryMapper tfInventoryMapper;

  @Autowired
  private TfInvStatusMapper tfInvStatusMapper;

  @Autowired
  private UserService userService;
  
//  @Autowired
//  private InoutTotService inoutTotService;

  @Autowired
  private CommService commService;

//  @Autowired
//  private BrandService brandService;
//
//  @Autowired
//  private OutputService outputService;

  @Autowired
  private TempRfidTagMapper tempRfidTagMapper;

  @Autowired
  private TfLogMapper tfLogMapper;
  
//  @Override
  public Map<String, Object> pdaLogin(Map param) throws Exception {
      HashMap item = new HashMap();
      item.put("userId", param.get("userId"));

      TfDeviceVO device = tfDeviceMapper.serialSearch(param);
      TfApplicationVO lastVer = tfApplicationMapper.appLastVs(param);

      if(device == null){                                                                             // 장비 등록 여부
          return ResultUtil.setCommonResult("E",ConstansConfig.NOT_FIND_DEVICE_MSG);
      }
      if(!lastVer.getVersion().equals(param.get("version"))){                                         //어플 버전 확인
          item = new HashMap();
          item.put("appDownUrl", "http://"+param.get("appDownUrl")+"/#/member/appDown");
          return ResultUtil.setCommonResult("U",ConstansConfig.VERSION_UPDATE_MSG, item);
      }

      TfUserVO user = tfUserMapper.login(item);
      try {
          item.put("userNm", user.getUserNm());
          item.put("storeCd", user.getStoreCd());
          item.put("gradeCd", user.getGrade());
          item.put("storeNm", commService.codeToNm(user.getStoreCd()));
          item.put("grade", commService.codeToNm(user.getGrade()));
      }catch(Exception e){
          return ResultUtil.setCommonResult("E",ConstansConfig.NOT_FIND_USER_MSG);
      }
      if(!passwordEncoder.matches((CharSequence) param.get("userPwd"), user.getUserPwd())){   // 비밀번호 검증 실패
          return ResultUtil.setCommonResult("E",ConstansConfig.NOT_VALID_PASSWORD_MSG);
      } else if(user.getUserStat().equals("N")){                                                      // 계정 사용가능 여부
          return ResultUtil.setCommonResult("E",ConstansConfig.NOT_USE_USER_MSG);
      } else if(user.getPdaUseYn().equals("N")){                                                      // PDA 사용가능 여부
          return ResultUtil.setCommonResult("E",ConstansConfig.NOT_CHECK_ADMIN_MSG);
      }
      return ResultUtil.setCommonResult("S",ConstansConfig.LOGIN_SUCCESS_MSG, item);
  }

}