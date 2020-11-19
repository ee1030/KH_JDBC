package com.kh.devple.company.model.dao;

import static com.kh.devple.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.kh.devple.company.model.vo.Company;

/** 회사 관련 DAO
 * @author 유현재
 *
 */
public class CompanyDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	private Properties prop = null;
	
	public CompanyDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("company-query.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 회사 정보 등록 DAO
	 * @param conn
	 * @param company
	 * @return result
	 */
	public int insertCompany(Connection conn, Company company) throws Exception {
		int result = 0;
		
		try {
			String query = prop.getProperty("insertCompany");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, company.getComNm());
			pstmt.setString(2, company.getPhone());
			pstmt.setString(3, company.getEmail());
			pstmt.setString(4, company.getComment());
			pstmt.setInt(5, company.getMemNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 회사 정보 수정 DAO
	 * @param conn
	 * @param company
	 * @return result
	 * @throws Exception
	 */
	public int updateCompany(Connection conn, Company company) throws Exception {
		int result = 0;
		
		try {
			String query = prop.getProperty("updateCompany");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, company.getComNm());
			pstmt.setString(2, company.getPhone());
			pstmt.setString(3, company.getEmail());
			pstmt.setString(4, company.getComment());
			pstmt.setInt(5, company.getMemNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 회사 정보 삭제 DAO
	 * @param conn
	 * @param upCompany
	 * @return result
	 * @throws Exception
	 */
	public int updateSecessionCompany(Connection conn, Company upCompany) throws Exception {
		int result = 0;
		
		try {
			String query = prop.getProperty("updateSecessionCompany");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, upCompany.getMemNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 회사 번호 조회 DAO
	 * @param conn
	 * @param memNo
	 * @return result
	 * @throws Exception
	 */
	public int findComNo(Connection conn, int memNo) throws Exception {
		int result = 0;
		
		try {
			String query = prop.getProperty("findComNo");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			rset.next();
			result = rset.getInt(1);
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

}
