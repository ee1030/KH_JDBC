package com.kh.devple.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.kh.devple.review.model.service.ReviewService;
import com.kh.devple.review.model.vo.Review;
import com.kh.devple.review.model.vo.VReview;

public class ReviewView {
	private Scanner sc = new Scanner(System.in);
	private ReviewService rService = new ReviewService();
	
	public void displayReview() {
		int sel = 0;
		
		do {
			try {
				System.out.println("=========================================");
				System.out.println("             ★~ 후기 관련 메뉴 ~★              ");
				System.out.println("1. 후기 조회");
				System.out.println("2. 후기 작성");
				System.out.println("0. 메인메뉴로 돌아가기");
				System.out.println("=========================================");
				System.out.print("메뉴 입력 >> ");
				sel = sc.nextInt();
				sc.nextLine();
				
				switch(sel) {
				case 1 : selectReview(); break;
				case 2 : insertReview(); break;
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
	
	/** 리뷰 조회 View
	 * @author 이진선
	 */
	private void selectReview() {
		System.out.println("[리뷰 조회]");
		
		try {
			List<VReview> list = rService.selectReview();
			
			if(list.isEmpty()) {
				System.out.println("조회 결과가 없습니다.");
			}else {
				for(VReview review : list) {
					System.out.println("----------------------------------------------------------------------------");
					System.out.printf("%d | %s\n",review.getRevwNo(), review.getTitle());
					System.out.printf("작성자 : %s | 작성일  : %s\n",review.getMemNm(), review.getCreateAt());
					System.out.println("----------------------------------------------------------------------------");
					System.out.print(review.getContent());
					System.out.println("----------------------------------------------------------------------------");

				}
			}
		} catch (Exception e) {
			System.out.println("리뷰 조회 중 오류 발생");
			e.printStackTrace();
		}
	}

	/** 리뷰 작성 View
	 * @author 이진선
	 */
	private void insertReview() {
		System.out.println("[리뷰 작성]");
		
		System.out.print("제목 : ");
		String title = sc.nextLine();
		
		StringBuffer sb = new StringBuffer();
		
		String str = null;
		
		System.out.println("----- 리뷰 작성(exit 작성 시 입력 종료) -----");
		while(true) {
			str = sc.nextLine();
			
			if(str.equals("exit")) {
				break;
			}
			
			sb.append(str + "\n");
		}
		
		try {
			Review review = new Review( title, sb.toString(), DevpleView.loginMember.getMemNo() );
			int result = rService.insertReview(review);
			
			if(result > 0) {
				System.out.println("리뷰 작성 성공");
			}else {
				System.out.println("리뷰 작성 실패");
			}
			
			
		} catch (Exception e) {
			System.out.println("게시글 작성 중 오류 발생");
			e.printStackTrace();
		}
		
	}

}
