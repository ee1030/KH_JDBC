package com.kh.devple.review.model.vo;

import java.sql.Date;

/**
 * @author LeeJS
 *
 */
public class VReview {

	private int revwNo;
	private String memNm;
	private String title;
	private String content;
	private Date createAt;
	
	public VReview() {}

	public VReview(int revwNo, String memNm, String title, String content, Date createAt) {
		super();
		this.revwNo = revwNo;
		this.memNm = memNm;
		this.title = title;
		this.content = content;
		this.createAt = createAt;
	}

	public int getRevwNo() {
		return revwNo;
	}

	public void setRevwNo(int revwNo) {
		this.revwNo = revwNo;
	}

	public String getMemNm() {
		return memNm;
	}

	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@Override
	public String toString() {
		return "VReview [revwNo=" + revwNo + ", memNm=" + memNm + ", title=" + title + ", content=" + content
				+ ", createAt=" + createAt + "]";
	}
}
