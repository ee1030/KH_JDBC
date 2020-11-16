package com.kh.scott.model.service;

import java.util.List;

import com.kh.scott.model.dao.EmpDAO;
import com.kh.scott.model.vo.Emp;

// Service (비즈니스 로직)
// 1. 전달받은 데이터 또는 DAO의 수행 결과 데이터를 필요한 형태로 가공처리 


public class EmpService {
	
	private EmpDAO empDAO = new EmpDAO();

	public List<Emp> selectAll() {
		// 1_4. 전체 사원 정보 조회는 별도의 데이터 가공 처리가 필요 없음.
		// --> 바로 EmpDAO.selectAll을 호출하여 결과를 반환 받기.
		List<Emp> empList = empDAO.selectAll();
		
		// 1_10. DAO 반환 데이터의 가공처리가 필요 없으므로
		// 그대로 다시 반환
		return empList;
	}
	// 2. 사번으로 사원 정보 조회 Service
	public Emp selectOne(int empNo) { 
		
		// 2_4. 전달받은 empNo의 별도 가공처리가 필요 없으므로
		// EmpDAO.selectOne(empNo)를 호출하여
		// 조회된 사원 한 명의 정보를 반환받음.
		Emp emp = empDAO.selectOne(empNo); 
		
		// 2_10. 전달받은 emp를 반환
		return emp;
	}
	

//	// 2. 사번으로 사원 정보 조회 Service
//	public Emp selectOne(int empNo) { 
//		
//		// 2_4. 전달받은 empNo의 별도 가공처리가 필요 없으므로
//		// EmpDAO.selectOne(empNo)를 호출하여
//		// 조회된 사원 한 명의 정보를 반환받음.
//		return empDAO.selectOne(empNo);
//	}
	
	// 3. 새로운 사원 정보 삽입용 Service
	public void insertEmp(Emp emp) {
		
		// 3_3. 전달받은 emp를 EmpDAO객체의 insertEmp(emp)로 전달하여
		// 결과를 반환 받음.
		empDAO.insertEmp(emp);
	}

//	public int insertEmp(Emp emp) {
//		int result = 0;
//		
//		result = empDAO.insertEmp(emp);
//		
//		return result;
//	}

	public int updateEmp(int currEmpNo, Emp emp) {
		int result = 0;
		
		result = empDAO.updateEmp(currEmpNo, emp);
		
		return result;
	}

	public int deleteEmpByEmpNo(int empNo) {
		int result = 0;
		
		result = empDAO.deleteEmpByEmpNo(empNo);
		
		return result;
	}
	
	public Emp selectOne2(int empNo, String eName) {
		// 대소문자 구분없이 모두 검색 가능하게 하는 방법
		// -> 입력 받은 값, SQL에서 조회 조건을
		//	  모두 대문자 또는 소문자로 통일
		return empDAO.selectOne2(empNo, eName.toUpperCase());
	}

}
