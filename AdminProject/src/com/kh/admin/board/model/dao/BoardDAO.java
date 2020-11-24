package com.kh.admin.board.model.dao;

import static com.kh.admin.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.admin.board.model.vo.VBoard;
import com.kh.admin.board.model.vo.VBoard2;

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
	
	/** 모든 게시글 조회 DAO
	 * @param conn
	 * @return list
	 */
	public List<VBoard2> selectAllBoard(Connection conn) throws Exception {
		List<VBoard2> list = null;
		
		try {
			String query = prop.getProperty("selectAllBoard");
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<>();
			
			while(rset.next()) {
				list.add(new VBoard2(rset.getInt("BOARD_NO"),
						rset.getString("TITLE"),
						rset.getNString("CONTENT"),
						rset.getDate("CREATE_DT"),
						rset.getInt("READ_COUNT"),
						rset.getString("MEM_NM"),
						rset.getString("CATEGORY_NM"),
						rset.getString("DELETE_FL").charAt(0)));
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	/** 삭제된 게시글 조회 DAO
	 * @param conn
	 * @return list
	 */
	public List<VBoard> selectDeleteBoard(Connection conn) throws Exception {
		List<VBoard> list = null;
		
		try {
			String query = prop.getProperty("selectDeleteBoard");
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<>();
			
			while(rset.next()) {
				list.add(new VBoard(rset.getInt("BOARD_NO"),
						rset.getString("TITLE"),
						rset.getNString("CONTENT"),
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

	/** 게시글 복구 DAO
	 * @param conn
	 * @param boardNo
	 * @return result
	 */
	public int restoreBoard(Connection conn, int boardNo) throws Exception {
		int result = 0;
		
		try {
			String query = prop.getProperty("restoreBoard");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

}
