package com.kh.jdbc.board.model.service;

import static com.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.jdbc.board.model.dao.BoardDAO;
import com.kh.jdbc.board.model.vo.Board;
import com.kh.jdbc.board.model.vo.VBoard;

public class BoardService {
	private BoardDAO bDAO = new BoardDAO();

	/** 게시글 목록 조회 Service
	 * @return list
	 * @throws Exception
	 */
	public List<VBoard> selectAllBaord() throws Exception {
		// 커넥션 얻어오기
		Connection conn = getConnection();
		
		// DAO 메소드 호출 후 결과를 반환 받음
		List<VBoard> list = bDAO.selectAllBoard(conn);
		
		close(conn);
		
		return list;
	}

	/** 게시글 상세 조회 Service
	 * @param boardNo
	 * @return vboard
	 * @throws Exception
	 */
	public VBoard selectBoard(int boardNo) throws Exception {
		Connection conn = getConnection();
		
		VBoard vboard = bDAO.selectBoard(conn, boardNo);
		
		// DB에서 게시글 정보를 성공적으로 조회를 해왔을 때
		// --> 해당 게시글의 조회수를 증가
		if(vboard != null) { // 성공적으로 조회함
			
			// DB에서 해당 글의 조회수를 증가시킬 수 있는 DAO 메소드 호출
			// --> UPDATE 수행
			int result = bDAO.updateReadCount(conn, boardNo);
			
			// 트랜잭션 처리
			if(result > 0) {
				commit(conn);
				
				// 조회수 증가 성공 시 이미 읽어온 게시글 데이터의 조회수도 1 증가시킴
				vboard.setReadCount(vboard.getReadCount()+1);
			}
			else {
				rollback(conn);
			}
		}
		
		close(conn);
		
		return vboard;
	}

	/** 게시글 작성 Service
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Board board) throws Exception {
		Connection conn = getConnection();
		
		int result = bDAO.insertBoard(conn, board);
		
		// 트랜잭션 처리
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
}
