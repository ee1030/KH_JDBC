package com.kh.admin.member.model.dao;

import static com.kh.admin.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.admin.member.model.vo.Member;

public class MemberDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	private Properties prop = null;
	
	public MemberDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("member-query.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
			
	/** 모든 회원 조회 DAO
	 * @return list
	 */
	public List<Member> selectAllMember(Connection conn) throws Exception {
		List<Member> list = null;
		
		try {
			String query = prop.getProperty("selectAllMember");
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<>();
			
			while(rset.next()) {
				list.add(new Member(rset.getInt(1),
									rset.getString(2),
									rset.getString(3),
									rset.getString(4),
									rset.getString(5),
									rset.getString(6).charAt(0),
									rset.getDate(7),
									rset.getString(8).charAt(0)));
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
				
		return list;
	}

	/** 탈퇴회원 조회 DAO
	 * @param conn
	 * @return list
	 * @throws Exception
	 */
	public List<Member> selectScsnMember(Connection conn) throws Exception {
		List<Member> list = null;
		
		try {
			String query = prop.getProperty("selectScsnMember");
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<>();
			
			while(rset.next()) {
				list.add(new Member(rset.getInt(1),
									rset.getString(2),
									rset.getString(3),
									rset.getString(4),
									rset.getString(5),
									rset.getString(6).charAt(0),
									rset.getDate(7),
									rset.getString(8).charAt(0)));
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
				
		return list;
	}

	/** 탈퇴 회원 복구 DAO
	 * @param conn
	 * @param memNo
	 * @return result
	 */
	public int restoreMember(Connection conn, int memNo) throws Exception {
		int result = 0;
		
		try {
			String query = prop.getProperty("restoreMember");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
