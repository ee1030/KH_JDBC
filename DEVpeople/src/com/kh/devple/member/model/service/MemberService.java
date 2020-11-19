package com.kh.devple.member.model.service;

import static com.kh.devple.common.JDBCTemplate.close;
import static com.kh.devple.common.JDBCTemplate.commit;
import static com.kh.devple.common.JDBCTemplate.getConnection;
import static com.kh.devple.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.kh.devple.member.model.vo.Member;
import com.kh.devple.member.model.dao.MemberDAO;

public class MemberService {
	
	private MemberDAO mDAO = new MemberDAO();

	public int signUp(Member newMember) throws Exception {
		
		// JDBCTemplate에서 커넥션 얻어 오기
		Connection conn = getConnection(); // import 해서 클래스명 생략
		
		// 요청을 처리할 수 있는 알맞은 DAO 메소드를 호출하여
		// 커넥션과, 매개변수를 전달하고 결과를 반환 받음
		int result = mDAO.signUp(conn, newMember);
		
		// result 값에 따른 트랜잭션 처리
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		// conn 반환
		close(conn);
		
		// DB 삽입 결과 반환
		return result;
	}

	public Member login(Member member) throws Exception {
		// 커넥션 객체 얻어오기
		Connection conn = getConnection();
		
		// 얻어온 커넥션과 매개변수를 DAO 메소드로 전달
		Member loginMember = mDAO.login(conn, member);
		
		// select는 트랜잭션 처리가 필요 없으므로 바로 커넥션 반환
		close(conn);
		
		// DB 조회 결과인 loginMemeber 반환
		return loginMember;
	}
}