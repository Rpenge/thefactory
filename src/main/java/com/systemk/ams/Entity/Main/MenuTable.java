package com.systemk.ams.Entity.Main;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="menu_table")
public class MenuTable {
	
	@Id
	private Long menuTableSeq;
	
	private String role;
	
	private String menuCode;
	
	private String menuName;

	public Long getMenuTableSeq() {
		return menuTableSeq;
	}

	public void setMenuTableSeq(Long menuTableSeq) {
		this.menuTableSeq = menuTableSeq;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Override
	public String toString() {
		return "MenuTable [menuTableSeq=" + menuTableSeq + ", role=" + role + ", menuCode=" + menuCode + ", menuName="
				+ menuName + "]";
	}


	
	
	
	
}
