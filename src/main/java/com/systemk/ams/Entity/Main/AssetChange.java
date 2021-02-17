package com.systemk.ams.Entity.Main;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="asset_change")
public class AssetChange {
	
	@Id
	private long astChgSeq;
	
	private String astCtrlCd;
	
	private String astNm;
	
	private Date chgDt;
	
	private String astSt;
	
	private String astWk;
	
	private String wkPrsn;
	
	private String wkLoc;

	private String wkEnv;
	
	private String astDiv;
	
	private String astDp;
	
	private String wiYn;
	
	private String updateYn;

	public long getAstChgSeq() {
		return astChgSeq;
	}

	public void setAstChgSeq(long astChgSeq) {
		this.astChgSeq = astChgSeq;
	}

	public String getAstCtrlCd() {
		return astCtrlCd;
	}

	public void setAstCtrlCd(String astCtrlCd) {
		this.astCtrlCd = astCtrlCd;
	}

	public String getAstNm() {
		return astNm;
	}

	public void setAstNm(String astNm) {
		this.astNm = astNm;
	}

	public Date getChgDt() {
		return chgDt;
	}

	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}

	public String getAstSt() {
		return astSt;
	}

	public void setAstSt(String astSt) {
		this.astSt = astSt;
	}

	public String getAstWk() {
		return astWk;
	}

	public void setAstWk(String astWk) {
		this.astWk = astWk;
	}

	public String getWkPrsn() {
		return wkPrsn;
	}

	public void setWkPrsn(String wkPrsn) {
		this.wkPrsn = wkPrsn;
	}

	public String getWkLoc() {
		return wkLoc;
	}

	public void setWkLoc(String wkLoc) {
		this.wkLoc = wkLoc;
	}

	public String getWkEnv() {
		return wkEnv;
	}

	public void setWkEnv(String wkEnv) {
		this.wkEnv = wkEnv;
	}

	public String getAstDiv() {
		return astDiv;
	}

	public void setAstDiv(String astDiv) {
		this.astDiv = astDiv;
	}

	public String getAstDp() {
		return astDp;
	}

	public void setAstDp(String astDp) {
		this.astDp = astDp;
	}

	public String getWiYn() {
		return wiYn;
	}

	public void setWiYn(String wiYn) {
		this.wiYn = wiYn;
	}

	public String getUpdateYn() {
		return updateYn;
	}

	public void setUpdateYn(String updateYn) {
		this.updateYn = updateYn;
	}

	@Override
	public String toString() {
		return "AssetChange [astChgSeq=" + astChgSeq + ", astCtrlCd=" + astCtrlCd + ", astNm=" + astNm + ", chgDt="
				+ chgDt + ", astSt=" + astSt + ", astWk=" + astWk + ", wkPrsn=" + wkPrsn + ", wkLoc=" + wkLoc
				+ ", wkEnv=" + wkEnv + ", astDiv=" + astDiv + ", astDp=" + astDp + ", wiYn=" + wiYn + ", updateYn="
				+ updateYn + "]";
	}

	
	
}
