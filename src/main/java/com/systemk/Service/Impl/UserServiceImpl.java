package com.systemk.Service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.systemk.Mapper.PageMapper;
import com.systemk.Mapper.TfUserMapper;
import com.systemk.Service.UserService;
import com.systemk.Util.MybatisUtil;
import com.systemk.Util.StringUtil;

@Service
public class UserServiceImpl implements UserService {
  
  @Autowired
  private PasswordEncoder passwordEncoder;
  
  @Autowired
  private TfUserMapper tfUserMapper;

  @Autowired
  private PageMapper pageMapper;
  
//사용자 리스트 조회, 검색
  public Map<String, Object> findList(Map param) throws Exception {
      MybatisUtil mu = new MybatisUtil();
      mu.setTable("tf_user");

      for(Object key : param.keySet()) {    //분류 처리
          if(key.equals("role")){
              mu.notEqual("GRADE", (String)param.get(key));
              continue;
          }

          if (key.equals("word")) {
              mu.addLike("USER_ID", (String)param.get(key));
              mu.addORLike("USER_NM", (String)param.get(key));
          }
          if (key.equals("size") || key.equals("page") || key.equals("word")) {
              continue;
          } else {
              mu.addEqual(StringUtil.camelToSnake((String)key), (String)param.get(key));
          }
      }
      mu.setTotalElements(pageMapper.pageRecord(mu.getTableSearch())); // 수량조회
      if(param.get("page")!=null)
          mu.setPage(Integer.parseInt((String)param.get("page")));
      if(param.get("size")!=null)
          mu.setSize(Integer.parseInt((String)param.get("size")));
      mu.setContent(tfUserMapper.userList(mu.getTableSearch())); //리스트 조회
      return mu.getList();
  }
  
  // 아이디로 계정 조회
  public Map<String, Object> userInfo(String param) throws Exception {
      return tfUserMapper.userInfo(param);
  }

  // 사용자 등록
  public Map<String, Object> userSave(Map param) throws Exception {
      Map map = new HashMap();
      param.put("userPwd", passwordEncoder.encode((String) param.get("userPwd")));
      if(tfUserMapper.userSave(param) == 1){
          map.put("resultCode", "S");
      }else{
          map.put("resultCode", "E");
      }
      return map;
  }

  // 사용자 정보변경
  public Map<String, Object> userUpdate(Map param) throws Exception {
      Map map = new HashMap();
      if(tfUserMapper.userUpdate(param) == 1){
          map.put("resultCode", "S");
      }else{
          map.put("resultCode", "E");
      }
      return map;
  }

  // 비밀번호 변경
  public Map<String, Object> userPwUpdate(Map param) throws Exception {
      Map map = new HashMap();
      param.put("userPwd", passwordEncoder.encode((String) param.get("userPwd")));
      if(tfUserMapper.userPwUpdate(param) == 1){
          map.put("resultCode", "S");
      }else{
          map.put("resultCode", "E");
      }
      return map;
  }

  //사용자 탈퇴
  @Transactional(rollbackFor=Exception.class)
  public Map<String, Object> userWd(List list) throws Exception {
      Map map = new HashMap();
      for(int i=0;i<list.size(); i++){
          //tfUserMapper.userWd((int) list.get(i));
          if(tfUserMapper.userWd ( (Integer) list.get(i)) != 1){
              map.put("resultCode", "E");
              return map;
          }
      }
      map.put("resultCode", "S");
      return map;
  }
  
}



