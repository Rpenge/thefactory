package com.systemk.thefactor2.Service.Impl;


import com.systemk.thefactor2.Mapper.TfStockMapper;
import com.systemk.thefactor2.Service.CommService;
import com.systemk.thefactor2.Service.ExcelService;
import com.systemk.thefactor2.Util.ResultUtil;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;


@Service
public class ExcelServiceImpl implements ExcelService {


    @Autowired
    private CommService commService;

    @Autowired
    private TfStockMapper tfStockMapper;

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
}
