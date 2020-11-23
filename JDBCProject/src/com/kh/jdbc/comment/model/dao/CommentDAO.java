package com.kh.jdbc.comment.model.dao;

import static com.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.jdbc.comment.model.vo.Comment;

public class CommentDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	private Properties prop = null;
	
	public CommentDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("comment-query.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/** 댓글 조회 DAO
	 * @param conn
	 * @param boardNo
	 * @return list
	 */
	public List<Map<String, Object>> selectComment(Connection conn, int boardNo) throws Exception {
		
		List<Map<String, Object>> list = null;
		
		try {
			String query = prop.getProperty("selectComment");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Map<String,Object>>();
			// 조회 결과를 수렴할 수 있는 VO가 없으므로 Map을 활용
			
			while(rset.next()) {
				// COMMENT_NO, CONTENT, CREATE_DT를 Comment 객체에 세팅
				Comment comment = new Comment();
				comment.setCommentNo(rset.getInt("COMMENT_NO"));
				comment.setContent(rset.getString("CONTENT"));
				comment.setCreateDt(rset.getDate("CREATE_DT"));
				
				
				// comment, MEM_NM 두 값을 Map에 저장
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("comment", comment);
				map.put("memNm", rset.getString("MEM_NM"));
				
				list.add(map);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	/** 댓글 작성 DAO
	 * @param conn
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int insertComment(Connection conn, Comment comment) throws Exception {
		int result = 0;
		
		try {
			String query = prop.getProperty("insertComment");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comment.getContent());
			pstmt.setInt(2, comment.getMemNo());
			pstmt.setInt(3, comment.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 댓글 확인 DAO
	 * @param conn
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int checkMyComment(Connection conn, Comment comment) throws Exception {
		int result = 0;
		
		try {
			String query = prop.getProperty("checkMyComment");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comment.getBoardNo());
			pstmt.setInt(2, comment.getCommentNo());
			pstmt.setInt(3, comment.getMemNo());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}


	/** 댓글 수정 DAO
	 * @param conn
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int updateComment(Connection conn, Comment comment) throws Exception {
		
		int result = 0;
		
		try {
			String query = prop.getProperty("updateComment");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comment.getContent());
			pstmt.setInt(2, comment.getCommentNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 댓글 삭제 DAO
	 * @param conn
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int updateDeleteFl(Connection conn, Comment comment) throws Exception {
		int result = 0;
		
		try {
			String query = prop.getProperty("updateDeleteFl");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comment.getCommentNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
			
}
