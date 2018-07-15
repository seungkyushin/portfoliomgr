package kr.or.kyuweb.portfoliomgr.dto;

import java.util.Date;

public class LogDto {

	private int id;
	private Date createDate;
	private String type;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Log [id=" + id + ", createDate=" + createDate + ", type=" + type + ", description=" + description + "]";
	}
	
	
}
