package com.kh.admin.board.model.service;

import static com.kh.admin.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.admin.board.model.dao.BoardDAO;
import com.kh.admin.board.model.vo.VBoard;
import com.kh.admin.board.model.vo.VBoard2;

public class BoardService {
	
	private BoardDAO bDAO = new BoardDAO(); 

	/** 모든 게시글 조회 Service
	 * @return list
	 */
	public List<VBoard2> selectAllBoard() throws Exception {
		Connection conn = getConnection();
		
		List<VBoard2> list = bDAO.selectAllBoard(conn);
		
		close(conn);
		
		return list;
	}

	/** 삭제된 게시글 조회 Service
	 * @return list
	 * @throws Exception 
	 */
	public List<VBoard> selectDeleteBoard() throws Exception {
		Connection conn = getConnection();
		
		List<VBoard> list = bDAO.selectDeleteBoard(conn);
		
		close(conn);
		
		return list;
	}

	/** 게시글 복구 Service
	 * @param boardNo
	 * @return result
	 */
	public int restoreBoard(int boardNo) throws Exception {
		Connection conn = getConnection();
		
		int result = bDAO.restoreBoard(conn, boardNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

}
