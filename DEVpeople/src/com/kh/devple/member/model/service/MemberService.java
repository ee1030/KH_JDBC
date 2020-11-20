package com.kh.devple.member.model.service;

import static com.kh.devple.common.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.devple.member.model.vo.Member;
import com.kh.devple.member.model.dao.MemberDAO;

// Service : 업무 수행에 필요한 일을 처리하는 역할 (비지니스 로직)

/** 회원 Service 
 * @author 박희도
 *
 */
public class MemberService {
	
	// MemberDAO 객체 생성
	private MemberDAO mDAO = new MemberDAO();
										// 예외처리 던짐
	/** 회원가입 Service
	 * @param newMember
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member newMember) throws Exception {
		
		// Connection 객체 얻기
		Connection conn = getConnection();
		
		// DAO 메소드 호출 -> Connection, parameter 전달 -> ResultSet 반환
		int result = mDAO.signUp(conn, newMember);
		
		// 트랜잭션 처리
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		// Connection 반환
		close(conn);
		
		// DB 삽입 결과 반환
		return result;
	}

	/** 로그인 Service
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(Member member) throws Exception{
		
		// Connection 객체 얻기
		Connection conn = getConnection();
		
		// Connection, parameter -> DAO 메소드에 전달
		Member loginMember = mDAO.login(conn, member);
		
		// Connection 반환
		close(conn);
		
		// DB 조회 결과 반환
		return loginMember;
	}


	/** 내 정보 수정 Service
	 * @param upMember
	 * @return result
	 * @throws Exception
	 */
	public int updateMyInfo(Member upMember) throws Exception {
	
		// Connection 객체 얻기
		Connection conn = getConnection();
		
		// Connection, parameter -> DAO 메소드에 전달
		int result = mDAO.updateMyInfo(conn, upMember);
		
		// 트랜잭션 처리
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		// Connection 반환
		close(conn);
		
		// DB 조회 결과 반환
		return result;
	}
		
	/** 비밀번호 변경 Service
	 * @param upPw
	 * @param newPw
	 * @return result
	 * @throws Exception
	 */
	public int updatePw(Member upMember, String newPw) throws Exception {
		
		// Connection 객체 얻기
		Connection conn = getConnection();
		
		// Connection, parameter -> DAO 메소드에 전달
		int result = mDAO.updatePw(conn, upMember, newPw);
		
		// 트랜잭션 처리
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		// DB 조회 결과 반환
		close(conn);
		
		return result;
	}
	
	/** 회원 탈퇴 Service
	 * @param upMember
	 * @return result
	 * @throws Exception
	 */
	public int updateSecessionMember(Member upMember) throws Exception {
		
		// Connection 객체 얻기
		Connection conn = getConnection();
		
		// Connection, parameter -> DAO 메소드에 전달
		int result = mDAO.updateSecessionMember(conn, upMember);
		
		// 트랜잭션 처리
		if(result > 0)	commit(conn);
		else 			rollback(conn);
		
		// DB 조회 결과 반환
		return result;
	}	
}