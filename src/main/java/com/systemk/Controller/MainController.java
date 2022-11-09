package com.systemk.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
  
  @Autowired
  private Environment env;
  
  
  @RequestMapping("/member/login")
  public String login(Model model) {
      return "member/login";
  }
}
