package com.kh.jdbc.board.model.vo;

import java.sql.Date;

public class Board {
	private int boardNo;
	private String title;
	private String content;
	private Date createDt;
	private int readCount;
	private char deleteFl;
	private int memNo;
	private int categoryCd;
	
	public Board() {}
	
	

	public Board(String title, String content, int memNo, int categoryCd) {
		super();
		this.title = title;
		this.content = content;
		this.memNo = memNo;
		this.categoryCd = categoryCd;
	}

	public Board(int boardNo, String title, String content, Date createDt, int readCount, char deleteFl, int memNo,
			int categoryCd) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.content = content;
		this.createDt = createDt;
		this.readCount = readCount;
		this.deleteFl = deleteFl;
		this.memNo = memNo;
		this.categoryCd = categoryCd;
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

	public char getDeleteFl() {
		return deleteFl;
	}

	public void setDeleteFl(char deleteFl) {
		this.deleteFl = deleteFl;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getCategoryCd() {
		return categoryCd;
	}

	public void setCategoryCd(int categoryCd) {
		this.categoryCd = categoryCd;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", title=" + title + ", content=" + content + ", createDt=" + createDt
				+ ", readCount=" + readCount + ", deleteFl=" + deleteFl + ", memNo=" + memNo + ", categoryCd="
				+ categoryCd + "]";
	}
	
	
}
