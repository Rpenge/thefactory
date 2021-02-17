package com.systemk.ams.Entity.Main;



import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="asset_management")
public class AssetMgJoinChange {
	
	private Long assetManagementSeq;
	
	@Id
	private String assetControlCode;
	
	private String assetName;
	
	private String modelNumber;
	
	private String assetDivision;
	
	private String serialNumber;

	private String manufacturer;
	
	private Date assetRegDate;
	
	@Transient
	private String dateForm;
	
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
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY, orphanRemoval = true)
	@OrderBy("chgDt DESC")
	@JoinColumn(name="astCtrlCd")
	private List<AssetChange> assetChange;
	
	
	public Long getAssetManagementSeq() {
		return assetManagementSeq;
	}

	public void setAssetManagementSeq(Long assetManagementSeq) {
		this.assetManagementSeq = assetManagementSeq;
	}
	
	public String getAssetControlCode() {
		return assetControlCode;
	}

	public void setAssetControlCode(String assetControlCode) {
		this.assetControlCode = assetControlCode;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getAssetDivision() {
		return assetDivision;
	}

	public void setAssetDivision(String assetDivision) {
		this.assetDivision = assetDivision;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Date getAssetRegDate() {
		return assetRegDate;
	}

	public void setAssetRegDate(Date assetRegDate) {
		this.assetRegDate = assetRegDate;
	}

	public String getAssetRegPerson() {
		return assetRegPerson;
	}

	public void setAssetRegPerson(String assetRegPerson) {
		this.assetRegPerson = assetRegPerson;
	}

	public String getAssetBarcode() {
		return assetBarcode;
	}

	public void setAssetBarcode(String assetBarcode) {
		this.assetBarcode = assetBarcode;
	}

	public String getAssetPurchase() {
		return assetPurchase;
	}

	public void setAssetPurchase(String assetPurchase) {
		this.assetPurchase = assetPurchase;
	}

	public String getAssetPrice() {
		return assetPrice;
	}

	public void setAssetPrice(String assetPrice) {
		this.assetPrice = assetPrice;
	}

	public String getAssetStatus() {
		return assetStatus;
	}

	public void setAssetStatus(String assetStatus) {
		this.assetStatus = assetStatus;
	}

	public String getAssetDept() {
		return assetDept;
	}

	public void setAssetDept(String assetDept) {
		this.assetDept = assetDept;
	}

	public String getAssetLocation() {
		return assetLocation;
	}

	public void setAssetLocation(String assetLocation) {
		this.assetLocation = assetLocation;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public String getAssetContact() {
		return assetContact;
	}

	public void setAssetContact(String assetContact) {
		this.assetContact = assetContact;
	}

	public String getAssetQuantity() {
		return assetQuantity;
	}

	public void setAssetQuantity(String assetQuantity) {
		this.assetQuantity = assetQuantity;
	}

	public String getAssetDetail() {
		return assetDetail;
	}

	public void setAssetDetail(String assetDetail) {
		this.assetDetail = assetDetail;
	}

	public String getDateForm() {
		return dateForm;
	}

	public void setDateForm(String dateForm) {
		this.dateForm = dateForm;
	}

	public String getMappingYn() {
		return mappingYn;
	}

	public void setMappingYn(String mappingYn) {
		this.mappingYn = mappingYn;
	}

	
	public List<AssetChange> getAssetChange() {
		return assetChange;
	}

	public void setAssetChange(List<AssetChange> assetChange) {
		this.assetChange = assetChange;
	}

	@Override
	public String toString() {
		return "AssetMgJoinChange [assetManagementSeq=" + assetManagementSeq + ", assetControlCode=" + assetControlCode
				+ ", assetName=" + assetName + ", modelNumber=" + modelNumber + ", assetDivision=" + assetDivision
				+ ", serialNumber=" + serialNumber + ", manufacturer=" + manufacturer + ", assetRegDate=" + assetRegDate
				+ ", dateForm=" + dateForm + ", assetRegPerson=" + assetRegPerson + ", assetBarcode=" + assetBarcode
				+ ", assetPurchase=" + assetPurchase + ", assetPrice=" + assetPrice + ", assetStatus=" + assetStatus
				+ ", assetDept=" + assetDept + ", assetLocation=" + assetLocation + ", warranty=" + warranty
				+ ", assetContact=" + assetContact + ", assetQuantity=" + assetQuantity + ", assetDetail=" + assetDetail
				+ ", mappingYn=" + mappingYn + ", assetChange=" + assetChange + "]";
	}


}
