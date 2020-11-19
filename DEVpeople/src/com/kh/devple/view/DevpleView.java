package com.kh.devple.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.kh.devple.company.model.service.CompanyService;
import com.kh.devple.company.model.vo.Company;
import com.kh.devple.member.model.service.MemberService;
import com.kh.devple.member.model.vo.Member;
import com.kh.devple.offer.model.service.OfferService;
import com.kh.devple.offer.model.vo.Offer;
import com.kh.devple.review.model.service.ReviewService;

/**
 * @author 유현재, 박희도, 진선Lee
 *
 */
public class DevpleView {

	private Scanner sc = new Scanner(System.in);
	private MemberService mService = new MemberService();
	private CompanyService cService = new CompanyService();
	private OfferService oService = new OfferService();
	private ReviewService rService = new ReviewService();
	
	private Member loginMember = null;
	
	public void displayMain() {
		int sel = 0;
		
	
		do {
			try {	
				if(loginMember == null) {
					System.out.println("★★★★★★★★★ 개발의 민족 ★★★★★★★★★");
					System.out.println("★★★★★★★★★  어서오세요 ★★★★★★★★★");
					System.out.println("1. 로그인");
					System.out.println("2. 회원가입");
					System.out.println("0. 프로그램 종료");
					System.out.print("메뉴 입력 >> ");
					sel = sc.nextInt();
					sc.nextLine();
					
					switch(sel) {
					case 1: break;
					case 2: signUp(); break;
					case 0: System.out.println("프로그램 종료."); break;
					default : System.out.println("잘못 입력했셈");
					}
				} else if(loginMember.getDevYn() == 'Y') {
					System.out.println("★★★★★★★★★ 개발의 민족 ★★★★★★★★★");
					System.out.println("★★★★★★★★★ " + loginMember.getMemId() + "님 ㅎㅇ★★★★★★★★★");
					System.out.println("1. 구인정보 조회");
					System.out.println("2. 내 정보 조회");
					System.out.println("3. 내 정보 수정");
					System.out.println("4. 후기 조회");
					System.out.println("5. 후기 작성");
					System.out.println("6. 회원 탈퇴");
					System.out.println("0. 로그아웃");
					
					switch(sel) {
					case 1: break;
					case 2: break;
					case 3: break;
					case 4: break;
					case 5: break;
					case 6: break;
					case 0: 
						loginMember = null;
						System.out.println("로그아웃 되었습니다.");
						break;
					default : System.out.println("잘못 입력했셈");
					}
				} else {
					System.out.println("★★★★★★★★★ 개발의 민족 ★★★★★★★★★");
					System.out.println("★★★★★★★★★ " + loginMember.getMemId() + "님 ㅎㅇ★★★★★★★★★");
					System.out.println("1. 회사 등록");
					System.out.println("2. 회사 정보 수정");
					System.out.println("3. 회사 등록해제");
					System.out.println("4. 구인정보 작성");
					System.out.println("5. 구인정보 수정");
					System.out.println("6. 구인정보 삭제");
					System.out.println("7. 회원 탈퇴");
					System.out.println("0. 로그아웃");
					
					switch(sel) {
					case 1: insertCompany(); break;
					case 2: updateCompany(); break;
					case 3: updateSecessionCompany(); break;
					case 4: insertOffer(); break;
					case 5: break;
					case 6: break;
					case 7: break;
					case 0: 
						loginMember = null;
						System.out.println("로그아웃 되었습니다.");
						break;
					default : System.out.println("잘못 입력했셈");
					}
				}
				
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력하셈");
				sel = -1;
				sc.nextLine();
				e.printStackTrace();
			}
			
		} while(sel != 0);
	}

	private void signUp() {
		System.out.println("★★★★★★★★★ 개발의 민족 ★★★★★★★★★");
		System.out.println("[회원 가입]");
		
		System.out.print("아이디 : ");
		String memId = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String memPw = sc.nextLine();
		
		System.out.print("이름 : ");
		String memNm = sc.nextLine();
		
		System.out.print("전화번호 : ");
		String phone = sc.nextLine();
		
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("경력(년) : ");
		int career  = sc.nextInt();
		sc.nextLine();
		
		System.out.print("기술 스펙 : ");
		String spec = sc.nextLine();
		
		System.out.println("개발자 여부(Y/N) : ");
		char devYn = sc.nextLine().toUpperCase().charAt(0);
		
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
			Company company = new Company(comNm, phone, email, sb.toString(), loginMember.getMemNo());	
			
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
			Company company = new Company(comNm, phone, email, sb.toString(), loginMember.getMemNo());	
			
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
				
				Company upCompany = new Company(comNm, loginMember.getMemNo());
				
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
	

	/**
	 * 구인 정보 등록 View
	 */
	private void insertOffer() {
		System.out.println("[구인 정보 등록]");
		System.out.print("근무 지역 : ");
		String location = sc.nextLine();
		
		System.out.println("급여(만) : ");
		int sal = sc.nextInt();
		sc.nextLine();
		
		System.out.println("근무 기간(월) : ");
		int term = sc.nextInt();
		sc.nextLine();
		
		System.out.println("업무 내용 : ");
		String job = sc.nextLine();
		
		try {
			int comNo = cService.findComNo(loginMember.getMemNo());
			Offer offer = new Offer(location, sal, term, job, comNo);
			
			int result = oService.insertOffer(offer);
			
			if(result > 0) {
				System.out.println("구인 정보 등록에 성공했습니다.");
			} else {
				System.out.println("구인 정보 등록에 실패했습니다.");
			}	
			
		} catch (Exception e) {
			System.out.println("구인정보 등록 중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
	}

}
