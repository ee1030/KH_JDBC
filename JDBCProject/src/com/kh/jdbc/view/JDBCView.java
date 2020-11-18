package com.kh.jdbc.view;

import java.util.InputMismatchException;
import java.util.List;
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
					case 1 : login(); break;
					case 2 : signUp(); break;
					case 0 : System.out.println("종료한다 애송이."); break;
					default : System.out.println("메뉴에 있는것만 고르시오");break;
					}
					
				} else {
					System.out.println("========================");
					System.out.println("★~ 메인메뉴 ~★");
					System.out.println("1. 회원기능");
					System.out.println("9. 로그아웃");
					System.out.println("0. 프로그램 종료");
					System.out.println("========================");
					
					System.out.print("메뉴 선택 >> ");
					sel = sc.nextInt();
					sc.nextLine();
					
					System.out.println();
					
					switch(sel) {
					case 1 : memberMenu(); break;
					case 9 : 
						loginMember = null;
						System.out.println("로그아웃했데수");
						System.out.println();
						break;
					case 0 : System.out.println("종료한다 애송이."); break;
					default : System.out.println("메뉴에 있는것만 고르시오");break;
					}
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
		try {
			int result = mService.signUp(newMember);
			if(result > 0) {
				System.out.println("회원 가입에 성공 했데수.\n");
			} else {
				System.out.println("회원 가입에 실패 했데수.\n");
			}
		} catch (Exception e) {
			System.out.println("회원가입 하다가 오류 났데수 ㅋㅋㅋㅋㅋㅋ\n");
			e.printStackTrace();
		}
	}
	
	/** 로그인용 View
	 * 
	 */
	private void login() {
		System.out.println("★=====★ 로그인 ★=====★");
		
		System.out.print("아이디 : ");
		String memId = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String memPw = sc.nextLine();
		
		// 아이디, 비밀번호를 하나의 Member 객체에 저장
		Member member = new Member();
		member.setMemId(memId);
		member.setMemPw(memPw);
		
		// 입력 받은 ID, PW를 이용하여 로그인 서비스 수행
		// -> 반환 받은 결과를 loginMember에 저장
		try {
			loginMember = mService.login(member);
			
			if(loginMember != null) {
				System.out.println("***** " + loginMember.getMemId() + "닝겐 환영한데수 *****");
			} else {
				System.out.println("아이디나 비밀번호를 확인해라 닝겐 탈퇴하지 않았는지도 생각해봐라");
			}
			
		} catch (Exception e) {
			System.out.println("로그인 중 오류가 발생했데수");
			e.printStackTrace();
		}
		
	}
	

	/** 
	 * 회원 기능 메뉴 View
	 */
	private void memberMenu() {
		int sel = 0;
		
		do {
			try {
				System.out.println("=========================================");
				System.out.println("             ★~ 회원기능 ~★              ");
				System.out.println("1. 내 정보 조회");
				System.out.println("2. 검색어가 이름에 포함된 모든 회원 검색");
				System.out.println("3. 내 정보 수정");
				System.out.println("4. 비밀번호 변경");
				System.out.println("5. 회원 탈퇴");
				System.out.println("6. 성별 조회");				
				System.out.println("0. 메인메뉴로 돌아가기");
				System.out.println("=========================================");
				System.out.print("메뉴 입력 >> ");
				sel = sc.nextInt();
				sc.nextLine();
				
				switch(sel) {
				case 1 : selectMyInfo(); break;
				case 2 : selectMemberName(); break;
				case 3 : updateMyInfo(); break;
				case 4 : updatePw(); break;
				case 5 : break;
				case 6 : selectGender(); break;
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
	 * 내 정보 조회 View
	 */
	private void selectMyInfo() {
		// DB에서 회원 정보를 조회할 필요 없이
		// loginMember에 담겨있는 값을 이용해 출력
		System.out.println("             ★~ 나의 정보 ~★              ");
		System.out.println("회원 번호 : " + loginMember.getMemNo());
		System.out.println("아이디 : " + loginMember.getMemId());
		System.out.println("이름 : " + loginMember.getMemNm());
		System.out.println("전화번호 : " + loginMember.getPhone());
		System.out.println("성별 : " + loginMember.getGender());
		System.out.println("가입일자 : " + loginMember.getHireDt());
	}
	

	/**
	 * 회원 이름에 검색어가 포함되는 모든 회원 조회
	 */
	private void selectMemberName() {
		// 1. 검색어를 입력받기
		System.out.println("             ★~ 이름이 포함된 사람 검색 ~★              ");
		System.out.print("검색할 단어 : ");
		String name = sc.nextLine();
		
		// 2. service에 알맞은 메소드 호출 후 결과 반환 받기
		try {
			List<Member> list = mService.selectMemberName(name);
			
			// 3. 결과로 List<Member>를 반환 받고 모두 출력
			if(list.isEmpty()) { // 리스트가 비어있다 == 조회 결과가 없다.
				System.out.println("조회 결과가 없습니다.");
			} else {
				for(Member m : list) {
					System.out.printf("%s / %s / %s / %c / %s\n", m.getMemId(), m.getMemNm(), m.getPhone(), m.getGender(), m.getHireDt());
				}
			}
			
		} catch(Exception e) {
			System.out.println("이름찾다 오류났데수");
			e.printStackTrace();
		}

	}
	
	/**
	 * 성별 검색 View
	 */
	private void selectGender() {
		// 성별(M 또는 F, 대소문자 구분 X)를 입력받아
		// DB에서 일치하는 회원을 모두 조회
		// 아이디, 이름, 전화번호 마지막 자리, 성별만 출력
		System.out.println("             ★~ 성별 검색 ~★              ");
		System.out.print("성별 입력 : ");
		char gender = sc.nextLine().toUpperCase().charAt(0);
		
		try {
			List<Member> list = mService.selectGender(gender);
			
			if(list.isEmpty()) {
				System.out.println("같은 성별의 조회 결과가 존재하지 않습니다.");
			} else {
				for(Member m : list) {
					System.out.printf("%s / %s / %s / %c\n", m.getMemId(), m.getMemNm(), m.getPhone(), m.getGender());
				}
			}
			
		}catch (Exception e) {
			System.out.println("성별 검색 중 오류가 발생했습니다.");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 내 정보 수정 View
	 */
	private void updateMyInfo() {
		System.out.println("           ★~ 내 정보 수정 ~★            ");
		System.out.println("=========================================");
		System.out.print("이름 : ");
		String memNm = sc.nextLine();
		
		System.out.print("전화번호 : ");
		String phone = sc.nextLine();
		
		System.out.print("성별 : ");
		char gender = sc.nextLine().toUpperCase().charAt(0);
		System.out.println("=========================================");
		
		// 입력받은 값 + 회원 번호를 저장할 Member 객체 생성
		Member upMember = new Member(loginMember.getMemNo(), memNm, phone, gender);
		
		try {
			// 생성한 member 객체를 매개변수로 하여 Service 메소드 호출 및 결과 반환
			int result = mService.updateMyInfo(upMember);
			
			if(result > 0) {
				System.out.println("정보 수정에 성공했데수");
				
				// loginMember는 수정전 데이터를 가지고 있으므로
				// 수정 성공시 데이터를 최신화 하여야 함
				loginMember.setMemNm(memNm);
				loginMember.setPhone(phone);
				loginMember.setGender(gender);
				
			} else {
				System.out.println("그런사람 없데수...");
			}
		} catch (Exception e) {
			System.out.println("정보 수정 중 오류 발생했데수");
			e.printStackTrace();
		}
	}
	
	/**
	 * 비밀번호 변경용 View
	 */
	private void updatePw() {
		System.out.println("           ★~ 비밀번호 변경 ~★            ");
		System.out.println("==========================================");
		System.out.print("현재 비밀번호 : ");
		String currPw = sc.nextLine();
		
		System.out.print("새 비밀번호 : ");
		String newPw = sc.nextLine();
		
		System.out.print("새 비밀번호 확인 : ");
		String newPw2 = sc.nextLine();
		System.out.println("==========================================");
		
		// 새 비밀번호가 일치하는지 판별
		if(newPw.equals(newPw2)) { // 같을 경우
			// 현재 비밀번호와 새 비밀번호 + 회원번호
			
			Member upMember = new Member();
			upMember.setMemNo(loginMember.getMemNo()); // 회원번호
			upMember.setMemPw(currPw); // 입력받은 현재 비밀번호
			
			
			try {
				int result = mService.updatePw(upMember, newPw);
				
				if(result > 0) {
					System.out.println("비밀번호 변경에 성공했데수");
				} else {
					System.out.println("현재 비밀번호를 잘못 임렦헇자나...");
				}
				
			} catch (Exception e) {
				System.out.println("비밀번호 바꾸다 오류났데수...");
				e.printStackTrace();
			}
			
		}
		
	}
}
