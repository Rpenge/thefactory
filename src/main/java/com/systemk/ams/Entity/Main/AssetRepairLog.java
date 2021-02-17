package com.systemk.ams.Entity.Main;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="asset_repair_log")
public class AssetRepairLog {
	
	
	@Id
	private long rpSeq;
	
	private String astCtrlCd;
	
	private String astNm;
	
	private String astDiv;
	
	private String rpCpn;
	
	private String rpCpnCnn;
	
	private String rpCpnAdr;
	
	private String rpCpnPic;
	
	private String rpCtt;
	
	private int rpQtt;
	
	private int rpEm;
	
	private String rpDt;
	
	private String rpRegPrsn;
	
	private String rpSt;
	
	private Date regDt;
	
	private Date stDt;
	
	private Date edDt;

	public long getRpSeq() {
		return rpSeq;
	}

	public void setRpSeq(long rpSeq) {
		this.rpSeq = rpSeq;
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

	public String getAstDiv() {
		return astDiv;
	}

	public void setAstDiv(String astDiv) {
		this.astDiv = astDiv;
	}

	public String getRpCpn() {
		return rpCpn;
	}

	public void setRpCpn(String rpCpn) {
		this.rpCpn = rpCpn;
	}

	public String getrpCpnCnn() {
		return rpCpnCnn;
	}

	public void setrpCpnCnn(String rpCpnCnn) {
		this.rpCpnCnn = rpCpnCnn;
	}

	public String getRpCpnAdr() {
		return rpCpnAdr;
	}

	public void setRpCpnAdr(String rpCpnAdr) {
		this.rpCpnAdr = rpCpnAdr;
	}

	public String getRpCpnPic() {
		return rpCpnPic;
	}

	public void setRpCpnPic(String rpCpnPic) {
		this.rpCpnPic = rpCpnPic;
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

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
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
		return "AssetRepairLog [rpSeq=" + rpSeq + ", astCtrlCd=" + astCtrlCd + ", astNm=" + astNm + ", astDiv=" + astDiv
				+ ", rpCpn=" + rpCpn + ", rpCpnCnn=" + rpCpnCnn + ", rpCpnAdr=" + rpCpnAdr + ", rpCpnPic=" + rpCpnPic
				+ ", rpCtt=" + rpCtt + ", rpQtt=" + rpQtt + ", rpEm=" + rpEm + ", rpDt=" + rpDt + ", rpRegPrsn="
				+ rpRegPrsn + ", rpSt=" + rpSt + ", regDt=" + regDt + ", stDt=" + stDt + ", edDt=" + edDt + "]";
	}

}
