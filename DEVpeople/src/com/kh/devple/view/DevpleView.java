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
					case 3: rView.displayReview(); break;
					case 4: 
						loginMember = null;
						System.out.println("로그아웃 되었습니다.");
						break;
					case 0: System.out.println("프로그램을 종료합니다."); break;
					default : System.out.println("잘못 입력하셨습니다.");
					}
				} else {
					System.out.println("★★★★★★★★★ 개발의 민족 ★★★★★★★★★");
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
				System.out.println("4. 비밀번호 변경");
				System.out.println("0. 메인메뉴로 돌아가기");
				System.out.println("=========================================");
				System.out.print("메뉴 입력 >> ");
				sel = sc.nextInt();
				sc.nextLine();
				
				switch(sel) {
				case 1 : selectMyInfo(); break;
				case 2 : updateMyInfo(); break;
				case 3 : updateSecessionMember(); break;
				case 4 : updateMyPw(); break;
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
	/** 
	 *  회원 가입 View  
	 */
	private void signUp() {
		System.out.println("★★★★★★★★★ 개발의 민족 ★★★★★★★★★");
		System.out.println("『          회원 가입          』");
		
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
		
		// 회원 정보 -> Member 객체에 저장
		Member newMember = new Member(memId, memPw, memNm, phone, email, career, spec, devYn);
	
		try {
			// Service 메소드 호출 및 결과 반환
			int result = mService.signUp(newMember);
			
			if(result > 0) {
				System.out.println("회원 가입이 되었습니다");
			} else {
				System.out.println("회원 가입 실패");
			}
		} catch (Exception e) {
			System.out.println("회원 가입 과정에서 오류 발생");
			e.printStackTrace();
		}
	}
	
	/** 
	 * 로그인 View
	 */
	private void login() {
		System.out.println("『          로그인         』");
		
		System.out.print("아이디 : ");
		String memId = sc.nextLine();

		System.out.print("비밀번호 : ");
		String memPw = sc.nextLine();
		
		// ID, PW -> Member 객체에 저장
		Member member = new Member();
		member.setMemId(memId);
		member.setMemPw(memPw);
		
		try {
			// Service 메소드 호출 및 결과 반환
			loginMember = mService.login(member);
			
			// 로그인 성공
			if(loginMember != null) {
				System.out.println("---------- Welcom " + loginMember.getMemNm() + "! ----------");
			// 로그인 실패
			} else {
				System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다. 다시 확인해주시길 바랍니다. ");
			}	
		} catch (Exception e) {
			System.out.println("로그인 과정에서 오류 발생");
			e.printStackTrace();
		}	
	}
	

	/**
	 * 회원 정보 조회 View
	 */
	private void selectMyInfo() {
		// loginMember 객체에서 필요한 정보 가져옴
		System.out.println("『          내 정보          』");
		System.out.println("회원 번호 : " + loginMember.getMemNo());
		System.out.println("아이디 : " + loginMember.getMemId());
		System.out.println("이름 : " + loginMember.getMemNm());
		System.out.println("전화번호 : " + loginMember.getPhone());
		System.out.println("이메일 : " + loginMember.getEmail());
		System.out.println("경력 : " + loginMember.getCareer());
		System.out.println("기술 : " + loginMember.getSpec());
	}
	
	/**
	 * 회원 정보 수정 View
	 */
	private void updateMyInfo() {
		System.out.println("『          내 정보 변경          』");
		System.out.print(" 이름 : ");
		String memNm = sc.nextLine();
		
		System.out.print(" 전화번호 : ");
		String phone = sc.nextLine();
		
		System.out.print(" 이메일: ");
		String email = sc.nextLine();
		
		System.out.print(" 경력(년) : ");
		int career = sc.nextInt();
		sc.nextLine(); // 개행문자 제거
		
		System.out.print(" 기술 : ");
		String spec = sc.nextLine();
		
		// 수정할 정보 저장 객체 생성
		Member upMember = new Member(loginMember.getMemNo(), memNm, phone, email, career, spec);
		
		try {
			// Service 메소드 호출 및 결과 반환
			int result = mService.updateMyInfo(upMember);
			
			// ResultSet 성공 시
			if(result > 0) {
				System.out.println("내 정보 변경이 성공되었습니다");
				
				// 수정한 데이터 
				loginMember.setMemNm(memNm);
				loginMember.setPhone(phone);
				loginMember.setEmail(email);
				loginMember.setCareer(career);
				loginMember.setSpec(spec);
			
			// ResultSet 실패 시
			} else {
				System.out.println("내 정보 변경이 실패되었습니다");
			} 
		} catch (Exception e) {
			System.out.println("정보 수정 과정에서 오류 발생");
			e.printStackTrace();
		}
	}
	
	/**
	 * 비밀번호 변경 View
	 */
	private void updateMyPw() {
		System.out.println("『          비밀번호 변경          』");
		System.out.print("현재 비밀번호 : ");
		String currPw = sc.nextLine();
		
		System.out.print("새 비밀번호 : ");
		String newPw = sc.nextLine();
		
		System.out.print("새 비밀번호 확인 : ");
		String newPw2 = sc.nextLine();
		
		// 새 비밀번호 일치 확인
		if(newPw.equals(newPw2)) {
			
			// 새 비밀번호 저장 객체
			Member updatePw = new Member();
			
			// 회원 번호를 통해 현재 비밀번호 입력
			updatePw.setMemNo(loginMember.getMemNo());
			updatePw.setMemPw(currPw);
			
			try {
				// Service 메소드 호출 및 결과 반환
				int result = mService.updatePw(updatePw, newPw);
				
				// 반환 성공 시
				if(result > 0) {
					System.out.println("비밀번호가 변경 되었습니다");
				// 반환 실패 시
				} else {
					System.out.println("현재 비밀번호를 다시 확인해주세요");
				}	
			} catch (Exception e) {
				System.out.println("비밀번호 변경 과정에서 오류 발생");
			}
		}
	}
	
	/** 
	 * 	회원 탈퇴 여부 View
	 */
	private void updateSecessionMember() {
		System.out.println("『          회원 탈퇴          』");
		
		System.out.print("비밀번호 : ");
		String memPw = sc.nextLine();
		
		System.out.print("정말 삭제 하시겠습니까? (Y/N) : ");
		char check = sc.nextLine().toUpperCase().charAt(0);
		
		if(check == 'Y') {
			try {
				// 
				Member upMember = new Member();
				upMember.setMemNo(loginMember.getMemNo( ));
				upMember.setMemPw(memPw);
				
				int result = mService.updateSecessionMember(upMember);
				
				if(result > 0) {
					System.out.println("탈퇴 되셨습니다");
					System.out.println("감사합니다");
					loginMember = null;
				}else {
					System.out.println("비밀번호가 일치하지 않습니다");
				}
				
			} catch(Exception e){
				System.out.println("회원 탈퇴 과정에서 오류 발생");
			}	
		} else {
			System.out.println("탈퇴가 취소 되었습니다");
		}
	}
}
