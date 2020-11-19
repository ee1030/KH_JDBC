package com.kh.devple.company.model.service;

import static com.kh.devple.common.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.devple.company.model.dao.CompanyDAO;
import com.kh.devple.company.model.vo.Company;

/** 회사 정보 관련 Service
 * @author 유현재
 *
 */
public class CompanyService {
	
	private CompanyDAO cDAO = new CompanyDAO();

	/** 회사 정보 등록 Service
	 * @param company
	 * @return result
	 */
	public int insertCompany(Company company) throws Exception {
		Connection conn = getConnection();
		
		int result = cDAO.insertCompany(conn, company);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 회사 정보 수정 Service
	 * @param company
	 * @return result
	 * @throws Exception
	 */
	public int updateCompany(Company company) throws Exception {
		Connection conn = getConnection();
		
		int result = cDAO.updateCompany(conn, company);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 회사 정보 삭제 Service
	 * @param upCompany
	 * @return result
	 */
	public int updateSecessionCompany(Company upCompany) throws Exception {
		Connection conn = getConnection();
		
		int result = cDAO.updateSecessionCompany(conn, upCompany);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 회사 번호 조회 Service
	 * @param memNo
	 * @return result
	 * @throws Exception
	 */
	public int findComNo(int memNo) throws Exception {
		Connection conn = getConnection();
		
		int result = cDAO.findComNo(conn, memNo);
		
		close(conn);

		return result;
	}

}
