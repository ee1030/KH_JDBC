package com.kh.jdbc.board.model.service;

import static com.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

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

	/** 게시글이 로그인한 회원의 글인지 판별하는 Service
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int checkMyBoard(Board board) throws Exception {
		Connection conn = getConnection();
		
		int result = bDAO.checkMyBoard(conn, board);
		
		close(conn);
		
		return result;
	}

	/** 게시글 수정 Service
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Board board) throws Exception {
		Connection conn = getConnection();
		
		int result = bDAO.updateBoard(conn, board);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 게시글 삭제 Service
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int updateDeleteFl(int boardNo) throws Exception {
		Connection conn = getConnection();
		
		int result = bDAO.updateDeleteFl(conn, boardNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	/** 랜덤 문자열 생성 Service
	 * @return str
	 */
	public String randomString() {
		String str = "";
		
		// 랜덤으로 영어 소문자 6개가 합쳐진 문자열 생성
		for(int i = 0; i < 6; i++) {
			// 랜덤 소문자 발생
			char ran = (char)((int)(Math.random()*26)+97); 
			str += ran;
		}
		
		return str;
	}

	/** 게시글 검색 Service
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	public List<VBoard> searchBoard(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();
		
		// 쿼리 조합 진행
		// 동일한 부분을 미리 작성하고, 다른 부분들을 별도로 조합
		String query = "SELECT * FROM V_BOARD WHERE ";
		
		String like = "LIKE '%" + map.get("keyword") + "%' ";
		
		switch((int)map.get("sel")) {
		// -> Map의 Value가 Object 타입이므로 알맞은 형태의 형변환(다운캐스팅)이 필요
		case 1: query += "TITLE " + like; break;
		case 2: query += "CONTENT " + like; break;
		case 3: query +=  "TITLE " + like + "OR CONTENT " + like; break;
		case 4: query += "MEM_NM " + like; break;	
		}
		query += "ORDER BY BOARD_NO DESC";
		
		List<VBoard> list = bDAO.searchBoard(conn, query);
		
		close(conn);
		
		return list;
	}
	
}
