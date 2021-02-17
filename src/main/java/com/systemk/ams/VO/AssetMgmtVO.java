package com.systemk.ams.VO;


import lombok.Data;

import java.util.Date;

@Data
public class AssetMgmtVO {

	private Long assetManagementSeq;

	private String assetControlCode;
	
	private String assetName;
	
	private String modelNumber;
	
	private String assetDivision;
	
	private String serialNumber;

	private String manufacturer;
	
	private Date assetRegDate;
	
	private String assetRegPerson;
	
	private String assetBarcode;
	
	private String assetPurchase;
	
	private String assetPrice;
	
	private String assetStatus;
	
	private String assetDept;
	
	private String assetLocation;
	
	private String warranty;
	
	private String assetContact;
	
	private String assetQuantity;
	
	private String assetDetail;

	private String mappingYn;

}
