package com.kh.jdbc.member.model.service;

import com.kh.jdbc.member.model.dao.MemberDAO;
import com.kh.jdbc.member.model.vo.Member;

// Service
// 1. 요청, 응답 데이터의 가공처리
// 2. 여러 DAO 메소드를 호출하여 DML 진행 후
//    수행된 DML을 하나의 트랜잭션으로 묶어 처리하는 역할
//    ***** 트랜잭션 처리를 위해 Connection 객체의 생성과 반환을
//          Service에서 진행해야 함!

/** JDBCProject 회원 관련 서비스
 * @author 유현재
 */
public class MemberService {

	private MemberDAO mDAO = new MemberDAO();
	
	public int signUp(Member newMember) {
		
		// Connection 생성
		
		
		return 0;
	}

}
