package com.kh.devple.offer.model.dao;

import static com.kh.devple.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.kh.devple.offer.model.vo.Offer;

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
	
	

}
