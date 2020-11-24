package com.kh.admin.comment.model.service;

import static com.kh.admin.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.admin.comment.model.dao.CommentDAO;

public class CommentService {
	
	private CommentDAO cDAO = new CommentDAO();
	
	/** 삭제된 댓글 조회 Service
	 * @return list
	 */
	public List<Map<String, Object>> selectDeleteComment() throws Exception {
		Connection conn = getConnection();
		
		List<Map<String, Object>> list = cDAO.selectDeleteComment(conn);
		
		close(conn);
		
		return list;
	}

	/** 댓글 복구 Service
	 * @param commentNo
	 * @return result
	 */
	public int restoreComment(int commentNo) throws Exception {
		Connection conn = getConnection();
		
		int result = cDAO.restoreComment(conn, commentNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

}
