package com.kh.jdbc.board.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.board.model.service.BoardService;
import com.kh.jdbc.board.model.vo.Board;
import com.kh.jdbc.board.model.vo.VBoard;
import com.kh.jdbc.view.JDBCView;

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
				case 2 : selectBoard(); break; 
				case 3 : insertBoard(); break; 
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

	/**
	 * 게시글 목록 조회 View
	 */
	private void selectAllBoard() {
		System.out.println("[게시글 목록]");
		// 카테고리명 | 글번호 | 제목 | 작성일 | 작성자 | 조회수
		
		try {
			// 게시글 목록 조회 Service 호출 후 결과를 반환받아 출력
			List<VBoard> list = bService.selectAllBaord();
			
			if(list.isEmpty()) {
				System.out.println("게시글이 존재하지 않습니다.");
			} else {
				System.out.printf(" %s | %s | %s | %s | %s | %s\n",
						"카테고리", "글번호", "제목", "작성일", "작성자", "조회수");
				for(VBoard board : list) {
					System.out.printf(" %s | %d | %s | %s | %s | %d\n",
							board.getCategoryNm(), board.getBoardNo(), 
							board.getTitle(), board.getCreateDt(), 
							board.getMemNm(),board.getReadCount());
				}
			}
			
		} catch (Exception e) {
			System.out.println("게시글 목록 조회 중 오류 발생");
			e.printStackTrace();
		}
	}
	

	/**
	 * 게시글 상세 조회 View 
	 */
	private void selectBoard() {
		System.out.println("[게시글 상세 조회]");
		System.out.print("조회할 글 번호 : ");
		int boardNo = sc.nextInt();
		sc.nextLine();
		
		try {
			VBoard vboard = bService.selectBoard(boardNo);
			
			if(vboard == null) {
				System.out.println("조회 결과가 없습니다.");
			} else {
				System.out.println("----------------------------------------------------------------------------");
				System.out.printf("%d | [%s] %s\n",vboard.getBoardNo(),  vboard.getCategoryNm(), vboard.getTitle());
				System.out.printf("작성자 : %s | 작성일  : %s | 조회수 %d\n",vboard.getMemNm(), vboard.getCreateDt(), vboard.getReadCount());
				System.out.println("----------------------------------------------------------------------------");
				System.out.println(vboard.getContent());
				System.out.println("----------------------------------------------------------------------------");
			}
			
		} catch (Exception e) {
			System.out.println("게시글 상세 조회 중 오류 발생");
			e.printStackTrace();
		}
	}
	
	/**
	 * 게시글 작성 View
	 */
	private void insertBoard() {
		System.out.println("[게시글 작성]");
		// 카테고리
		System.out.print("카테고리(1. JAVA / 2. DB / 3. JDBC)  :  ");
		int categoryCd = sc.nextInt();
		sc.nextLine();
		
		// 제목
		System.out.print("제목 : ");
		String title = sc.nextLine();
		
		// 내용
		StringBuffer sb = new StringBuffer();
		// 입력되는 모든 내용을 저장할 객체 생성
		
		String str = null;
		// 키보드 입력을 받아 임시 저장할 변수 선언
		
		System.out.println("------ 내용 입력(exit 입력시 내용 입력 종료) -----");
		while(true) {
			str = sc.nextLine();
			
			if(str.equals("exit")) break; // exit가 입력된 경우 반복문 종료
			
			sb.append(str + "\n");
			// 입력된 문자열을 StringBuffer에 누적한다.
		}
		
		try {
			// 카테고리코드, 제목, 내용, 회원번호를 저장할 수 있는 Board 객체를 생성
			Board board = new Board(title,
									sb.toString(),
									JDBCView.loginMember.getMemNo(),
									categoryCd);
			
			int result = bService.insertBoard(board);
			
			if(result > 0) {
				System.out.println("게시글 작성에 성공했습니다.");
			} else {
				System.out.println("게시글 작성에 실패했습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("게시글 작성 중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
	}


}
