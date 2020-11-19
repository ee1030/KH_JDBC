package com.kh.devple.offer.model.service;

import static com.kh.devple.common.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.devple.offer.model.dao.OfferDAO;
import com.kh.devple.offer.model.vo.Offer;

public class OfferService {
	private OfferDAO oDAO = new OfferDAO();

	public int insertOffer(Offer offer) throws Exception {
		Connection conn = getConnection();
		
		int result = oDAO.insertOffer(conn, offer);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
}
