package com.kh.admin.member.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.kh.admin.member.model.service.MemberService;
import com.kh.admin.member.model.vo.Member;

public class MemberView {
	private Scanner sc = new Scanner(System.in);
	private MemberService mService = new MemberService();
	
	/**
	 * 회원 관련 메뉴 View
	 */
	public void displayMem() {
		int sel = 0;
		
		do {
			try {
				System.out.println("[회원 관련 메뉴]");
				System.out.println("=================");
				System.out.println("1. 모든 회원 조회");
				System.out.println("2. 탈퇴 회원 조회");
				System.out.println("3. 탈퇴 회원 복구");
				System.out.println("0. 메인 메뉴로");
				System.out.println("=================");
				System.out.print("메뉴 선택 >> ");
				sel = sc.nextInt();
				sc.nextLine();
				
				switch(sel) {
				case 1: selectAllMember(); break;
				case 2: selectScsnMember(); break;
				case 3: restoreMember(); break;
				case 0: System.out.println("프로그램을 종료합니다."); break;
				default : System.out.println("잘못 입력 하셨습니다.");
				}
				
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해 주세요.");
				sel = -1;
				sc.nextLine();
				e.printStackTrace();
			}
			
		} while(sel != 0);
	}

	/**
	 * 모든 회원 조회 view
	 */
	private void selectAllMember() {
		System.out.println("[모든 회원 조회]");
		
		try {
			List<Member> list = mService.selectAllMember();
			
			if(list.isEmpty()) {
				System.out.println("조회 결과가 없습니다.");
			} else {
				for(Member m : list) {
					String scsnFl = "";
					
					if(m.getScsnFl() == 'Y') scsnFl = "탈퇴상태";
					else scsnFl = "가입상태";
					
					System.out.printf("%d / %s / %s / %s / %s / %c / %s / %s\n",
							m.getMemNo(), m.getMemId(), m.getMemPw(),
							m.getMemNm(), m.getPhone(),
							m.getGender(), m.getHireDt(), scsnFl);
				}
			}
			
		} catch (Exception e) {
			System.out.println("회원 조회 과정 중 오류 발생");
			e.printStackTrace();
		}
	}

	/**
	 * 탈퇴 회원 조회 View
	 */
	private void selectScsnMember() {
		System.out.println("[탈퇴한 회원 조회]");
		try {
			List<Member> list = mService.selectScsnMember();
			
			if(list.isEmpty()) {
				System.out.println("조회 결과가 없습니다.");
			} else {
				for(Member m : list) {
					System.out.printf("%d / %s / %s / %s / %s / %c / %s / %c\n",
							m.getMemNo(), m.getMemId(), m.getMemPw(),
							m.getMemNm(), m.getPhone(),
							m.getGender(), m.getHireDt(), m.getScsnFl());
				}
			}
			
		} catch (Exception e) {
			System.out.println("탈퇴 회원 조회 과정 중 오류 발생");
			e.printStackTrace();
		}
	}
	

	/**
	 * 탈퇴 회원 복구 View
	 */
	private void restoreMember() {
		System.out.println("[탈퇴 회원 복구]");
		System.out.print("복구할 회원의 회원번호 입력 : ");
		int memNo = sc.nextInt();
		sc.nextLine();
		
		try {
			int result = mService.restoreMember(memNo);
			
			if(result > 0) {
				System.out.println("회원 복구가 완료되었습니다.");
			} else {
				System.out.println("존재하지 않는 회원번호거나 삭제 되지 않은 회원입니다.");
			}
			
		} catch (Exception e) {
			System.out.println("회원 복구 도중 오류 발생");
			e.printStackTrace();
		}
	}
}
