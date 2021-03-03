package com.systemk.thefactor2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@Autowired
	private Environment env;

	@RequestMapping("/")
    public String index(Model model) {
		model.addAttribute("version", env.getProperty("version"));
        return "index";
    }

	@RequestMapping("/main/home")
    public String home(Model model) {
        return "main/home";
    }

	@RequestMapping("/member/login")
    public String login(Model model) {
        return "member/login";
    }

	@RequestMapping("/assetManagement/modal/assetRegist")
    public String assetReg(Model model) {
        return "assetManagement/modal/assetRegist";
    }

    @RequestMapping("/sample/proReg")
    public String proReg(Model model) {
        return "sample/proReg";
    }

    @RequestMapping("/sample/codeList")
    public String codeList(Model model) {
        return "sample/codeList";
    }

	@RequestMapping("/assetManagement/assetList")
    public String assetMgList(Model model) {
        return "sample/assetList";
    }

}
