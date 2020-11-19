package com.kh.devple.offer.model.vo;

public class VOffer {
	private int joNo;
	private String comNm;
	private String location;
	private int sal;
	private int term;
	private String job;
	private int comNo;
	
	public VOffer() {}

	public VOffer(int joNo, String comNm, String location, int sal, int term, String job) {
		super();
		this.joNo = joNo;
		this.comNm = comNm;
		this.location = location;
		this.sal = sal;
		this.term = term;
		this.job = job;
	}
	
	public VOffer(int joNo, String comNm, String location, int sal, int term, String job, int comNo) {
		super();
		this.joNo = joNo;
		this.comNm = comNm;
		this.location = location;
		this.sal = sal;
		this.term = term;
		this.job = job;
		this.comNo = comNo;
	}

	public int getJoNo() {
		return joNo;
	}

	public void setJoNo(int joNo) {
		this.joNo = joNo;
	}

	public String getComNm() {
		return comNm;
	}

	public void setComNm(String comNm) {
		this.comNm = comNm;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getComNo() {
		return comNo;
	}

	public void setComNo(int comNo) {
		this.comNo = comNo;
	}

	@Override
	public String toString() {
		return "VOffer [joNo=" + joNo + ", comNm=" + comNm + ", location=" + location + ", sal=" + sal + ", term="
				+ term + ", job=" + job + "]";
	}
	
}
