package com.kh.admin.board.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.kh.admin.board.model.service.BoardService;
import com.kh.admin.board.model.vo.VBoard;
import com.kh.admin.board.model.vo.VBoard2;

public class BoardView {
	private Scanner sc = new Scanner(System.in);
	private BoardService bService = new BoardService();
	
	/**
	 * 게시판 관련 메뉴 View
	 */
	public void displayBoard() {
		int sel = 0;
		
		do {
			try {
				System.out.println("[회원 관련 메뉴]");
				System.out.println("=================");
				System.out.println("1. 모든 게시글 조회");
				System.out.println("2. 삭제된 게시글 조회");
				System.out.println("3. 삭제된 게시글 복구");
				System.out.println("0. 메인 메뉴로");
				System.out.println("=================");
				System.out.print("메뉴 선택 >> ");
				sel = sc.nextInt();
				sc.nextLine();
				
				switch(sel) {
				case 1: selectAllBoard(); break;
				case 2: selectDeleteBoard(); break;
				case 3: restoreBoard(); break;
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
	 * 모든 게시글 조회 View
	 */
	private void selectAllBoard() {
		System.out.println("[모든 게시글 조회]");
		
		try {
			List<VBoard2> list = bService.selectAllBoard();
			
			if(list.isEmpty()) {
				System.out.println("게시글이 존재하지 않습니다.");
			} else {
				for(VBoard2 board : list) {
					String deleteFl = "";
					
					if(board.getDeleteFl() == 'Y') deleteFl = "삭제됨";
					else deleteFl = "게시중";
						
					System.out.println("----------------------------------------------------------------------------");
					System.out.printf("%d | [%s] %s\n",board.getBoardNo(),  board.getCategoryNm(), board.getTitle());
					System.out.printf("작성자 : %s | 작성일  : %s | 조회수 %d | 삭제여부 : %s\n",board.getMemNm(), board.getCreateDt(), board.getReadCount(), deleteFl);
					System.out.println("----------------------------------------------------------------------------");
					System.out.println(board.getContent());
					System.out.println("----------------------------------------------------------------------------");
				}
			}
			
		} catch (Exception e) {
			System.out.println("게시글 조회 중 오류 발생");
			e.printStackTrace();
		}
	}

	/**
	 * 삭제된 게시글 조회 View
	 */
	private void selectDeleteBoard() {
		System.out.println("[삭제된 게시글 조회]");
		
		try {
			List<VBoard> list = bService.selectDeleteBoard();
			
			if(list.isEmpty()) {
				System.out.println("삭제된 게시글이 존재하지 않습니다.");
			} else {				
				for(VBoard board : list) {
					System.out.println("----------------------------------------------------------------------------");
					System.out.printf("%d | [%s] %s\n",board.getBoardNo(),  board.getCategoryNm(), board.getTitle());
					System.out.printf("작성자 : %s | 작성일  : %s | 조회수 : %d\n",board.getMemNm(), board.getCreateDt(), board.getReadCount());
					System.out.println("----------------------------------------------------------------------------");
					System.out.println(board.getContent());
					System.out.println("----------------------------------------------------------------------------");
				}
			}
			
		} catch (Exception e) {
			System.out.println("게시글 조회 중 오류 발생");
			e.printStackTrace();
		}
		
	}

	/**
	 * 삭제 게시글 복구 View
	 */
	private void restoreBoard() {
		System.out.println("[삭제된 게시글 복구]");
		System.out.print("복구할 게시글의 번호를 입력하세요 : ");
		int boardNo = sc.nextInt();
		sc.nextLine();
		
		try {
			int result = bService.restoreBoard(boardNo);
			
			if(result > 0) {
				System.out.println("게시글 복구가 완료되었습니다.");
			} else {
				System.out.println("존재하지 않는 게시글 번호거나 삭제 되지 않은 게시글입니다.");
			}
			
		} catch (Exception e) {
			System.out.println("게시글 복구 도중 오류 발생");
			e.printStackTrace();
		}
		
	}
}
