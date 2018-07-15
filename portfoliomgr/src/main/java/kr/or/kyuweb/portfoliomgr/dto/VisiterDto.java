package kr.or.kyuweb.portfoliomgr.dto;

import java.sql.Date;

public class VisiterDto {
	
	private int id;
	private String name;
	private String password;
	private String email;
	private String organization;
	private Date createDate;
	private Date lastLoginDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	@Override
	public String toString() {
		return "Visiter [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", organization=" + organization + ", createDate=" + createDate + ", lastLoginDate=" + lastLoginDate
				+ "]";
	}
	
	

}
