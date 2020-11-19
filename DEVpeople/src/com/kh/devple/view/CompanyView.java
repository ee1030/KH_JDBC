package com.kh.devple.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.kh.devple.company.model.service.CompanyService;
import com.kh.devple.company.model.vo.Company;

public class CompanyView {
	private Scanner sc = new Scanner(System.in);
	private CompanyService cService = new CompanyService();
	
	
	public void displayCompany() {
		int sel = 0;
		
		do {
			try {
				// 회원 탈퇴로 인해 로그아웃 될 경우 메인 메뉴로 리턴
				System.out.println("=========================================");
				System.out.println("             ★~ 회사 관련 메뉴 ~★              ");
				System.out.println("1. 회사 등록");
				System.out.println("2. 내 회사 정보");
				System.out.println("3. 회사 정보 수정");
				System.out.println("4. 회사 정보 삭제");		
				System.out.println("0. 메인메뉴로 돌아가기");
				System.out.println("=========================================");
				System.out.print("메뉴 입력 >> ");
				sel = sc.nextInt();
				sc.nextLine();
				
				switch(sel) {
				case 1: insertCompany(); break;
				case 2: selectMyCompany(); break;
				case 3: updateCompany(); break;
				case 4: updateSecessionCompany(); break;
				case 0 : System.out.println("메인메뉴로 돌아가겠다능"); break;
				default : System.out.println("잘못 입력하셨다능");
				}
				
			}catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sel = -1;
				sc.nextLine();
			}
			
		} while(sel != 0);
		
	}
	
	/**
	 * 내 회사 정보 조회 View
	 */
	private void selectMyCompany() {
		System.out.println("[우리 회사 정보]");
		
		try {
			Company company = cService.selectMyCompany(DevpleView.loginMember.getMemNo());
			
			if(company == null) {
				System.out.println("등록된 회사 정보가 존재하지 않습니다.");
			} else {
				System.out.println("==================================");
				System.out.println("회사명 : " + company.getComNm());
				System.out.println("전화번호 : " + company.getPhone());
				System.out.println("이메일 : " + company.getEmail());
				System.out.println("회사 소개 : ");
				System.out.println(company.getComment());
				System.out.println("==================================");
			}
			
		} catch (Exception e) {
			System.out.println("회사 정보 조회 중 오류가 발생했습니다.");
			e.printStackTrace();
		}
		
	}

	/**
	 * 회사 정보 등록 View
	 */
	private void insertCompany() {
		System.out.println("[회사 등록]");
		System.out.print("회사명 : ");
		String comNm = sc.nextLine();
		
		System.out.print("회사 전화번호 : ");
		String phone = sc.nextLine();
		
		System.out.print("회사 이메일 : ");
		String email = sc.nextLine();
		
		StringBuffer sb = new StringBuffer();
		String str = null;
		
		System.out.print("----- 회사 소개글 입력(exit입력 시 입력 종료) -----");
		while(true) {
			str = sc.nextLine();
			
			if(str.equals("exit")) break;
			
			sb.append(str + "\n");
		}
		
		try {
			Company company = new Company(comNm, phone, email, sb.toString(), DevpleView.loginMember.getMemNo());	
			
			int result = cService.insertCompany(company);
			
			if(result > 0) {
				System.out.println("회사 정보 등록에 성공했습니다.");
			} else {
				System.out.println("회사 정보 등록에 실패했습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("회사 정보 입력중 오류가 발생 했습니다.");
			e.printStackTrace();
		}
	}
	

	/**
	 * 회사 정보 수정 View
	 */
	private void updateCompany() {
		System.out.println("[회사 정보 수정]");
		System.out.print("회사명 수정 : ");
		String comNm = sc.nextLine();
		
		System.out.print("회사 전화번호 수정 : ");
		String phone = sc.nextLine();
		
		System.out.print("회사 이메일 수정 : ");
		String email = sc.nextLine();
		
		StringBuffer sb = new StringBuffer();
		String str = null;
		
		System.out.print("----- 회사 소개글 수정(exit입력 시 수정 종료) -----");
		while(true) {
			str = sc.nextLine();
			
			if(str.equals("exit")) break;
			
			sb.append(str + "\n");
		}
		
		try {
			Company company = new Company(comNm, phone, email, sb.toString(), DevpleView.loginMember.getMemNo());	
			
			int result = cService.updateCompany(company);
			
			if(result > 0) {
				System.out.println("회사 정보 수정에 성공했습니다.");
			} else {
				System.out.println("회사 정보 수정에 실패했습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("회사 정보 수정 중 오류가 발생 했습니다.");
			e.printStackTrace();
		}
		
	}

	/**
	 * 회사 정보 삭제 View
	 */
	private void updateSecessionCompany() {
		System.out.println("[회사 정보 삭제]");
		System.out.print("회사명을 입력하세요 : ");
		String comNm = sc.nextLine();
		
		System.out.print("정말 삭제 하시겠습니까?(Y/N) : ");
		char check = sc.nextLine().toUpperCase().charAt(0);
		
		if(check == 'Y') {
			try {
				
				Company upCompany = new Company(comNm, DevpleView.loginMember.getMemNo());
				
				int result = cService.updateSecessionCompany(upCompany);
				
				if(result > 0) {
					System.out.println("회사 정보 삭제에 성공 했습니다.");
				} else {
					System.out.println("존재하는 회사가 아닙니다.");
				}
				
			} catch (Exception e) {
				System.out.println("회사 정보 삭제 과정에서 오류가 발생 했습니다.");
				e.printStackTrace();
			}
		} else {
			System.out.println("삭제를 취소하였습니다.");
		}
	}

}
