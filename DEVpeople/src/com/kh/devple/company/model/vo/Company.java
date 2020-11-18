package com.kh.devple.company.model.vo;

/** Company VO
 * @author 유현재
 *
 */
public class Company {

	private int comNo;
	private String comNm;
	private String phone;
	private String email;
	private String comment;
	private int devNo;
	private char cScsnFl;
	
	public Company() {}

	public Company(int comNo, String comNm, String phone, String email, String comment, int devNo, char cScsnFl) {
		super();
		this.comNo = comNo;
		this.comNm = comNm;
		this.phone = phone;
		this.email = email;
		this.comment = comment;
		this.devNo = devNo;
		this.cScsnFl = cScsnFl;
	}

	public int getComNo() {
		return comNo;
	}

	public void setComNo(int comNo) {
		this.comNo = comNo;
	}

	public String getComNm() {
		return comNm;
	}

	public void setComNm(String comNm) {
		this.comNm = comNm;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getDevNo() {
		return devNo;
	}

	public void setDevNo(int devNo) {
		this.devNo = devNo;
	}

	public char getcScsnFl() {
		return cScsnFl;
	}

	public void setcScsnFl(char cScsnFl) {
		this.cScsnFl = cScsnFl;
	}

	@Override
	public String toString() {
		return "Company [comNo=" + comNo + ", comNm=" + comNm + ", phone=" + phone + ", email=" + email + ", comment="
				+ comment + ", devNo=" + devNo + ", cScsnFl=" + cScsnFl + "]";
	}
	
	

}
