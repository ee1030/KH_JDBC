package com.kh.devple.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.kh.devple.company.model.service.CompanyService;
import com.kh.devple.company.model.vo.Company;
import com.kh.devple.member.model.service.MemberService;
import com.kh.devple.member.model.vo.Member;
import com.kh.devple.offer.model.service.OfferService;
import com.kh.devple.offer.model.vo.Offer;
import com.kh.devple.offer.model.vo.VOffer;
import com.kh.devple.review.model.service.ReviewService;

/**
 * @author 유현재, 박희도, 진선Lee
 *
 */
public class DevpleView {

	private Scanner sc = new Scanner(System.in);
	private MemberService mService = new MemberService();
	private OfferService oService = new OfferService();
	private CompanyService cService = new CompanyService();
	
	private CompanyView cView = new CompanyView();
	private OfferView oView = new OfferView();
	private ReviewView rView = new ReviewView();
	
	public static Member loginMember = null;
	
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
					case 1: login(); break;
					case 2: signUp(); break;
					case 0: System.out.println("프로그램 종료."); break;
					default : System.out.println("잘못 입력하셨습니다.");
					}
				} else if(loginMember.getDevYn() == 'Y') {
					System.out.println("★★★★★★★★★ 개발의 민족 ★★★★★★★★★");
					System.out.println("★★★★★★★★★ " + loginMember.getMemId() + "님 ㅎㅇ★★★★★★★★★");
					System.out.println("1. 구인정보 조회");
					System.out.println("2. 내정보 관련 메뉴");
					System.out.println("3. 후기 관련 메뉴");
					System.out.println("4. 로그 아웃");
					System.out.println("0. 프로그램 종료.");
					System.out.print("메뉴 선택 >> ");
					sel = sc.nextInt();
					sc.nextLine();
					
					switch(sel) {
					case 1: selectOffer(); break;
					case 2: displayInfo(); break;
					case 3: break;
					case 4: 
						loginMember = null;
						System.out.println("로그아웃 되었습니다.");
						break;
					case 0: System.out.println("프로그램을 종료합니다."); break;
					default : System.out.println("잘못 입력하셨습니다.");
					}
				} else {
					System.out.println("★★★★★★★★★ 개발의 민족 ★★★★★★★★★");
					System.out.println("★★★★★★★★★ " + loginMember.getMemId() + "님 ㅎㅇ★★★★★★★★★");
					System.out.println("1. 회사 관련 메뉴");
					System.out.println("2. 구인 정보 관련 메뉴");
					System.out.println("3. 내 정보 관련 메뉴");
					System.out.println("4. 로그아웃");
					System.out.println("0. 프로그램 종료");
					System.out.print("메뉴 선택 >> ");
					sel = sc.nextInt();
					sc.nextLine();
					
					switch(sel) {
					case 1: cView.displayCompany(); break;
					case 2: oView.displayOffer(); break;
					case 3: displayInfo(); break;
					case 4: 
						loginMember = null;
						System.out.println("로그아웃 되었습니다.");
						break;
					case 0: System.out.println("프로그램을 종료합니다."); break;
					default : System.out.println("잘못 입력하셨습니다.");
					}
				}
				
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sel = -1;
				sc.nextLine();
				e.printStackTrace();
			}
			
		} while(sel != 0);
	}

	/**
	 * 구인 정보 조회 View
	 */
	private void selectOffer() {
		System.out.println("[구인 정보]");
		int sel = 0;
		
		try {
			List<VOffer> list = oService.selectOffer();
			
			if(list.isEmpty()) {
				System.out.println("구인 정보가 존재하지 않습니다.");
			} else {
				for(VOffer v : list) {
					
					System.out.println("===============================================================");
					System.out.println("번호 || 회사명 || 근무 지역 || 급여 || 계약 기간 || 업무");
					System.out.printf("%d || %s || %s || %d 만원 || %d 개월 || %s \n",
							v.getJoNo(), v.getComNm(), v.getLocation(), v.getSal(), v.getTerm(), v.getJob());
					System.out.println("===============================================================");
					System.out.println("1. 회사 페이지로  ||   2. 다음 공고   ||    0. 이전메뉴로");
					System.out.print("메뉴 선택 >> ");
					sel = sc.nextInt();
					sc.nextLine();
					
					if(sel == 1) {
						selectCompany(v.getComNo());
					} else if(sel == 0) {
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("구인 정보 조회 중 오류가 발생했습니다.");
			e.printStackTrace();
		}
		
	}

	/** 구인정보 회사 조회 view
	 * @param comNo
	 */
	private void selectCompany(int comNo) {
		System.out.println("[선택한 공고의 회사 정보]");
		try {
			Company company = cService.selectCompany(comNo);
			
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
	 * 내 정보 관련 메뉴 View
	 */
	private void displayInfo() {
		int sel = 0;
		
		do {
			try {
				if(loginMember == null) return;
				// 회원 탈퇴로 인해 로그아웃 될 경우 메인 메뉴로 리턴
				System.out.println("=========================================");
				System.out.println("             ★~ 회원 정보 기능 ~★              ");
				System.out.println("1. 내 정보 조회");
				System.out.println("2. 내 정보 수정");
				System.out.println("3. 회원 탈퇴");				
				System.out.println("0. 메인메뉴로 돌아가기");
				System.out.println("=========================================");
				System.out.print("메뉴 입력 >> ");
				sel = sc.nextInt();
				sc.nextLine();
				
				switch(sel) {
				case 1 :  break;
				case 2 :  break;
				case 3 :  break;
				case 0 : System.out.println("메인메뉴로 돌아갑니다."); break;
				default : System.out.println("잘못 입력하셨습니다.");
				}
				
			}catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sel = -1;
				sc.nextLine();
			}
			
		} while(sel != 0);
	}

	/** 회원 가입 View 
	 *  @author 박희도
	 */
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
		
		System.out.print("개발자 여부(Y/N) : ");
		char devYn = sc.nextLine().toUpperCase().charAt(0);
		
		// 입력받은 값을 Member 객체에 저장
		Member newMember = new Member(memId, memPw, memNm, phone, email, career, spec, devYn);
	
		// 입력받은 회원 정보를 DB에 삽입하기 위한 service 호출
		try {
			int result = mService.signUp(newMember);
			
			if(result > 0) {
				System.out.println("회원 가입 성공 ~!");
			} else {
				System.out.println("회원 가입 실패 T^T");
			}
		} catch (Exception e) {
			System.out.println("회원 가입 과정에서 오류 발생..");
			e.printStackTrace();
		}
	}
	
	/** 로그인 View
	 * @author 박희도
	 */
	private void login() {
		System.out.println("『          로그인          』");
		
		System.out.print("아이디 : ");
		String memId = sc.nextLine();

		System.out.print("비밀번호 : ");
		String memPw = sc.nextLine();
		
		// 아이디, 비밀번호를 하나의 Memeber 객체에 저장
		Member member = new Member();
		member.setMemId(memId);
		member.setMemPw(memPw);
		
		try {
			// 입력 받은 ID, PW를 이용하여 로그인 서비스 수행
			// -> 반환 받은 결과를 loginMember에 저장
			loginMember = mService.login(member);
			
			// 로그인이 성공한 경우
			if(loginMember != null) {
				System.out.println("***** " + loginMember.getMemNm() + "님 환영 합니다  *****");
			} else {
				System.out.println("아이디 또는 비밀번호가 일치하지 않거나, 탈퇴한 회원 입니다");
			}	
		} catch (Exception e) {
			System.out.println("로그인 과정에서 오류 발생");
			e.printStackTrace();
		}
		
	}

}
