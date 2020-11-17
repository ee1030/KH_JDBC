package com.kh.jdbc.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.kh.jdbc.member.model.service.MemberService;
import com.kh.jdbc.member.model.vo.Member;

/** JDBCProject View
 * @author 유현재
 *
 */
public class JDBCView {

	private Scanner sc = new Scanner(System.in);
	private MemberService mService = new MemberService();
	
	private Member loginMember = null; // 로그인된 회원의 정보를 저장
	
	// alt + shift + j
	/**
	 * 메인 메뉴 View
	 */
	public void displayMain() {
		int sel = 0;
		
		do {		
			try {
				if(loginMember == null) { // 로그인이 되어있지 않은 경우
					System.out.println("★★★★★ JDBC PROJECT ★★★★★");
					System.out.println("========================");
					System.out.println("1. 로그인");
					System.out.println("2. 회원가입");
					System.out.println("0. 프로그램 종료");
					System.out.println("========================");
					
					System.out.print("메뉴 선택 >> ");
					sel = sc.nextInt();
					sc.nextLine();
					
					System.out.println();
					
					switch(sel) {
					case 1 : break;
					case 2 : signUp(); break;
					case 0 : System.out.println("종료한다 애송이."); break;
					default : System.out.println("메뉴에 있는것만 고르시오");break;
					}
					
				} else {
					
				}
				
				
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해 주세요.");
				sel = -1;
				sc.nextLine();
			}
			
		} while(sel != 0);
	}

	/**
	 * 회원 가입용 View
	 */
	private void signUp() {
		System.out.println("★=====★ 회원가입 ★=====★");
		System.out.print("아이디 : ");
		String memId = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String memPw = sc.nextLine();
		
		System.out.print("이름 : ");
		String memNm = sc.nextLine();
		
		System.out.print("전화번호 : ");
		String phone = sc.nextLine();
		
		System.out.print("성별 : ");
		char gender = sc.nextLine().toUpperCase().charAt(0); // 대문자로 입력 받음
		
		// 입력받은 값을 하나의 Member 객체에 저장
		Member newMember = new Member(memId, memPw, memNm, phone, gender);
		
		// 입력받은 회원 정보를 DB에 삽입하기 위한 service 호출
		int result = mService.signUp(newMember);
	}

}
