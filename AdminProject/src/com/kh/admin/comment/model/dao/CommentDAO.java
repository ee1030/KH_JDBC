package com.kh.admin.comment.model.dao;

import static com.kh.admin.common.JDBCTemplate.*;

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

import com.kh.admin.comment.model.vo.Comment;

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

	public List<Map<String, Object>> selectDeleteComment(Connection conn) throws Exception {
		
		List<Map<String, Object>> list = null;
		
		try {
			String query = prop.getProperty("selectDeleteComment");
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<>();
			
			while(rset.next()) {
				Comment comment = new Comment();
				comment.setCommentNo(rset.getInt("COMMENT_NO"));
				comment.setContent(rset.getString("CONTENT"));
				comment.setCreateDt(rset.getDate("CREATE_DT"));
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("comment", comment);
				map.put("memNm", rset.getString("MEM_NM"));
				
				list.add(map);
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	/** 댓글 복구 DAO
	 * @param conn
	 * @param commentNo
	 * @return result
	 */
	public int restoreComment(Connection conn, int commentNo) throws Exception {
		int result = 0;
		
		try {
			String query = prop.getProperty("restoreComment");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
