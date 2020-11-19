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
	private int memNo;
	private char cScsnFl;
	
	public Company() {}

	

	public Company(String comNm, int memNo) {
		super();
		this.comNm = comNm;
		this.memNo = memNo;
	}



	public Company(String comNm, String phone, String email, String comment) {
		super();
		this.comNm = comNm;
		this.phone = phone;
		this.email = email;
		this.comment = comment;
	}



	public Company(String comNm, String phone, String email, String comment, int memNo) {
		super();
		this.comNm = comNm;
		this.phone = phone;
		this.email = email;
		this.comment = comment;
		this.memNo = memNo;
	}

	public Company(int comNo, String comNm, String phone, String email, String comment, int memNo, char cScsnFl) {
		super();
		this.comNo = comNo;
		this.comNm = comNm;
		this.phone = phone;
		this.email = email;
		this.comment = comment;
		this.memNo = memNo;
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

	public int getMemNo() {
		return memNo;
	}

	public void setDevNo(int memNo) {
		this.memNo = memNo;
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
				+ comment + ", memNo=" + memNo + ", cScsnFl=" + cScsnFl + "]";
	}
	
	

}
