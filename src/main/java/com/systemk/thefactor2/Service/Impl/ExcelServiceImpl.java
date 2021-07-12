package com.systemk.thefactor2.Service.Impl;


import com.systemk.thefactor2.Config.ConstansConfig;
import com.systemk.thefactor2.Mapper.*;
import com.systemk.thefactor2.Service.BrandService;
import com.systemk.thefactor2.Service.CommService;
import com.systemk.thefactor2.Service.ExcelService;
import com.systemk.thefactor2.Util.ResultUtil;
import com.systemk.thefactor2.Util.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@Service
public class ExcelServiceImpl implements ExcelService {


    @Autowired
    private CommService commService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private TfStockMapper tfStockMapper;

    @Autowired
    private TfProductMapper tfProductMapper;

    @Autowired
    private TfBrandMapper tfBrandMapper;

    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map stockExcelUpload(MultipartFile mf, String userId) throws Exception {

        OPCPackage opcPackage = OPCPackage.open(mf.getInputStream());
        XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Map cdMap = commService.nmToCdKV();
        for(int i=0; i<sheet.getLastRowNum() + 1; i++) {
            XSSFRow row = sheet.getRow(i);

            //상단명 맞지않을경우 return
            if(i==0) {
                if (!row.getCell(1).toString().equals("자체상품코드") ||
                        !row.getCell(2).toString().equals("상품명") ||
                        !row.getCell(3).toString().equals("단품") ||
                        !row.getCell(5).toString().equals("바코드") ||
                        !row.getCell(7).toString().equals("현재재고") ||
                        !row.getCell(8).toString().equals("실제재고") ||
                        !row.getCell(9).toString().equals("적정재고") ||
                        !row.getCell(13).toString().equals("배송처(창고)")
                ) {
                    return ResultUtil.setCommonResult("E","EXCEL 형식 불일치");
                }
                continue;
            }


            System.out.print(row.getCell(1).toString());  //자체상품코드
            System.out.print(row.getCell(2).toString());  //상품명
            System.out.print(row.getCell(3).toString());  //단품 : 사이즈
            System.out.print(row.getCell(5).toString()+" ");  //바코드
            System.out.print(row.getCell(7).toString()+" ");  //현재재고
            System.out.print(row.getCell(8).toString()+" ");  //실재재고
            System.out.print(row.getCell(9).toString()+" ");  //적정재고
            System.out.println(row.getCell(13).toString()+" ");  //지점

            Map map = new HashMap();

            map.put("TF_PRD_CD", row.getCell(1).toString());
            map.put("TF_PRD_NM", row.getCell(2).toString());
            map.put("PRD_SIZE", row.getCell(3).toString());
            map.put("TF_PRD_BARCODE", row.getCell(5).toString());
            map.put("CUR_STOCK_CNT", row.getCell(7).toString());
            map.put("REAL_STOCK_CNT", row.getCell(8).toString());
            map.put("STD_STOCK_CNT", row.getCell(9).toString());
            map.put("STOCK_STORE_NM", row.getCell(13).toString());
            map.put("STOCK_STORE_CD", cdMap.get(row.getCell(13).toString())); //코드 찾아서 입력
            map.put("userId", userId);

            if(tfStockMapper.stockCheck(map) > 0){
                tfStockMapper.stockUpdate(map);
            }else{
                tfStockMapper.stockSave(map);
            }

        }
        return ResultUtil.setCommonResult("S","성공하였습니다");
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map productExcelUpload(MultipartFile mf, String userId) throws Exception {
        OPCPackage opcPackage = OPCPackage.open(mf.getInputStream());
        XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Map cdMap = commService.nmToCdKV();
        for(int i=0; i<sheet.getLastRowNum() + 1; i++) {
            XSSFRow row = sheet.getRow(i);
            if(i==0) {
                if (
                    !row.getCell(0).toString().equals("상태") ||
                    !row.getCell(1).toString().equals("상품분류") ||
                    !row.getCell(2).toString().equals("상품코드") ||
                    !row.getCell(3).toString().equals("자체상품코드") ||
                    !row.getCell(5).toString().equals("상품명") ||
                    !row.getCell(6).toString().equals("자체상품명") ||
                    !row.getCell(7).toString().equals("모델명") ||
                    !row.getCell(8).toString().equals("제조사") ||
                    !row.getCell(9).toString().equals("원산지") ||
                    !row.getCell(10).toString().equals("브랜드") ||
                    !row.getCell(21).toString().equals("단품항목")
                ) {
                    return ResultUtil.setCommonResult("E","EXCEL 형식 불일치");
                }
                continue;
            }

            Map map = new HashMap();
            map.put("useYn", row.getCell(0).toString());
            map.put("brandKindCd", row.getCell(1).toString());
            map.put("ecPrdCd", row.getCell(2).toString());
            map.put("tfPrdCd", row.getCell(3).toString());
            map.put("ecPrdNm", row.getCell(5).toString());
            map.put("tfPrdNm", row.getCell(6).toString());
            map.put("prdModelNm", row.getCell(7).toString());
            map.put("brandMakeNm", row.getCell(8).toString());
            map.put("orgCountryNm", row.getCell(9).toString());
            map.put("brandNm", row.getCell(10).toString());
            map.put("ecSizeNm", row.getCell(21).toString());
            map.put("regId", userId);

            if(tfProductMapper.productCheck(map) > 0){
                tfProductMapper.prdExcelUpdate(map);
            }else{
                tfProductMapper.productSave(map);
            }
        }
        return ResultUtil.setCommonResult("S","성공하였습니다");
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map brandExcelUpload(MultipartFile mf, String userId) throws Exception {
        OPCPackage opcPackage = OPCPackage.open(mf.getInputStream());
        XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Map cdMap = commService.nmToCdKV();
        String brandCd = "";
        String genderCd = "";
        for(int i=0; i<sheet.getLastRowNum() + 1; i++) {
            XSSFRow row = sheet.getRow(i);
            if(i==0) {
                if (
                    !row.getCell(0).toString().equals("대분류") ||
                    !row.getCell(1).toString().equals("중분류") ||
                    !row.getCell(2).toString().equals("소분류") ||
                    !row.getCell(3).toString().equals("분류명")
                ) {
                    return ResultUtil.setCommonResult("E","EXCEL 형식 불일치");
                }
                continue;
            }

            Map map = new HashMap();

            if(row.getCell(0) != null && row.getCell(0).toString() != ""){
                map.put("brandKindCd", row.getCell(0).toString()+"0000");
                map.put("codeLevel", "B");
                brandCd = row.getCell(0).toString();
            }else if(row.getCell(1) != null && row.getCell(1).toString() != ""){
                map.put("brandKindCd", brandCd + row.getCell(1).toString()+"00");
                map.put("codeLevel", "M");
                genderCd = row.getCell(1).toString();
            }else if(row.getCell(2) != null && row.getCell(2).toString() != "") {
                map.put("brandKindCd", brandCd + genderCd + row.getCell(2).toString());
                map.put("codeLevel", "S");
            }
            map.put("brandNm", row.getCell(3).toString());
            map.put("useYn", "Y");
            map.put("regId", userId);
            map.put("modId", userId);

            if(tfBrandMapper.findBrand((String) map.get("brandKindCd")) != null){
                tfBrandMapper.brandUpdate(map);
            }else{
                tfBrandMapper.brandSave(map);
            }
        }
        return ResultUtil.setCommonResult("S","성공하였습니다");
    }


    @Autowired
    private TfInoutTotalMapper tfInoutTotalMapper;

    @Transactional(rollbackFor=Exception.class)
    @Override
    public void inoutExcelDown(Map map, HttpServletResponse response) throws Exception{
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();

        style.setAlignment(HorizontalAlignment.CENTER);
        font.setBold(true);
        style.setFont(font);

        List<LinkedHashMap<String, Object>> list = new ArrayList<>();
        XSSFRow row = null;
        XSSFCell cell = null;

        if((map.get("stType")).equals("0601")) {
            list = tfInoutTotalMapper.inExcel(map);
        }else if(map.get("stType").equals("0602") || map.get("stType").equals("0603")){
            list = tfInoutTotalMapper.outExcel(map);
        }else{
            list = tfInoutTotalMapper.totExcel(map);
        }
        Map commMap = commService.codeToNmKV();
        Map brandMap = brandService.brandMap();

        Map nameMap = ConstansConfig.getNameMap();
        sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, 10));

        XSSFRow topRow = sheet.createRow(0);
        for(int i=0; i< list.size();i++) {

            row = sheet.createRow(i + 1);

            int j = 0;
            for(String key : list.get(i).keySet()) {
                if(i == 0) {
                    cell = topRow.createCell(j);
                    cell.setCellStyle(style);
                    cell.setCellValue((String) nameMap.get(key));
                }else if(i==1){
                    sheet.autoSizeColumn(j);
                    sheet.setColumnWidth(j, (sheet.getColumnWidth(j)) + 700 );
                    if(sheet.getColumnWidth(j) < 2000){
                        sheet.setColumnWidth(j, 2000 );
                    }
                }

                cell = row.createCell(j);

                if(j==2) {
                    cell.setCellValue((String) commMap.get(list.get(i).get(key)));
                }else if(j==4) {
                    cell.setCellValue((String) brandMap.get(list.get(i).get(key).toString().substring(0,2)+"0000"));
                }else if(list.get(i).get(key) instanceof String) {
                    cell.setCellValue((String) list.get(i).get(key));
                }else if(list.get(i).get(key) instanceof Date){
                    cell.setCellValue(StringUtil.dateFormat((Date) list.get(i).get(key)));
                }
                j++;
            }
        }

        //파일 전송
        try {
            response.reset();
            response.setContentType( "application/vnd.ms-excel" );
            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.getOutputStream().close();
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Autowired
    private TfAcStockMapper tfAcStockMapper;

    @Transactional(rollbackFor=Exception.class)
    @Override
    public void stockExcelDown(Map map, HttpServletResponse response) throws Exception{
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();

        style.setAlignment(HorizontalAlignment.CENTER);
        font.setBold(true);
        style.setFont(font);

        List<LinkedHashMap<String, Object>> list = new ArrayList<>();
        XSSFRow row = null;
        XSSFCell cell = null;

        list = tfAcStockMapper.acStockExcel(map);
        Map commMap = commService.codeToNmKV();
        Map nameMap = ConstansConfig.getNameMap();
        sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, 10));

        XSSFRow topRow = sheet.createRow(0);
        for(int i=0; i< list.size();i++) {

            row = sheet.createRow(i + 1);

            int j = 0;
            for(String key : list.get(i).keySet()) {
                if(i == 0) {
                    cell = topRow.createCell(j);
                    cell.setCellStyle(style);
                    cell.setCellValue((String) nameMap.get(key));
                }else if(i==1){
                    sheet.autoSizeColumn(j);
                    sheet.setColumnWidth(j, (sheet.getColumnWidth(j)) + 700 );
                    if(sheet.getColumnWidth(j) < 2000){
                        sheet.setColumnWidth(j, 2000 );
                    }
                }

                cell = row.createCell(j);

                if(j==2) {
                    cell.setCellValue((String) commMap.get(list.get(i).get(key)));
                }else if(list.get(i).get(key) instanceof String) {
                    cell.setCellValue((String) list.get(i).get(key));
                }else if(list.get(i).get(key) instanceof Date){
                    cell.setCellValue(StringUtil.dateFormat((Date) list.get(i).get(key)));
                }
                j++;
            }
        }
        //파일 전송
        try {
            response.reset();
            response.setContentType( "application/vnd.ms-excel" );
            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.getOutputStream().close();
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Transactional(rollbackFor=Exception.class)
    @Override
    public void stkBaseExcelDown(Map map, HttpServletResponse response) throws Exception{
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();

        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        List<LinkedHashMap<String, Object>> list = new ArrayList<>();
        XSSFRow row = null;
        XSSFCell cell = null;

        list = tfStockMapper.stockExcel(map);

        Map commMap = commService.codeToNmKV();

        sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, 14));

        XSSFRow topRow = sheet.createRow(0);
        for(int i=0; i< list.size();i++) {
            row = sheet.createRow(i + 1);
            int j = 0;
            for(String key : list.get(i).keySet()) {
                if(i == 0) {
                    cell = topRow.createCell(j);
                    cell.setCellStyle(style);
                    cell.setCellValue(key);
                }else if(i==1){
                    sheet.autoSizeColumn(j);
                    sheet.setColumnWidth(j, (sheet.getColumnWidth(j)) + 800 );
                    if(sheet.getColumnWidth(j) < 2500){
                        sheet.setColumnWidth(j, 2500 );
                    }
                }

                cell = row.createCell(j);
                if(list.get(i).get(key) instanceof String) {
                    cell.setCellValue((String) list.get(i).get(key));
                }else if(list.get(i).get(key) instanceof Integer) {
                    cell.setCellValue((Integer) list.get(i).get(key));
                }else if(list.get(i).get(key) instanceof Date){
                    cell.setCellValue(StringUtil.dateFormat((Date) list.get(i).get(key)));
                }
                j++;
            }
        }

        //파일 전송
        try {
            response.reset();
            response.setContentType( "application/vnd.ms-excel" );
            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.getOutputStream().close();
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
