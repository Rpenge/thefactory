package com.systemk.ams.Controller;

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

	@RequestMapping("/member/userList")
    public String userList(Model model) {
        return "sample/userList";
    }

	@RequestMapping("/assetManagement/modal/assetRegist")
    public String assetReg(Model model) {
        return "assetManagement/modal/assetRegist";
    }

	@RequestMapping("/assetManagement/assetRepair")
    public String assetRepair(Model model) {
        return "assetManagement/assetRepair";
    }

	@RequestMapping("/assetManagement/assetList")
    public String assetMgList(Model model) {
        return "sample/assetList";
    }

	@RequestMapping("/assetManagement/assetPrintList")
    public String assetPrintList(Model model) {
        return "assetManagement/assetPrintList";
    }

	@RequestMapping("/assetManagement/assetStatusChange")
    public String assetStatusChange(Model model) {
        return "assetManagement/assetStatusChange";
    }

	@RequestMapping("/assetManagement/assetDisList")
    public String assetDisList(Model model) {
        return "assetManagement/assetDisList";
    }

	@RequestMapping("/member/userReg")
    public String userReg(Model model) {
        return "member/userReg";
    }

	@RequestMapping("/member/userAuthMod")
    public String userAuthMod(Model model) {
        return "member/userAuthMod";
    }

	@RequestMapping("/member/userInfoMod")
    public String userInfoMod(Model model) {
        return "member/userInfoMod";
    }

	@RequestMapping("/RFID/rfidChange")
    public String rfidChange(Model model) {
        return "RFID/rfidChange";
    }

	@RequestMapping("/assetManagement/modal/assetDetail")
    public String assetDetail(Model model) {
        return "assetManagement/modal/assetDetail";
    }

	@RequestMapping("/assetManagement/modal/repairReg")
    public String repairReg(Model model) {
        return "assetManagement/modal/repairReg";
    }

	@RequestMapping("/assetManagement/modal/assetPrint")
    public String assetPrint(Model model) {
        return "assetManagement/modal/assetPrint";
    }

	@RequestMapping("/assetManagement/modal/qrPrint")
    public String qrPrint(Model model) {
        return "assetManagement/modal/qrPrint";
    }

	@RequestMapping("/RFID/rfidRegList")
    public String rfidRegList(Model model) {
        return "RFID/rfidRegList";
    }

	@RequestMapping("/RFID/rfidWiList")
    public String rfidWiList(Model model) {
        return "RFID/rfidWiList";
    }

    @RequestMapping("/RFID/rfidMoveList")
    public String rfidMoveList(Model model) {
        return "RFID/rfidMoveList";
    }

    @RequestMapping("/RFID/rfidDisList")
    public String rfidDisList(Model model) {
        return "RFID/rfidDisList";
    }

	@RequestMapping("/RFID/rfidFailList")
    public String rfidFailList(Model model) {
        return "RFID/rfidFailList";
    }

	@RequestMapping("/setting/commonCode")
    public String commonCode(Model model) {
        return "setting/commonCode";
    }

	@RequestMapping("/setting/addCode")
    public String addCode(Model model) {
        return "setting/addCode";
    }

	@RequestMapping("/modal/alert")
    public String modalAlert(Model model) {
        return "modal/alert";
    }

	@RequestMapping("/modal/check")
    public String modalCheck(Model model) {
        return "modal/check";
    }
}
