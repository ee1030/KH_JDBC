package com.kh.devple.offer.model.service;

import static com.kh.devple.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.devple.offer.model.dao.OfferDAO;
import com.kh.devple.offer.model.vo.Offer;
import com.kh.devple.offer.model.vo.VOffer;

/** Offer 메뉴 Service
 * @author 유현재
 *
 */
public class OfferService {
	private OfferDAO oDAO = new OfferDAO();

	/** 구인정보 작성 Service
	 * @param offer
	 * @return result
	 * @throws Exception
	 */
	public int insertOffer(Offer offer) throws Exception {
		Connection conn = getConnection();
		
		int result = oDAO.insertOffer(conn, offer);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 구인 정보 수정 Service
	 * @param offer
	 * @return result
	 * @throws Exception
	 */
	public int updateOffer(Offer offer) throws Exception {
		Connection conn = getConnection();
		
		int result = oDAO.updateOffer(conn, offer);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 내 회사 구인 정보 조회 Service
	 * @param comNo
	 * @return list
	 * @throws Exception
	 */
	public List<VOffer> selectOfferByCompany(int comNo) throws Exception {
		Connection conn = getConnection();
		
		List<VOffer> list = oDAO.selectOfferByCompany(conn, comNo);
		
		close(conn);
		
		return list;
	}

	/** 구인정보 삭제 Serivce
	 * @param joNo
	 * @return result
	 * @throws Exception
	 */
	public int updateSecessionOffer(int joNo) throws Exception {
		Connection conn = getConnection();
		
		int result = oDAO.updateSecessionOffer(conn, joNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 구인정보 조회Service
	 * @return list
	 * @throws Exception
	 */
	public List<VOffer> selectOffer() throws Exception {
		Connection conn = getConnection();
		
		List<VOffer> list = oDAO.selectOffer(conn);
		
		close(conn);
		
		return list;
	}
	
}
