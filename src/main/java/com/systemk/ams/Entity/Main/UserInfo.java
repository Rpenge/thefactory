package com.systemk.ams.Entity.Main;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user_info")
public class UserInfo{

	@Id
	private Long userInfoSeq;
	
	private String userId;
	
	private String email;
	
	@Column(insertable=false, updatable=false)
	private String password;
	
	private Date userRegDate;
	
	private String empName;
	 
	private String company;
	
	private String dept;
	
	private String position;
	
	private String empNo;
	
	private String location;
	
	private String empPhone;
	
	private String empContact;
	
	private String empAuthorization;

	public Long getUserInfoSeq() {
		return userInfoSeq;
	}

	public void setUserInfoSeq(Long userInfoSeq) {
		this.userInfoSeq = userInfoSeq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getUserRegDate() {
		return userRegDate;
	}

	public void setUserRegDate(Date userRegDate) {
		this.userRegDate = userRegDate;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getEmpContact() {
		return empContact;
	}

	public void setEmpContact(String empContact) {
		this.empContact = empContact;
	}

	public String getEmpAuthorization() {
		return empAuthorization;
	}

	public void setEmpAuthorization(String empAuthorization) {
		this.empAuthorization = empAuthorization;
	}

	@Override
	public String toString() {
		return "UserInfo [userInfoSeq=" + userInfoSeq + ", userId=" + userId + ", email=" + email + ", password="
				+ password + ", userRegDate=" + userRegDate + ", empName=" + empName + ", company=" + company
				+ ", dept=" + dept + ", position=" + position + ", empNo=" + empNo + ", location=" + location
				+ ", empPhone=" + empPhone + ", empContact=" + empContact + ", empAuthorization=" + empAuthorization
				+ "]";
	}

	

}
