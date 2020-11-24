package com.kh.admin.board.model.vo;

import java.sql.Date;

public class VBoard2 {
	private int boardNo;
	private String title;
	private String content;
	private Date createDt;
	private int readCount;
	private String memNm;
	private String categoryNm;
	private char deleteFl;
	
	public VBoard2() {}

	public VBoard2(int boardNo, String title, String content, Date createDt, int readCount, String memNm,
			String categoryNm, char deleteFl) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.content = content;
		this.createDt = createDt;
		this.readCount = readCount;
		this.memNm = memNm;
		this.categoryNm = categoryNm;
		this.deleteFl = deleteFl;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getMemNm() {
		return memNm;
	}

	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}

	public String getCategoryNm() {
		return categoryNm;
	}

	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}

	public char getDeleteFl() {
		return deleteFl;
	}

	public void setDeleteFl(char deleteFl) {
		this.deleteFl = deleteFl;
	}

	@Override
	public String toString() {
		return "VBoard2 [boardNo=" + boardNo + ", title=" + title + ", content=" + content + ", createDt=" + createDt
				+ ", readCount=" + readCount + ", memNm=" + memNm + ", categoryNm=" + categoryNm + ", deleteFl="
				+ deleteFl + "]";
	}
	
	
}
