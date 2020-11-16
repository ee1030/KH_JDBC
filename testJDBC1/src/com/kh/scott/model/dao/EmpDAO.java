package com.kh.scott.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.scott.model.vo.Emp;

public class EmpDAO {
	
	// DB에 있는 EMP 테이블의 모든 사원 정보를 조회 DAO
	public List<Emp> selectAll() {
		
		// JDBC 객체 선언(java.sql 패키지에 존재하는 객체)
		Connection conn = null;		
		// DB와의 연결 정보를 담은 객체
		// -> 프로그램과 DB 사이를 연결해주는 일종의 길
		
		Statement stmt = null;
		// Connection 객체를 통해 DB에 SQL문을 전달하고 실행하여
		// 결과를 반환받는 역할을 하는 객체
		
		ResultSet rset = null;
		// DB에서 SELECT 질의 성공 시 반환되는 객체
		// - SELECT문의 결과로 생성된 테이블을 담고 있으며
		//   '커서(CURSOR)'를 이용해 한 행씩을 참조 할 수 있음.
		
		List<Emp> empList = null;
		
		try {
			// 1_5. JDBC 드라이버 클래스를 메모리에 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 프로그램과 DB사이의 연결 역할을 해주는 JDBC 드라이버 로드
			// 오라클 DB와 연결하기 위한 JDBC 드라이버 : "oracle.jdbc.driver.OracleDriver"
			// 해당 드라이버를 호출함으로써 메모리에 로드됨.
			
			// 1_6. DB와의 연결 작업 준비
			/* 연결 정보를 담을 Connection을 얻어옴.
			 * -> Connection을 얻어오기 위해서는 DriverManager가 필요
			 */
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "scott", "tiger");
			/* jdbc:oracle:thin -> JDBC 드라이버의 타입이 thin 타입임을 의미함
			 * 
			 * @127.0.0.1 -> 자신의 컴퓨터 로컬 ip (== @localhost 대체가능)
			 *
			 * :1521 -> 오라클 listener의 포트번호
			 * 			(포트 : 응용프로그램이 외부와 통신하기 위한 통로)
			 * 
			 * :xe -> 오라클 DB 이름
			 * 
			 * "scott" : 접속할 사용자 이름(계정)
			 * "tiger" : 해당 계정의 비밀번호
			 * 
			 */
			
			// DB와의 연결과 관련된 요소들은 모두 SQLException을 발생시킬 가능성이 있다.
			
			// DB 접속 성공 시 conn을 출력하면 연결 객체의 주소가 출력됨.
			// 실패 시 null이 출력
			// System.out.println(conn);
			// oracle.jdbc.driver.T4CConnection@6438a396
			
			// 1_7. DB 접속 성공 시
			// 이를 전달하여 결과를 얻어올 객체 Statement를 생성
			String query = "SELECT * FROM EMP";
			// ***** 주의사항 *****
			// JAVA 단에서 SQL 구문 작성 시 세미콜론 붙이면 큰일남
			
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			// *** Statement.executeQuery(query)
			// -> DB로 SELECT문을 전달하고 결과로 ResultSet을 반환받음.
			
			// 1_8. ResultSet rset에 있는 SELECT 처리 결과를 
			// JAVA단에서 쉽게 사용할 수 있도록 
			// List객체에 저장시키기.
			
			// DB와의 통신 성공 시 List 객체를 생성
			// -> 오류가 났을 경우에 메모리 손실을 방지하기 위해
			empList = new ArrayList<>();
			
			while(rset.next()) {
				// ResultSet.next() : 반환받은 조회 결과를
				// 커서를 이용해 순차적으로 한 행씩 접근
				// 접근한 행이 존재하면 true, 없으면 false
				
				// ResultSet에서 현재 접근한 행의 데이터를 꺼내는 방법
				// get[타입]("컬럼명")
				int empNo = rset.getInt("EMPNO"); // 7369
				String eName = rset.getString("ENAME"); // SMITH
				String job = rset.getString("JOB");
				int mgr = rset.getInt("MGR");
				Date hireDate = rset.getDate("HIREDATE");
				int sal = rset.getInt("SAL");
				int comm = rset.getInt("COMM");
				int deptNo = rset.getInt("DEPTNO");
				
				// 얻어온 행의 정보를 통해 Emp 객체를 만들어
				// empList에 추가하기
				
				empList.add(new Emp(empNo, eName, job, mgr, hireDate, sal, comm, deptNo));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 1_9. JDBC 관련 객체 연결 끊기(자원 반환)
			// 연결을 끊을 경우 생성 역순으로 끊어 나가기
			try {
				if(rset != null) rset.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return empList;
	}
	
	// 2. 사번으로 사원 정보 조회 DAO
	public Emp selectOne(int empNO) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Emp emp = null;
		// DB에서 조회한 결과를 저장할 Emp 참조변수
		
		try {
			// 2_5. JDBC 드라이버 로드 및 커넥션 작업
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			
			// 2_6. 사원 한 명의 정보를 조회하기 위한 SQL 구문을 준비하고
			// Statement 객체를 생성하여
			// DB로 전달 및 수행 후 결과를 반환 받아 오기
			String query = "SELECT * FROM EMP WHERE EMPNO = "+ empNO;
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			// 2_7. 조회 결과가 있을 경우
			// Emp 참조변수에 조회 결과를 저장
			// 조회 결과가 0또는 1행이 반환 되므로 rset.next() 한 번 호출했을 때
			// true가 나오면 조회 결과가 있는것,
			// false가 나오면 조회 결과가 없는것
			if(rset.next()) {
				
				// 조회 결과에서 각 컬럼의 값을 얻어와 변수에 저장
				int empNo = rset.getInt("EMPNO");
				String eName = rset.getString("ENAME");
				String job = rset.getString("JOB");
				int mgr = rset.getInt("MGR");
				Date hireDate = rset.getDate("HIREDATE");
				int sal = rset.getInt("SAL");
				int comm = rset.getInt("COMM");
				int deptNo = rset.getInt("DEPTNO");
				
				// 조회 결과를 이용하여 Emp 객체를 생성
				emp = new Emp(empNo, eName, job, mgr, hireDate, sal, comm, deptNo);
			}
			
			// 조회 성공시 emp는 참조하는 객체 있음
			// 조회 실패시 emp는 참조하는 객체 없음(null)

			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 2_8. 사용한 JDBC 객체들을 반환
			try {
				if(rset != null) rset.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 2_9. 조회 결과가 저장된 emp 반환
		return emp; // 메소드 반환형을 Emp로 수정
	}
	
//	public Emp selectOne(int empNO) {
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rset = null;
//		Emp tmpEmp = null;
//		
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
//			
//			String query = "SELECT * FROM EMP WHERE EMPNO = "+ empNO;
//			
//			stmt = conn.createStatement();
//			rset = stmt.executeQuery(query);
//			
//			rset.next();
//			int empNo = rset.getInt("EMPNO"); // 7369
//			String eName = rset.getString("ENAME"); // SMITH
//			String job = rset.getString("JOB");
//			int mgr = rset.getInt("MGR");
//			Date hireDate = rset.getDate("HIREDATE");
//			int sal = rset.getInt("SAL");
//			int comm = rset.getInt("COMM");
//			int deptNo = rset.getInt("DEPTNO");
//			
//			tmpEmp = new Emp(empNo, eName, job, mgr, hireDate, sal, comm, deptNo);
//			
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(rset != null) rset.close();
//				if(stmt != null) stmt.close();
//				if(conn != null) conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return tmpEmp;
//	}

	public int insertEmp(Emp emp) {
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			
			int empNo = emp.getEmpNo();
			String eName = emp.geteName();
			String job = emp.getJob();
			int mgr = emp.getMgr();
			Date hireDate = emp.getHireDate();
			int sal = emp.getSal();
			int comm = emp.getComm();
			int deptNo = emp.getDeptNo();
			
			String query = "INSERT INTO EMP VALUES(" + empNo + ", " + "'" + eName + "'" + ", " + "'" + job + "'" + ", " + mgr + ", " + "'" + hireDate + "'" + ", " + sal + ", " + comm + ", " + deptNo + ")";   
			
			stmt = conn.createStatement();
			stmt.executeQuery(query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
			result = 1;
		} finally {
			try {
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public int updateEmp(int currEmpNo, Emp emp) {
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			
			int empNo = emp.getEmpNo();
			String eName = emp.geteName();
			String job = emp.getJob();
			int mgr = emp.getMgr();
			Date hireDate = emp.getHireDate();
			int sal = emp.getSal();
			int comm = emp.getComm();
			int deptNo = emp.getDeptNo();
			
			String query = "UPDATE EMP SET EMPNO = " + empNo + ", ENAME = '" + eName + "', JOB = '" + job + "', MGR = " 
						+ mgr + ", HIREDATE = '" + hireDate + "', SAL = " + sal + ", COMM = " + comm + ", DEPTNO = " + deptNo
						+ "WHERE EMPNO = " + currEmpNo;
			
			stmt = conn.createStatement();
			stmt.executeQuery(query);
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
			result = 1;
		} finally {
			try {
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public int deleteEmpByEmpNo(int empNo) {
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			String query = "DELETE FROM EMP WHERE EMPNO = " + empNo;
			
			stmt = conn.createStatement();
			stmt.executeQuery(query);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
			result = 1;
		} finally {
			try {
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public Emp selectOne2(int empNO, String empName) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Emp emp = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

			String query = "SELECT * FROM EMP WHERE EMPNO = "+ empNO + " AND ENAME = UPPER(\'" + empName + "\')";
			
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if(rset.next()) {
				
				int empNo = rset.getInt("EMPNO");
				String eName = rset.getString("ENAME");
				String job = rset.getString("JOB");
				int mgr = rset.getInt("MGR");
				Date hireDate = rset.getDate("HIREDATE");
				int sal = rset.getInt("SAL");
				int comm = rset.getInt("COMM");
				int deptNo = rset.getInt("DEPTNO");
				
				emp = new Emp(empNo, eName, job, mgr, hireDate, sal, comm, deptNo);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rset != null) rset.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return emp;
	}

}
