package com.systemk.ams.Entity.Main;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class Company {


	@Id
	private String cpnCd;
	private String cpnNm;
	private String cpnPic;
	private String cpnCnn;
	private String cpnDiv;
	private String cpnAdr;
	private String cpnDt;
	private String cpnReg;
	private Date cpnRegDt;
	public String getCpnCd() {
		return cpnCd;
	}
	public void setCpnCd(String cpnCd) {
		this.cpnCd = cpnCd;
	}
	public String getCpnNm() {
		return cpnNm;
	}
	public void setCpnNm(String cpnNm) {
		this.cpnNm = cpnNm;
	}
	public String getCpnPic() {
		return cpnPic;
	}
	public void setCpnPic(String cpnPic) {
		this.cpnPic = cpnPic;
	}
	public String getcpnCnn() {
		return cpnCnn;
	}
	public void setcpnCnn(String cpnCnn) {
		this.cpnCnn = cpnCnn;
	}
	public String getCpnDiv() {
		return cpnDiv;
	}
	public void setCpnDiv(String cpnDiv) {
		this.cpnDiv = cpnDiv;
	}
	public String getCpnAdr() {
		return cpnAdr;
	}
	public void setCpnAdr(String cpnAdr) {
		this.cpnAdr = cpnAdr;
	}
	public String getCpnDt() {
		return cpnDt;
	}
	public void setCpnDt(String cpnDt) {
		this.cpnDt = cpnDt;
	}
	public String getCpnReg() {
		return cpnReg;
	}
	public void setCpnReg(String cpnReg) {
		this.cpnReg = cpnReg;
	}
	public Date getCpnRegDt() {
		return cpnRegDt;
	}
	public void setCpnRegDt(Date cpnRegDt) {
		this.cpnRegDt = cpnRegDt;
	}
	@Override
	public String toString() {
		return "Company [cpnCd=" + cpnCd + ", cpnNm=" + cpnNm + ", cpnPic=" + cpnPic + ", cpnCnn=" + cpnCnn
				+ ", cpnDiv=" + cpnDiv + ", cpnAdr=" + cpnAdr + ", cpnDt=" + cpnDt + ", cpnReg=" + cpnReg
				+ ", cpnRegDt=" + cpnRegDt + "]";
	}
	
}
