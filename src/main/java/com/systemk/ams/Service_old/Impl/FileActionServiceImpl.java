package com.systemk.ams.Service.Impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.multipart.MultipartFile;

import com.systemk.ams.Config.MultiDataBase.myDataSourceConfig;
import com.systemk.ams.Entity.Main.AssetManagement;
import com.systemk.ams.Service.AssetManagementService;
import com.systemk.ams.Service.CommonService;
import com.systemk.ams.Service.FileActionService;


@Service
public class FileActionServiceImpl implements FileActionService{

	@Autowired
	private myDataSourceConfig myDataSourceConfig;
	
	@Autowired
	private AssetManagementService assetManagementService;
	
	@Autowired
	private CommonService commonService;
	
	@Override
	public boolean assetExcelUpload(MultipartFile mf, String userId) throws SQLException{
		TransactionSynchronizationManager.initSynchronization();
        Connection conn = DataSourceUtils.getConnection(myDataSourceConfig.myDataSource());
        conn.setAutoCommit(false);
		try {
			OPCPackage opcPackage = OPCPackage.open(mf.getInputStream());
			XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
			XSSFSheet sheet = workbook.getSheetAt(0);
			for(int i=1; i<sheet.getLastRowNum() + 1; i++) {
				AssetManagement asset = new AssetManagement();
                XSSFRow row = sheet.getRow(i);
                if(row == null) {
                    continue;
                }
                //기본 데이터
                asset.setAssetRegDate(new Date());							//등록일
                asset.setAssetRegPerson(userId);							//등록자
                //필수 데이터
                asset.setAssetName(row.getCell(0).toString()); 				//자산명
                asset.setAssetDivision(commonService.nameToCode(row.getCell(2).toString()));			//자산분류
                asset.setAssetStatus(commonService.nameToCode(row.getCell(8).toString()));			//자산상태
                asset.setAssetDept(commonService.nameToCode(row.getCell(9).toString()));				//부서
                asset.setAssetLocation(commonService.nameToCode(row.getCell(10).toString()));			//위치
                asset.setAssetQuantity(row.getCell(13).getRawValue());		//수량
                //선택 데이터
                if(row.getCell(1) != null) asset.setModelNumber(row.getCell(1).toString());		//모델번호
                if(row.getCell(3) != null) asset.setSerialNumber(row.getCell(3).toString());	//시리얼번호
                if(row.getCell(4) != null) asset.setManufacturer(row.getCell(4).toString());	//제조사명
                if(row.getCell(5) != null) asset.setAssetBarcode(row.getCell(5).toString());	//바코드
                if(row.getCell(6) != null) asset.setAssetPurchase(row.getCell(6).toString());	//구매처
                if(row.getCell(7) != null) asset.setAssetPrice(row.getCell(7).toString());		//가격
                if(row.getCell(11) != null) asset.setWarranty(row.getCell(11).toString());		//보증기간
                if(row.getCell(12) != null) asset.setAssetContact(row.getCell(12).toString());	//연락처
                if(row.getCell(14) != null) asset.setAssetDetail(row.getCell(14).toString());	//비고 or 상세정보
                
                assetManagementService.assetReg(asset, userId, i);
            }
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return false;
		}finally{
            DataSourceUtils.releaseConnection(conn, myDataSourceConfig.myDataSource());
            TransactionSynchronizationManager.unbindResource(myDataSourceConfig.myDataSource());
            TransactionSynchronizationManager.clearSynchronization();
           
        }
		 return true;
	}

	//excel파일 생성 & 다운로드
	@Override
	public void createExcel(List<Map<String, String>> list, HttpServletResponse response) throws Exception{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		
		XSSFRow row = null;
		XSSFCell cell = null;
		
		XSSFRow topRow = sheet.createRow(0);
//		for (int i=0; i<=15; i++){
//			sheet.autoSizeColumn(i);
//			sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+(short)1024);
//		}


		for(int i=0; i< list.size();i++) {
			row = sheet.createRow(i+1);
			int j = 0;
			for(String key : list.get(i).keySet()) {
				if(i == 0) {
					cell = topRow.createCell(j);
					cell.setCellValue(key);
				}
				cell = row.createCell(j);
				if(list.get(i).get(key) instanceof String) {
					cell.setCellValue(list.get(i).get(key));
				}
				j++;
			}
		}

		//파일 전송
		try {
            response.reset();
            response.setContentType( "application/vnd.ms-excel" );
            //response.addHeader("Content-Disposition","attachment;filename=\"" + URLEncoder.encode("filename", "UTF-8") + ".xlsx\"");
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
