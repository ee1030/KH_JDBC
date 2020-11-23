package com.kh.jdbc.board.view;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.kh.jdbc.board.model.service.BoardService;
import com.kh.jdbc.board.model.vo.Board;
import com.kh.jdbc.board.model.vo.VBoard;
import com.kh.jdbc.comment.model.vo.Comment;
import com.kh.jdbc.comment.view.CommentView;
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
				case 4 : updateBoard(); break; 
				case 5 : updateDeleteFl(); break; 
				case 6 : searchBoard(); break; 
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
			}else {
				
				int sel = 0;
				
				do {
					System.out.println("----------------------------------------------------------------------------");
					System.out.printf("%d | [%s] %s\n",vboard.getBoardNo(),  vboard.getCategoryNm(), vboard.getTitle());
					System.out.printf("작성자 : %s | 작성일  : %s | 조회수 %d\n",vboard.getMemNm(), vboard.getCreateDt(), vboard.getReadCount());
					System.out.println("----------------------------------------------------------------------------");
					System.out.println(vboard.getContent());
					System.out.println("----------------------------------------------------------------------------");
					
					// ************************ 댓글 기능 추가 ************************
					//  CommentView 객체 생성
					CommentView commentView =  new CommentView();
					
					// 1) 해당 게시글의 댓글 조회
					commentView.selectComment(boardNo);
					
					// 2) 댓글 삽입, 수정, 삭제
					System.out.println("--------------- [댓글 메뉴] ---------------");
					System.out.println("1. 댓글 작성 / 2. 댓글 수정 / 3. 댓글 삭제 / 0. 이전으로");
					System.out.print("메뉴 선택 >> ");
					sel = sc.nextInt(); // 댓글 메뉴 입력 받기
					sc.nextLine(); 
					
					// 작성, 수정, 삭제에 모두 필요한 회원번호, 게시글 번호를 저장한
					// Comment 객체 생성
					Comment comment = new Comment();
					comment.setMemNo(JDBCView.loginMember.getMemNo());
					comment.setBoardNo(vboard.getBoardNo());
					
					switch(sel) {
					case 1: commentView.insertComment(comment); break; // 작성 (회원번호, 게시글 번호, 댓글 내용)
					case 2:
						System.out.println("[댓글 수정]");
						commentView.updateComment(comment);
						break; // 수정 (게시글 번호, 댓글 내용 + 회원번호)
					case 3:
						System.out.println("[댓글 삭제]");
						commentView.updateDeleteFl(comment);
						break; // 삭제 (게시글 번호 + 회원 번호)
					case 0: System.out.println("게시판 메뉴로 돌아갑니다...");break;
					default : System.out.println("잘못 입력했데수");
					}
					
				} while(sel != 0);
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

	/**
	 * 게시글 수정 View
	 */
	private void updateBoard() {
		System.out.println("[게시글 수정]");
		
		// 게시글 번호를 입력 받아 해당 글이 로그인한 회원의 글인지 판별
		// -> checkMyBoard() 라는 메소드를 만들어서 판별
		int boardNo = checkMyBoard();
		
		if(boardNo > 0) {
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
				// 게시글 번호, 카테고리 번호, 제목, 내용
				Board board = new Board(boardNo, title, sb.toString(), categoryCd);
				
				int result = bService.updateBoard(board);
				
				if(result > 0) {
					System.out.println("게시글 수정에 성공했습니다.");
				} else {
					System.out.println("게시글 수정에 실패했습니다.");
				}
				
			} catch (Exception e) {
				System.out.println("게시글 수정 중 오류가 발생했습니다.");
				e.printStackTrace();
			}
		}
		
		
	}
	
	/** 게시글이 로그인한 회원의 글인지 판별하는 View
	 * @return result
	 */
	private int checkMyBoard() {
		// 게시글 번호 입력
		System.out.print("게시글 번호 입력 : ");
		int boardNo = sc.nextInt();
		sc.nextLine();
		
		int result = 0; // 글이 존재하는지에 대한 판별 결과를 저장할 변수
		
		try {
			Board board = new Board();
			board.setBoardNo(boardNo);
			board.setMemNo(JDBCView.loginMember.getMemNo());
			
			result = bService.checkMyBoard(board);
			
			if(result > 0) { // 입력한 번호의 글이 로그인한 회원의 글인 경우
				System.out.println("(확인완료)");
				result = boardNo;
			} else {
				System.out.println("자신의 글이 아니거나, 존재하지 않는 글 번호입니다.");
			}
			
		} catch (Exception e) {
			System.out.println("게시글 확인 과정에서 오류 발생");
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * 게시글 삭제 View
	 */
	private void updateDeleteFl() {
		System.out.println("[게시글 삭제]");
		
		int boardNo = checkMyBoard();
		
		if(boardNo > 0) {
			String random =  bService.randomString();

			System.out.print("다음 출력된 글자를 입력하시면 삭제됩니다. [" + random + "] : ");
			String input = sc.nextLine();
			
			if(input.equals(random)) {
				try {
					int result = bService.updateDeleteFl(boardNo);
					
					if(result > 0) {
						System.out.println(boardNo + "번글이 삭제되었습니다.");
					} else {
						System.out.println("삭제에 실패했습니다.");
					}
					
				} catch (Exception e) {
					System.out.println("게시글 삭제 중 오류가 발생했습니다.");
					e.printStackTrace();
				}
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		} 
	}
	
	/**
	 * 게시글 검색 View
	 */
	private void searchBoard() {
		System.out.println("[게시글 검색]");
		System.out.println("1. 제목 검색");
		System.out.println("2. 내용 검색");
		System.out.println("3. 제목 + 내용 검색");
		System.out.println("4. 작성자 검색");
		System.out.println("0. 취소");
		
		System.out.print("선택 : ");
		int sel = sc.nextInt();
		sc.nextLine();
		
		if(sel == 0) {
			System.out.println("검색 취소");
		} else if(sel >= 1 && sel <= 4) {
			// 1, 2, 3, 4번 메뉴 선택시	
			
			System.out.print("검색어 입력 : ");
			String keyword = sc.nextLine();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sel", sel);
			map.put("keyword", keyword);
				
			try {
				List<VBoard> list = bService.searchBoard(map);
				
				System.out.println("=== 검색 결과 ===");
				
				if(list.isEmpty()) {
					System.out.println("검색 결과가 존재 하지 않습니다.");
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
				System.out.println("검색 과정에서 오류 발생");
				e.printStackTrace();
			}
			
		} else {
			// 0, 1, 2, 3, 4 제외한 수 입력시
			System.out.println("잘못 입력하셨습니다.");
		}
	}


}
