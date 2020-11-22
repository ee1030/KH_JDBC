package com.kh.devple.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.kh.devple.company.model.service.CompanyService;
import com.kh.devple.company.model.vo.Company;
import com.kh.devple.offer.model.service.OfferService;
import com.kh.devple.offer.model.vo.Offer;
import com.kh.devple.offer.model.vo.VOffer;

/** Offer 메뉴 View
 * @author 유현재
 *
 */
public class OfferView {
	private Scanner sc = new Scanner(System.in);
	private OfferService oService = new OfferService();
	private CompanyService cService = new CompanyService();

	/** 구인정보 메뉴 화면
	 * @author 유현재
	 */
	public void displayOffer() {
		int sel = 0;
		
		do {
			try {
				System.out.println("====================================================");
				System.out.println("             ★~ 구인 정보 관련 메뉴 ~★              ");
				System.out.println("1. 내 구인 정보 확인");
				System.out.println("2. 구인 정보 등록");
				System.out.println("3. 구인 정보 수정");
				System.out.println("4. 구인 정보 삭제");	
				System.out.println("0. 메인메뉴로 돌아가기");
				System.out.println("=====================================================");
				System.out.print("메뉴 입력 >> ");
				sel = sc.nextInt();
				sc.nextLine();
				
				switch(sel) {
				case 1: selectOfferByCompany(); break;
				case 2: insertOffer(); break;
				case 3: updateOffer(); break;
				case 4: updateSecessionOffer(); break;
				case 0 : System.out.println("메인메뉴로 돌아가겠다능"); break;
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
	 * 내가 등록한 구인 정보 조회 View
	 * @author 유현재
	 */
	private void selectOfferByCompany() {
		System.out.println("[내 구인 정보 조회]");
		
		try {
			int comNo = cService.findComNo(DevpleView.loginMember.getMemNo());
			List<VOffer> list = oService.selectOfferByCompany(comNo);
			
			if(list.isEmpty()) {
				System.out.println("구인 정보가 존재하지 않습니다.");
			} else {
				for(VOffer v : list) {
					System.out.println("===============================================================");
					System.out.println("번호 || 회사명 || 근무 지역 || 급여 || 계약 기간 || 업무");
					System.out.printf("%d || %s || %s || %d 만원 || %d 개월 || %s \n",
							v.getJoNo(), v.getComNm(), v.getLocation(), v.getSal(), v.getTerm(), v.getJob());
					System.out.println("===============================================================");
				}
			}
			
		} catch (Exception e) {
			System.out.println("구인 정보 조회 중 오류가 발생했습니다.");
			e.printStackTrace();
		}
	}

	/**
	 * 구인 정보 등록 View
	 * @author 유현재
	 */
	private void insertOffer() {
		System.out.println("[구인 정보 등록]");
		System.out.print("근무 지역 : ");
		String location = sc.nextLine();
		
		System.out.print("급여(만) : ");
		int sal = sc.nextInt();
		sc.nextLine();
		
		System.out.print("근무 기간(월) : ");
		int term = sc.nextInt();
		sc.nextLine();
		
		System.out.print("업무 내용 : ");
		String job = sc.nextLine();
		
		try {
			int comNo = cService.findComNo(DevpleView.loginMember.getMemNo());
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

	/**
	 * 구인 정보 수정 View
	 * @author 유현재
	 */
	private void updateOffer() {
		System.out.println("[구인 정보 수정]");
		System.out.print("근무 지역 : ");
		String location = sc.nextLine();
		
		System.out.print("급여(만) : ");
		int sal = sc.nextInt();
		sc.nextLine();
		
		System.out.print("근무 기간(월) : ");
		int term = sc.nextInt();
		sc.nextLine();
		
		System.out.print("업무 내용 : ");
		String job = sc.nextLine();
		
		try {
			int comNo = cService.findComNo(DevpleView.loginMember.getMemNo());
			Offer offer = new Offer(location, sal, term, job, comNo);
			
			int result = oService.updateOffer(offer);
			
			if(result > 0) {
				System.out.println("구인 정보 수정에 성공했습니다.");
			} else {
				System.out.println("구인 정보 수정에 실패했습니다.");
			}	
			
		} catch (Exception e) {
			System.out.println("구인정보 수정 중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
		
	}
	
	/** 구인정보 삭제 View
	 * @author 유현재
	 */
	private void updateSecessionOffer() {
		System.out.println("[구인 정보 삭제]");
		System.out.print("구인정보 번호를 입력하세요 : ");
		int joNo = sc.nextInt();
		sc.nextLine();
		
		System.out.print("정말 삭제 하시겠습니까?(Y/N) : ");
		char check = sc.nextLine().toUpperCase().charAt(0);
		
		if(check == 'Y') {
			try {
				int comNo = new CompanyService().findComNo(DevpleView.loginMember.getMemNo());
				int result = oService.updateSecessionOffer(joNo, comNo);
				
				if(result > 0) {
					System.out.println("구인 정보 삭제에 성공 했습니다.");
				} else {
					System.out.println("존재하는 구인 정보가 아니거나, 본인의 구인정보가 아닙니다.");
				}
				
			} catch (Exception e) {
				System.out.println("구인 정보 삭제 과정에서 오류가 발생 했습니다.");
				e.printStackTrace();
			}
		} else {
			System.out.println("삭제를 취소하였습니다.");
		}		
	}

}
