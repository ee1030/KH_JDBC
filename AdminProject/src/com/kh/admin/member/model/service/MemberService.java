package com.kh.admin.member.model.service;

import static com.kh.admin.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.admin.member.model.dao.MemberDAO;
import com.kh.admin.member.model.vo.Member;

public class MemberService {
	
	private MemberDAO mDAO = new MemberDAO();

	/** 모든 회원 조회 Service
	 * @return list
	 * @throws Exception
	 */
	public List<Member> selectAllMember() throws Exception {
		Connection conn = getConnection();
		
		List<Member> list = mDAO.selectAllMember(conn);
		
		close(conn);
		
		return list;
	}

	/** 탈퇴 회원 조회  Service
	 * @return list
	 * @throws Exception
	 */
	public List<Member> selectScsnMember() throws Exception {
		Connection conn = getConnection();
		
		List<Member> list = mDAO.selectScsnMember(conn);
		
		close(conn);
		
		return list;
	}

	/** 회원 복구 Service
	 * @param memNo
	 * @return result
	 */
	public int restoreMember(int memNo) throws Exception{
		Connection conn = getConnection();
		
		int result = mDAO.restoreMember(conn, memNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

}
