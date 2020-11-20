package com.kh.devple.review.model.dao;

import static com.kh.devple.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.devple.review.model.vo.Review;
import com.kh.devple.review.model.vo.VReview;

public class ReviewDAO {

	
	private Statement stmt = null;
	private ResultSet rset = null;
	private PreparedStatement pstmt= null;
	
	private Properties prop = null;
	
	public ReviewDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("review-query.xml"));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 이진선
	 * @param conn
	 * @return list
	 * @throws Exception
	 */
	public List<VReview> selectReview(Connection conn) throws Exception {
		
		List<VReview> list  = null;
		
		try {
			
			String query = prop.getProperty("selectReview");
					
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<VReview>();
			
			while(rset.next()) {
				list.add(new VReview ( rset.getInt("REVW_NO"),
										rset.getString("MEM_NM"),
										rset.getString("TITLE"),
										rset.getString("CONTENT"),
										rset.getDate("CREATE_AT")
								));
				
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		
		return list;
	}

	/** 리뷰 작성 DAO
	 * @author 이진선
	 * @param conn
	 * @param review
	 * @return result
	 * @throws Exception
	 */
	public int insertReview(Connection conn, Review review) throws Exception{
		
		int result = 0;
		try {
			String query = prop.getProperty("insertReview");
			
			pstmt = conn.prepareStatement(query);
			//제목, 내용, 넘버
			pstmt.setString(1, review.getTitle());
			pstmt.setString(2, review.getContent());
			pstmt.setInt(3, review.getmemNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
}
