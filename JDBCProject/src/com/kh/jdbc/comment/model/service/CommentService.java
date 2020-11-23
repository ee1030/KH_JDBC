package com.kh.jdbc.comment.model.service;

import static com.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.jdbc.comment.model.dao.CommentDAO;
import com.kh.jdbc.comment.model.vo.Comment;

public class CommentService {
	private CommentDAO cDAO = new CommentDAO();

	/** 댓글 조회 Service
	 * @param boardNo
	 * @return list
	 */
	public List<Map<String, Object>> selectComment(int boardNo) throws Exception {
		Connection conn = getConnection();
		 
		List<Map<String, Object>> list = cDAO.selectComment(conn, boardNo);
		 
		close(conn);
		 
		return list;
	}

	/** 댓글 작성 Service
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int insertComment(Comment comment) throws Exception {
		Connection conn = getConnection();
		
		int result = cDAO.insertComment(conn, comment);
		
		// 트랜잭션 처리
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 댓글 확인 Service
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int checkMyComment(Comment comment) throws Exception {
		Connection conn = getConnection();
		
		int result = cDAO.checkMyComment(conn, comment);
		
		close(conn);
		
		return result;
	}

	/** 댓글 수정 Service
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int updateComment(Comment comment) throws Exception {
		Connection conn = getConnection();
		
		int result = cDAO.updateComment(conn, comment);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 댓글 삭제 Service
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int updateDeleteFl(Comment comment) throws Exception {
		Connection conn = getConnection();
		
		int result = cDAO.updateDeleteFl(conn, comment);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
}
