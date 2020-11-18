package com.kh.jdbc.member.model.dao;

import static com.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.jdbc.member.model.vo.Member;

public class MemberDAO {

	// DAO에서 자주 사용하는 JDBC 객체 참조 변수를 멤버 변수로 선언 --> 재활용
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	// 외부 XML 파일에 작성된 SQL 구문을 읽어들일 Properties 변수 선언
	private Properties prop = null;
	
	public MemberDAO() {
		// 기본 생성자를 통한 객체 생성시 xml 파일을 읽어오게 함
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("member-query.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 회원가입용 DAO
	 * @param conn
	 * @param newMember
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member newMember) throws Exception {
		
		/* 이전 DAO에서 하던 역할
		 * 1) JDBC 드라이버 등록
		 * 2) DB 연결 커넥션 생성
		 * 3) SQL 수행
		 * 4) 트랜잭션 처리
		 * 5) JDBC 객체 반환
		 * 6) SQL 수행 결과 반환
		 * 
		 * --> 이제는 3, 5, 6번만 수행
		 */
		
		int result = 0; // DML 수행 결과 저장용 변수
		
		
		try {
			String query = prop.getProperty("signUp");
			
			// SQL 구문을 DB에 전달할 준비
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newMember.getMemId());
			pstmt.setString(2, newMember.getMemPw());
			pstmt.setString(3, newMember.getMemNm());
			pstmt.setString(4, newMember.getPhone());
			pstmt.setString(5, newMember.getGender()+"");
			// char데이터에 빈 문자열을 더해 String 형태로 형변환
			
			// SQL 수행 후 결과를 반환받아 result에 저장
			result = pstmt.executeUpdate();
		} finally {
			// catch 없어도 finally 작성 가능
			close(pstmt);
		}
		
		return result;
	}

	public Member login(Connection conn, Member member) throws Exception {
		Member loginMember = null; // 로그인된 회원 정보를 저장할 임시변수
		
		try {
			// member-query.xml 파일에서 키값이 "login"인 태그의 값을 얻어옴.
			String query = prop.getProperty("login");

			// DB 전달을 위한 pstmt 생성
			pstmt = conn.prepareStatement(query);
			
			// SQL 구문의 위치홀더에 알맞는 값을 배치
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPw());
			
			// SQL 수행 후 결과를 ResultSet으로 반환
			rset = pstmt.executeQuery();
			
			// 조회 결과가 있는지 확인
			if(rset.next()) {
				// 조회 결과가 있을 경우 Member 객체를 만들어 로그인 정보를 저장
				loginMember = new Member(rset.getInt("MEM_NO"),
										 rset.getString("MEM_ID"),
										 rset.getString("MEM_NM"),
										 rset.getString("PHONE"),
										 rset.getString("GENDER").charAt(0),
										 rset.getDate("HIRE_DT"));
			}
		} finally {
			// DB 자원 반환
			close(rset);
			close(pstmt);
		}
		
		// 조회 결과가 담긴 loginMember 반환
		return loginMember;
	}

	/** 검색어 포함 이름 검색 DAO
	 * @param conn
	 * @param name
	 * @return list
	 * @throws Exception
	 */
	public List<Member> selectMemberName(Connection conn, String name) throws Exception {
		// 1. 반환할 결과를 저장할 변수 List<Member> 선언
		List<Member> list = null;
		try {
			// 2. SQL 작성
			String query = prop.getProperty("selectMemberName");

			// 3. PrearedStatement 객체 생성
			pstmt = conn.prepareStatement(query);
			
			// 4. 위치 홀더에 알맞은 값 배치
			pstmt.setString(1, name);
			// 5. SQL 수행 후 결과를 반환받음
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			// 6. 수행 결과를 모두 List에 담기
			while(rset.next()) {
				list.add(new Member(rset.getString("MEM_ID"),
							   	    rset.getString("MEM_NM"),
								    rset.getString("PHONE"),
								    rset.getString("GENDER").charAt(0),
								    rset.getDate("HIRE_DT")));
			}
			
		} finally {
			// 7. 사용한 JDBC 객체 반환
			close(rset);
			close(pstmt);
		}
		// 8. 조회 결과가 담긴 List 반환
		return list;
	}

	public List<Member> selectGender(Connection conn, char gender) throws Exception {
		List<Member> list = null;
		
		try {
			String query = prop.getProperty("selectGender");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, gender+"");
			
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			
			while(rset.next()) {
				list.add(new Member(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4).charAt(0)));
			}
			
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int updateMyInfo(Connection conn, Member upMember) throws Exception {
		int result = 0;
		
		try {
			String query = prop.getProperty("updateMyInfo");
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, upMember.getMemNm());
			pstmt.setString(2, upMember.getPhone());
			pstmt.setString(3, upMember.getGender()+"");
			pstmt.setInt(4, upMember.getMemNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 비밀번호 변경 DAO
	 * @param conn
	 * @param upMember
	 * @param newPw
	 * @return result
	 * @throws Exception
	 */
	public int updatePw(Connection conn, Member upMember, String newPw) throws Exception {
		int result = 0;
		
		try {
			String query = prop.getProperty("updatePw");
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, newPw);
			pstmt.setInt(2, upMember.getMemNo());
			pstmt.setString(3, upMember.getMemPw());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateSecessionMember(Connection conn, Member upMember) throws Exception {
		int result = 0;
		
		try {
			String query = prop.getProperty("updateSecessionMember");
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, upMember.getMemNo());
			pstmt.setString(2, upMember.getMemPw());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
