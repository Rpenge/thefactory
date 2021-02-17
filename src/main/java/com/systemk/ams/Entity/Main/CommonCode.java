package com.systemk.ams.Entity.Main;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="common_code")
public class CommonCode {
	
	@Id
	private String cmmnSeq;
	
	private String code;
	
	private String codeName;
	
	private String parentCode;
	
	private String codeDepth;

	public String getCmmnSeq() {
		return cmmnSeq;
	}

	public void setCmmnSeq(String cmmnSeq) {
		this.cmmnSeq = cmmnSeq;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getCodeDepth() {
		return codeDepth;
	}

	public void setCodeDepth(String codeDepth) {
		this.codeDepth = codeDepth;
	}

	@Override
	public String toString() {
		return "CommonCode [cmmnSeq=" + cmmnSeq + ", code=" + code + ", codeName=" + codeName + ", parentCode="
				+ parentCode + ", codeDepth=" + codeDepth + "]";
	}

	
}
