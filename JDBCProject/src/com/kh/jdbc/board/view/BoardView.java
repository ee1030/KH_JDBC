package com.kh.jdbc.board.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.kh.jdbc.board.model.service.BoardService;

/** Board View
 * @author 유현재
 *
 */
public class BoardView {
	
	private Scanner sc = new Scanner(System.in);
	private BoardService bService = new BoardService();
	
	/**
	 * 게시판 메뉴
	 */
	public void boardMenu() {
		int sel = 0;
		
		do {
			try {
				System.out.println("====================================");
				System.out.println("[게시판 메뉴]");
				System.out.println("1. 게시글 목록 조회");
				System.out.println("2. 게시글 상세 조회(게시글 번호)");
				System.out.println("3. 게시글 작성");
				System.out.println("4. 게시글 수정");
				System.out.println("5. 게시글 삭제");
				System.out.println("6. 게시글 검색(제목, 작성자, 내용, 제목+내용)");
				System.out.println("0. 메인 메뉴로 돌아가기");
				System.out.println("====================================");
				
				System.out.print("메뉴 선택 : ");
				sel = sc.nextInt();
				sc.nextLine();
				System.out.println();
				
				switch(sel) {
				case 1 : selectAllBoard(); break; 
				case 2 :  break; 
				case 3 :  break; 
				case 4 :  break; 
				case 5 :  break; 
				case 6 :  break; 
				case 0 : System.out.println("프로그램 종료"); break;
				default : System.out.println("메인 메뉴로 돌아갑니다.");
				}
				
			}catch(InputMismatchException e) {
				System.out.println("숫자만 입력해 주세요.");
				sel = -1;
				sc.nextLine(); // 버퍼에 남아있는 잘못 된 문자 제거 
			}
			
		}while(sel != 0);
	}

	private void selectAllBoard() {
		
		
	}

}
