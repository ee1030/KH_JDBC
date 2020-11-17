package com.kh.jdbc.common;

public class JDBCTemplate {
	// 1. 반복되는 Connection 객체의 생성을 간소화
	// 2. 트랜잭션 처리, close() 처리의 간소화
	
	// ** 싱글톤(SingleTon) 패턴
	// 프로그램 구동 시 메모리 상에 딱 하나의 객체만 기록되게 하는 디자인 패턴
	// 대표적인 예시로 java.lang.Math가 있다.
	
	// 모든 필드, 메소드를 static으로 선언하여
	// 프로그램 구동 시 static 메모리 영역에
	// 클래스의 모든 내용을 로드하여 하나의 객체 모양을 띄게 함. 
}