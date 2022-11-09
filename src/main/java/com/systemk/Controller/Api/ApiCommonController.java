package com.systemk.Controller.Api;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.systemk.Service.ApiService;

@RestController
@RequestMapping("/api")
public class ApiCommonController {
  @Autowired
  private ApiService apiService;
  
  
  // id, 비밀번호, 버전 확인
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public Map<String, Object> MemberLogin(@RequestHeader("type") String deviceGub,
                                             HttpServletRequest request,
                                             @RequestBody(required = false) Map<String, Object> data) throws Exception {
      data.put("deviceGub", deviceGub);
      data.put("appDownUrl", request.getServerName()+":"+request.getServerPort());
      Map resultMap = apiService.pdaLogin(data);
      if(resultMap.get("resultCode").equals("S")){
          HttpSession session = request.getSession();
          session.setMaxInactiveInterval(60*60);
          session.setAttribute("PdaUserId", data.get("userId")); //세션 ID 저장
      }
      return resultMap;
  }

}