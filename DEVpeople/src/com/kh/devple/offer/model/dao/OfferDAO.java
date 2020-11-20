package com.kh.devple.offer.model.dao;

import static com.kh.devple.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.devple.offer.model.vo.Offer;
import com.kh.devple.offer.model.vo.VOffer;

/** 구인정보 관련 DAO
 * @author 유현재
 *
 */
public class OfferDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	private Properties prop = null;

	public OfferDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("offer-query.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 구인정보 작성 DAO
	 * @param conn
	 * @param offer
	 * @return result
	 * @throws Exception
	 */
	public int insertOffer(Connection conn, Offer offer) throws Exception {
		int result = 0;
		
		try {
			String query = prop.getProperty("insertOffer");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, offer.getLocation());
			pstmt.setInt(2, offer.getSal());
			pstmt.setInt(3, offer.getTerm());
			pstmt.setString(4, offer.getJob());
			pstmt.setInt(5, offer.getComNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 구인정보 수정 DAO
	 * @param conn
	 * @param offer
	 * @return result
	 * @throws Exception
	 */
	public int updateOffer(Connection conn, Offer offer) throws Exception {
		int result = 0;
		
		try {
			String query = prop.getProperty("updateOffer");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, offer.getLocation());
			pstmt.setInt(2, offer.getSal());
			pstmt.setInt(3, offer.getTerm());
			pstmt.setString(4, offer.getJob());
			pstmt.setInt(5, offer.getComNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 내 회사 구인정보 조회 DAO
	 * @param conn
	 * @param comNo
	 * @return list
	 * @throws Exception
	 */
	public List<VOffer> selectOfferByCompany(Connection conn, int comNo) throws Exception {
		List<VOffer> list = null;
		
		try {
			String query = prop.getProperty("selectOfferByCompany");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comNo);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			
			while(rset.next()) {
				list.add(new VOffer(rset.getInt("JO_NO"),
									rset.getString("COM_NM"),
									rset.getString("LOCATION"),
									rset.getInt("SAL"),
									rset.getInt("TERM"),
									rset.getString("JOB")));
			}
					
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	/** 구인정보 삭제 DAO
	 * @param conn
	 * @param joNo
	 * @return result
	 * @throws Exception
	 */
	public int updateSecessionOffer(Connection conn, int joNo, int memNo) throws Exception {
		int result = 0;
		
		try {
			String query = prop.getProperty("updateSecessionOffer");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,joNo);
			pstmt.setInt(2, memNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 구인 정보 조회 DAO
	 * @param conn
	 * @return list
	 */
	public List<VOffer> selectOffer(Connection conn) throws Exception {
		List<VOffer> list = null;
		
		try {
			String query = prop.getProperty("selectOffer");
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<>();
			
			while(rset.next()) {
				list.add(new VOffer(rset.getInt(1),
									rset.getString(2),
									rset.getString(3),
									rset.getInt(4),
									rset.getInt(5),
									rset.getString(6),
									rset.getInt(7)));
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}
}
