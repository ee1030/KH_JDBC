package com.kh.admin.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.kh.admin.board.view.BoardView;
import com.kh.admin.comment.view.CommentView;
import com.kh.admin.member.view.MemberView;

public class AdminView {
	private Scanner sc = new Scanner(System.in);
	private MemberView mView = new MemberView();
	private BoardView bView = new BoardView();
	private CommentView cView = new  CommentView();
	
	/**
	 * 메인 메뉴 View
	 */
	public void displayMain() {
		int sel = 0;
		
		do {
			try {
				System.out.println("[관리자 프로그램]");
				System.out.println("=================");
				System.out.println("1. 회원 관련 메뉴");
				System.out.println("2. 게시판 관련 메뉴");
				System.out.println("3. 댓글 관련 메뉴");
				System.out.println("0. 프로그램 종료");
				System.out.println("=================");
				System.out.print("메뉴 선택 >> ");
				sel = sc.nextInt();
				sc.nextLine();
				
				switch(sel) {
				case 1: mView.displayMem(); break;
				case 2: bView.displayBoard(); break;
				case 3: cView.displayComment(); break;
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
}
