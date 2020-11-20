package com.kh.devple.member.model.dao;

import static com.kh.devple.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.kh.devple.member.model.vo.Member;

// DAO(Data Access Object)
// : 데이터 저장소(파일 또는 DB)에 실제 데이터 저장, 조회, 삭제 역할

/** 회원 DAO
 * @author 박희도
 *
 */
public class MemberDAO {

	// JDBC 객체 참조 변수를 멤버 변수로 선언  (재활용)
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	// xml 파일의 properties 변수 선언
	private Properties prop = null;
	
	public MemberDAO() {
		// xml 파일을 읽어오게 함
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("member-query.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 회원 가입 DAO
	 * @param conn
	 * @param newMember
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member newMember) throws Exception {
		
		// DML 결과 저장 변수
		int result = 0;
		
		try {
			String query = prop.getProperty("signUp");
		
			// SLQ 구문을 DB 전달할 준비
			pstmt = conn.prepareStatement(query);
			
			// SQL 구문의 위치홀더에 알맞는 값을 배치
			pstmt.setString(1, newMember.getMemId());
			pstmt.setString(2, newMember.getMemPw());
			pstmt.setString(3, newMember.getMemNm());
			pstmt.setString(4, newMember.getPhone());
			pstmt.setString(5, newMember.getEmail());
			pstmt.setInt(6, newMember.getCareer());
			pstmt.setString(7, newMember.getSpec());
			pstmt.setString(8, newMember.getDevYn() + "");
					
			// SQL 수행 결과 반환 -> result에 저장
			result = pstmt.executeUpdate();
			
		} finally {
			
			// DB 자원 반한
			close(pstmt);
		}
		
		// DB 조회 결과 반환
		return result;
	}

	/** 로그인 DAO
	 * @param conn
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(Connection conn, Member member) throws Exception{
		
		// 로그인된 회원 정보 저장 변수
		Member loginMember = null;
	
		try {
			
			// member-query.xml 파일에서 "login"인 태그의 값을 얻어옴
			String query = prop.getProperty("login");
			
			// SLQ 구문을 DB 전달할 준비
			pstmt = conn.prepareStatement(query);
			
			// SQL 구문의 위치홀더에 알맞는 값을 배치
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPw());
		
			// SQL 수행 후 결과를 ResultSet으로 반환 받음
			rset = pstmt.executeQuery();
			
			// 조회 결과 확인
			if(rset.next()) {
				// 조회 결과가 있을 경우 Member 객체를 만들어 로그인 정보를 저장
				loginMember = new Member(rset.getInt("MEM_NO"), rset.getString("MEM_ID"), 
										 rset.getString("MEM_NM"), rset.getString("PHONE"), 
										 rset.getString("EMAIL"), rset.getInt("CAREER"), 
										 rset.getString("SPEC"), rset.getString("DEV_YN").charAt(0));
			}
			
		} finally {
			
			// DB 자원 반환
			close(rset);
			close(pstmt);
			
		}
		
		// 조회 결과가 담긴 loginMember 반환
		return loginMember;
	}

	/** 내 정보 수정 DAO
	 * @param conn
	 * @param upMember
	 * @return result
	 */
	public int updateMyInfo(Connection conn, Member upMember) throws Exception {
		
		// 변경된 회원 정보 저장 변수
		int result = 0;
		try {
			String query = prop.getProperty("updateMyInfo");
		
		// SLQ 구문을 DB 전달할 준비	
		pstmt = conn.prepareStatement(query);
		
		// SQL 구문의 위치홀더에 알맞는 값을 배치
		pstmt.setString(1, upMember.getMemNm());
		pstmt.setString(2, upMember.getPhone());
		pstmt.setString(3, upMember.getEmail());
		pstmt.setInt(4, upMember.getCareer());
		pstmt.setString(5, upMember.getSpec());
		pstmt.setInt(6, upMember.getMemNo());
		
		// SQL 수행 결과 반환 -> result에 저장
		result = pstmt.executeUpdate();
		
		} finally {
			
			// Connection 반환
			close(pstmt);
		}
		
		// DB 조회 결과 반환
		return result;
	}

	/** 비밀번호 변경 DAO
	 * @param conn
	 * @param upPw
	 * @param newPw
	 * @return result
	 * @throws Exception
	 */
	public int updatePw(Connection conn, Member upMember, String newPw) throws Exception {
		
		// 변경된 회원 정보 저장 변수
		int result = 0;
		try {
			String query = prop.getProperty("updatePw");
			
			// SLQ 구문을 DB 전달할 준비
			pstmt = conn.prepareStatement(query);
			
			// SQL 구문의 위치홀더에 알맞는 값을 배치
			pstmt.setString(1,  newPw);
			pstmt.setInt(2,  upMember.getMemNo());
			pstmt.setString(3, upMember.getMemPw());
			
			// SQL 수행 결과 반환 -> result에 저장
			result = pstmt.executeUpdate();
		
		} finally {
			
			// Connection 반환
			close(pstmt);
		}
		
		// DB 조회 결과 반환
		return result;
	}
	
	/** 회원 탈퇴 여부 DAO
	 * @param conn
	 * @param upMember
	 * @return result
	 * @throws Exception
	 */
	public int updateSecessionMember(Connection conn, Member upMember) throws Exception {
		
		// 변경된 회원 정보 저장 변수
		int result = 0;
		try {
			String query = prop.getProperty("updateSecessionMember");
			
			// SLQ 구문을 DB 전달할 준비
			pstmt = conn.prepareStatement(query);
			
			// SQL 구문의 위치홀더에 알맞는 값을 배치
			pstmt.setInt(1, upMember.getMemNo());
			pstmt.setString(2, upMember.getMemPw());
			
			// SQL 수행 결과 반환 -> result에 저장
			result = pstmt.executeUpdate();
			
		} finally {
			
			// Connection 반환
			close(pstmt);
		}
		
		// DB 조회 결과 반환
		return result;
	}
}
