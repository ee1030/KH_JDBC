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
	private String introduction;
	private int memNo;
	private char cScsnFl;
	
	public Company() {}

	

	public Company(String comNm, int memNo) {
		super();
		this.comNm = comNm;
		this.memNo = memNo;
	}

	public Company(String comNm, String phone, String email, String introduction) {
		super();
		this.comNm = comNm;
		this.phone = phone;
		this.email = email;
		this.introduction = introduction;
	}

	public Company(String comNm, String phone, String email, String introduction, int memNo) {
		super();
		this.comNm = comNm;
		this.phone = phone;
		this.email = email;
		this.introduction = introduction;
		this.memNo = memNo;
	}

	public Company(int comNo, String comNm, String phone, String email, String introduction, int memNo, char cScsnFl) {
		super();
		this.comNo = comNo;
		this.comNm = comNm;
		this.phone = phone;
		this.email = email;
		this.introduction = introduction;
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

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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
		return "Company [comNo=" + comNo + ", comNm=" + comNm + ", phone=" + phone + ", email=" + email + ", introduction="
				+ introduction + ", memNo=" + memNo + ", cScsnFl=" + cScsnFl + "]";
	}
	
	

}
