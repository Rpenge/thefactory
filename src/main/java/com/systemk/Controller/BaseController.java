package com.systemk.Controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.systemk.Service.BrandService;
import com.systemk.Service.CommService;
import com.systemk.Service.DeviceService;
import com.systemk.Service.ProductService;
import com.systemk.Util.RequestUtil;
import com.systemk.VO.TfCommCodeVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/base")
public class BaseController {

    @Autowired
    private CommService commService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductService productService;
    @Autowired
    private DeviceService deviceService;

    //기초정보관리 - 코드관리 - 코드 리스트 조회 메인에서도 사용
    @RequestMapping(value="/commList", method = RequestMethod.GET)
    public List<TfCommCodeVO> commList(HttpServletRequest request) throws Exception{
        return commService.commList();
    }
    //기초정보관리 - 코드관리 - 코드 리스트 조회 페이징 처리
    @RequestMapping(value = "findList", method = RequestMethod.GET)
    public Map<String, Object> findList(HttpServletRequest request) throws Exception {
        Map param = RequestUtil.reqParamToMap(request);
        return commService.findList(param);
    }
    //기초정보관리 - 코드관리 - 대분류 코드 조회
    @RequestMapping(value = "commBList", method = RequestMethod.GET)
    public List<TfCommCodeVO> commBList(HttpServletRequest request) throws Exception {
        return commService.commBList();
    }
    //기초정보관리 - 코드관리 - 중분류 코드 조회
    @RequestMapping(value = "/commMList", method = RequestMethod.GET)
    public List<TfCommCodeVO> commMList(@RequestParam("commCd") String commCd) throws Exception {
        return commService.commMList(commCd);
    }
    //기초정보관리 - 코드관리 - 소분류 코드 조회
    @RequestMapping(value = "/commSList", method = RequestMethod.GET)
    public List<TfCommCodeVO> commSList(@RequestParam("code") String code) throws Exception {
        return commService.commSList(code);
    }
    //기초정보관리 - 코드관리 - 중소분류 코드 조회
    @RequestMapping(value = "/commMSList", method = RequestMethod.GET)
    public List<TfCommCodeVO> commMSList(@RequestParam("code") String code) throws Exception {
        return commService.commMSList(code);
    }
    //기초정보관리 - 코드관리 - 특정코드 조회
    @RequestMapping(value = "/findComm", method = RequestMethod.GET)
    public String findComm(@RequestBody(required = false) @RequestParam("code") String code) throws Exception {
        return commService.codeToNm(code);
    }
    //기초정보관리 - 코드관리 - 코드 추가
    @RequestMapping(value="/commSave", method = RequestMethod.POST)
    public Map commSave(@RequestBody(required = false) Map<String, String> map, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        map.put("regId", (String)session.getAttribute("userId"));
        return commService.commSave(map);
    }
    //기초정보관리 - 코드관리 - 코드 수정
    @RequestMapping(value="/commUpdate", method = RequestMethod.POST)
    public Map commUpdate(@RequestBody(required = false) Map<String, String> map, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        map.put("modId", (String)session.getAttribute("userId"));
        return commService.commUpdate(map);
    }
    //기초정보관리 - 코드관리 - 코드 삭제(update처리)
    @RequestMapping(value="/commDelete", method = RequestMethod.POST)
    public Map commDelete(@RequestBody(required = false) Map<String, Object> map) throws Exception{
        List<Integer> list = (List)map.get("prList");
        return commService.commDelete(map);
    }


    //기초정보관리 - 브랜드관리 - 브랜드 조회 목록
    @RequestMapping(value="/brandList", method = RequestMethod.GET)
    public Map<String, Object> brandAllList(HttpServletRequest request) throws Exception{
        Map param = RequestUtil.reqParamToMap(request);
        return brandService.brandAllList(param);
    }
    //기초정보관리 - 브랜드관리 - 브랜드 추가
    @RequestMapping(value="/brandSave", method = RequestMethod.POST)
    public Map brandSave(@RequestBody(required = false) Map<String, String> map, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        map.put("regId", (String)session.getAttribute("userId"));
        return brandService.brandSave(map);
    }
    //기초정보관리 - 브랜드관리 - 브랜드 수정
    @RequestMapping(value="/brandUpdate", method = RequestMethod.POST)
    public Map brandUpdate(@RequestBody(required = false) Map<String, String> map, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        map.put("modId", (String)session.getAttribute("userId"));
        return brandService.brandUpdate(map);
    }
    //기초정보관리 - 브랜드관리 - 브랜드 삭제(update처리)
    @RequestMapping(value="/brandDelete", method = RequestMethod.POST)
    public Map brandDelete(@RequestBody(required = false) Map<String, Object> map) throws Exception{
        List<Integer> list = (List)map.get("prList");
        return brandService.brandDelete(map);
    }

    //기초정보관리 - 상품관리 - 상품 조회
    @RequestMapping(value="/productList", method = RequestMethod.GET)
    public Map<String, Object> productList(HttpServletRequest request) throws Exception{
        Map param = RequestUtil.reqParamToMap(request);
        return productService.productList(param);
    }
    //기초정보관리 - 상품관리 - 상품 추가
    @RequestMapping(value="/productSave", method = RequestMethod.POST)
    public Map productSave(@RequestBody(required = false) Map<String, String> map) throws Exception{
        return productService.productSave(map);
    }
    //기초정보관리 - 상품관리 - 상품 수정
    @RequestMapping(value="/productUpdate", method = RequestMethod.POST)
    public Map productUpdate(@RequestBody(required = false) Map<String, String> map) throws Exception{
        return productService.productUpdate(map);
    }
    //기초정보관리 - 상품관리 - 상품 삭제(update처리)
    @RequestMapping(value="/productDelete", method = RequestMethod.POST)
    public Map productDelete(@RequestBody(required = false) Map<String, Object> map) throws Exception{
        // return productService.productDelete(map);
        return productService.productDelYn(map);
    }

    //기초정보관리 - 디바이스관리 - 디바이스 조회
    @RequestMapping(value="/deviceList", method = RequestMethod.GET)
    public Map<String, Object> deviceList(HttpServletRequest request) throws Exception{
        Map param = RequestUtil.reqParamToMap(request);
        return deviceService.deviceList(param);
    }
    //기초정보관리 - 디바이스관리 - 디바이스 추가
    @RequestMapping(value="/deviceSave", method = RequestMethod.POST)
    public Map deviceSave(@RequestBody(required = false) Map<String, String> map, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        map.put("regId", (String)session.getAttribute("userId"));
        return deviceService.deviceSave(map);
    }
    //기초정보관리 - 디바이스관리 - 디바이스 수정
    @RequestMapping(value="/deviceUpdate", method = RequestMethod.POST)
    public Map deviceUpdate(@RequestBody(required = false) Map<String, String> map, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        map.put("modId", (String)session.getAttribute("userId"));
        return deviceService.deviceUpdate(map);
    }
    //기초정보관리 - 디바이스관리 - 디바이스 삭제(update처리)
    @RequestMapping(value="/deviceDelete", method = RequestMethod.POST)
    public Map deviceDelete(@RequestBody(required = false) Map<String, Object> map) throws Exception{
        return deviceService.deviceDelete(map);
    }
}
