package com.kh.jdbc.board.model.dao;

import static com.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.jdbc.board.model.vo.Board;
import com.kh.jdbc.board.model.vo.VBoard;

public class BoardDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	private Properties prop = null;
	
	public BoardDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("board-query.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 게시글 목록 조회 DAO
	 * @param conn
	 * @return list
	 * @throws Exception
	 */
	public List<VBoard> selectAllBoard(Connection conn) throws Exception {
		// 조회 결과를 저장하고 반환할 변수 선언
		List<VBoard> list = null;
		
		try {
			String query = prop.getProperty("selectAllBoard");
			
			stmt = conn.createStatement();
			
			// SQL 수행 후 조회 결과 반환
			rset = stmt.executeQuery(query);
			
			// SQL 수행 시 문제가 없었다면 조회 내용을 저장할 list 객체를 생성
			list = new ArrayList<>();
			
			while(rset.next()) {
				list.add(new VBoard(rset.getInt("BOARD_NO"),
									rset.getString("TITLE"),
									rset.getDate("CREATE_DT"), 
									rset.getInt("READ_COUNT"),
									rset.getString("MEM_NM"),
									rset.getString("CATEGORY_NM")));
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public VBoard selectBoard(Connection conn, int boardNo) throws Exception {
		
		VBoard vboard = null;
		
		try {
			String query = prop.getProperty("selectBoard");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			rset.next();
			vboard = new VBoard(rset.getInt("BOARD_NO"),
								rset.getString("TITLE"),
								rset.getNString("CONTENT"),
								rset.getDate("CREATE_DT"),
								rset.getInt("READ_COUNT"),
								rset.getString("MEM_NM"),
								rset.getString("CATEGORY_NM"));
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return vboard;
	}

	/** 게시글 조회수 증가 DAO
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int updateReadCount(Connection conn, int boardNo) throws Exception {
		int result = 0;
		
		try {
			String query = prop.getProperty("updateReadCount");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			// SQL 수행 후 결과를 반환 받음
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 게시글 작성 DAO
	 * @param conn
	 * @param board
	 * @return result
	 */
	public int insertBoard(Connection conn, Board board) throws Exception {
		int result = 0;
		
		try {
			String query = prop.getProperty("insertBoard");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getMemNo());
			pstmt.setInt(4,  board.getCategoryCd());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
}
