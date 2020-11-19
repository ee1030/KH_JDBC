package com.kh.devple.offer.model.vo;

/** Offer VO
 * @author 유현재
 *
 */
public class Offer {
	
	private int joNo;
	private String location;
	private int sal;
	private int term;
	private String job;
	private int comNo;
	
	public Offer() {}


	public Offer(String location, int sal, int term, String job, int comNo) {
		super();
		this.location = location;
		this.sal = sal;
		this.term = term;
		this.job = job;
		this.comNo = comNo;
	}



	public Offer(int joNo, String location, int sal, int term, String job, int comNo) {
		super();
		this.joNo = joNo;
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
		return "Offer [joNo=" + joNo + ", location=" + location + ", sal=" + sal + ", term=" + term + ", job=" + job
				+ ", comNo=" + comNo + "]";
	}
	
}
