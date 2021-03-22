package com.systemk.thefactor2.Controller;

import com.systemk.thefactor2.Service.BrandService;
import com.systemk.thefactor2.Service.CommService;
import com.systemk.thefactor2.Service.ProductService;
import com.systemk.thefactor2.VO.TfBrandVO;
import com.systemk.thefactor2.VO.TfCommCodeVO;
import com.systemk.thefactor2.VO.TfProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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

	//기초정보관리 - 코드관리 - 코드 리스트 조회(페이지 처리)
	@RequestMapping(value="/commList", method = RequestMethod.GET)
	public List<TfCommCodeVO> commList(HttpServletRequest request) throws Exception{
		return commService.commList();
	}
	@RequestMapping(value = "/findComm", method = RequestMethod.GET)
	public String findComm(@RequestBody(required = false) @RequestParam("code") String code) throws Exception {
		return commService.codeToNm(code);
	}
	//기초정보관리 - 코드관리 - 코드 추가
	@RequestMapping(value="/commSave", method = RequestMethod.POST)
	public Map commSave(@RequestBody(required = false) Map<String, String> map) throws Exception{
		return commService.commSave(map);
	}
	//기초정보관리 - 코드관리 - 코드 수정
	@RequestMapping(value="/commUpdate", method = RequestMethod.POST)
	public Map commUpdate(@RequestBody(required = false) Map<String, String> map) throws Exception{
		return commService.commUpdate(map);
	}
	//기초정보관리 - 코드관리 - 코드 삭제(update처리)
	@RequestMapping(value="/commDelete", method = RequestMethod.POST)
	public Map commDelete(@RequestBody(required = false) Map<String, Object> map) throws Exception{
		List<Integer> list = (List)map.get("prList");
		return commService.commDelete(map);
	}

	//기초정보관리 - 브랜드관리 - 브랜드 조회
	@RequestMapping(value="/brandList", method = RequestMethod.GET)
	public List<TfBrandVO> brandAllList(HttpServletRequest request) throws Exception{
		return brandService.brandAllList();
	}
	//기초정보관리 - 브랜드관리 - 브랜드 추가
	@RequestMapping(value="/brandSave", method = RequestMethod.POST)
	public Map brandSave(@RequestBody(required = false) Map<String, String> map) throws Exception{
		return brandService.brandSave(map);
	}
	//기초정보관리 - 브랜드관리 - 브랜드 수정
	@RequestMapping(value="/brandUpdate", method = RequestMethod.POST)
	public Map brandUpdate(@RequestBody(required = false) Map<String, String> map) throws Exception{
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
	public List<TfProductVO> productList(HttpServletRequest request) throws Exception{
		return productService.productList();
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
		List<Integer> list = (List)map.get("prList");
		return productService.productDelete(map);
	}
}
