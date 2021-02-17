package com.systemk.ams.Entity.Main;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="asset_repair")
public class AssetRepair {
	
	@Id
	private String astCtrlCd;
	
	private String rpCpn;
	
	private String rpCtt;
	
	private int rpQtt;
	
	private int rpEm;
	
	private String rpDt;
	
	private String rpRegPrsn;
	
	private String rpSt;
	
	private Date stDt;
	
	private Date edDt;

	public String getAstCtrlCd() {
		return astCtrlCd;
	}

	public void setAstCtrlCd(String astCtrlCd) {
		this.astCtrlCd = astCtrlCd;
	}

	public String getRpCpn() {
		return rpCpn;
	}

	public void setRpCpn(String rpCpn) {
		this.rpCpn = rpCpn;
	}

	public String getRpCtt() {
		return rpCtt;
	}

	public void setRpCtt(String rpCtt) {
		this.rpCtt = rpCtt;
	}

	public int getRpQtt() {
		return rpQtt;
	}

	public void setRpQtt(int rpQtt) {
		this.rpQtt = rpQtt;
	}

	public int getRpEm() {
		return rpEm;
	}

	public void setRpEm(int rpEm) {
		this.rpEm = rpEm;
	}

	public String getRpDt() {
		return rpDt;
	}

	public void setRpDt(String rpDt) {
		this.rpDt = rpDt;
	}

	public String getRpRegPrsn() {
		return rpRegPrsn;
	}

	public void setRpRegPrsn(String rpRegPrsn) {
		this.rpRegPrsn = rpRegPrsn;
	}

	public String getRpSt() {
		return rpSt;
	}

	public void setRpSt(String rpSt) {
		this.rpSt = rpSt;
	}

	public Date getStDt() {
		return stDt;
	}

	public void setStDt(Date stDt) {
		this.stDt = stDt;
	}

	public Date getEdDt() {
		return edDt;
	}

	public void setEdDt(Date edDt) {
		this.edDt = edDt;
	}

	@Override
	public String toString() {
		return "AssetRepair [astCtrlCd=" + astCtrlCd + ", rpCpn=" + rpCpn + ", rpCtt=" + rpCtt + ", rpQtt=" + rpQtt
				+ ", rpEm=" + rpEm + ", rpDt=" + rpDt + ", rpRegPrsn=" + rpRegPrsn + ", rpSt=" + rpSt + ", stDt=" + stDt
				+ ", edDt=" + edDt + "]";
	}
	
	
	
}
