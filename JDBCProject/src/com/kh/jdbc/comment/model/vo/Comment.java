package com.kh.jdbc.comment.model.vo;

import java.sql.Date;

public class Comment {
	private int commentNo;
	private String content;
	private Date createDt;
	private char deleteFl;
	private int memNo;
	private int boardNo;
	
	public Comment() {}

	public Comment(int commentNo, String content, Date createDt, char deleteFl, int memNo, int boardNo) {
		super();
		this.commentNo = commentNo;
		this.content = content;
		this.createDt = createDt;
		this.deleteFl = deleteFl;
		this.memNo = memNo;
		this.boardNo = boardNo;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
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

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", content=" + content + ", createDt=" + createDt + ", deleteFl="
				+ deleteFl + ", memNo=" + memNo + ", boardNo=" + boardNo + "]";
	}

}
