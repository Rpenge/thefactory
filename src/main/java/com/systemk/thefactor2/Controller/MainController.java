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

    @RequestMapping("/modal/alert")
    public String modalAlert(Model model) {
        return "modal/alert";
    }

    @RequestMapping("/modal/check")
    public String modalCheck(Model model) {
        return "modal/check";
    }

    @RequestMapping("/modal/upload")
    public String modalUpload(Model model) {
        return "modal/upload";
    }

    @RequestMapping("/member/appDown")
    public String appDown(Model model) {
        return "member/appDown";
    }


    @RequestMapping("/base/comm")
    public String comm(Model model) {
        return "base/comm";
    }

    @RequestMapping("/base/brand")
    public String brand(Model model) {
        return "base/brand";
    }

    @RequestMapping("/base/product")
    public String product(Model model) {
        return "base/product";
    }

    @RequestMapping("/base/device")
    public String device(Model model) {
        return "base/device";
    }


    @RequestMapping("/inout/input")
    public String input(Model model) {
        return "inout/input";
    }

    @RequestMapping("/inout/output")
    public String output(Model model) {
        return "inout/output";
    }

    @RequestMapping("/inout/sales")
    public String sales(Model model) {
        return "inout/sales";
    }

    @RequestMapping("/inout/ioInfo")
    public String ioInfo(Model model) {
        return "inout/ioInfo";
    }


    @RequestMapping("/inven/inv")
    public String inven(Model model) {
        return "inven/inv";
    }

    @RequestMapping("/inven/invInfo")
    public String invInfo(Model model) {
        return "inven/invInfo";
    }


    @RequestMapping("/stock/stk")
    public String stock(Model model) {
        return "stock/stk";
    }

    @RequestMapping("/stock/stkTag")
    public String stkTag(Model model) {
        return "stock/stkTag";
    }


    @RequestMapping("/member/login")
    public String login(Model model) {
        return "member/login";
    }

    @RequestMapping("/system/user")
    public String user(Model model) {
        return "system/user";
    }


    @RequestMapping("/ex/stk")
    public String ex1(Model model) {
        return "ex/stk";
    }

//    /////
//
//    @RequestMapping("/sample/proReg")
//    public String proReg(Model model) {
//        return "sample/proReg";
//    }
//
//    @RequestMapping("/sample/userReg")
//    public String userReg(Model model) {
//        return "sample/userReg";
//    }
//

//
//    //////
//    @RequestMapping("/assetManagement/assetList")
//    public String assetMgList(Model model) {
//        return "sample/assetList";
//    }
//

//
//    @RequestMapping("/assetManagement/modal/assetRegist")
//    public String assetReg(Model model) {
//        return "assetManagement/modal/assetRegist";
//    }

}
