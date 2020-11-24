package com.kh.admin.comment.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.kh.admin.comment.model.service.CommentService;
import com.kh.admin.comment.model.vo.Comment;

public class CommentView {

	private Scanner sc = new Scanner(System.in);
	private CommentService cService = new CommentService();
	
	public void displayComment() {
int sel = 0;
		
		do {
			try {
				System.out.println("[댓글 관련 메뉴]");
				System.out.println("=================");
				System.out.println("1. 삭제된 댓글 조회");
				System.out.println("2. 삭제된 댓글 복구");
				System.out.println("0. 메인 메뉴로");
				System.out.println("=================");
				System.out.print("메뉴 선택 >> ");
				sel = sc.nextInt();
				sc.nextLine();
				
				switch(sel) {
				case 1: selectDeleteComment(); break;
				case 2: restoreComment(); break;
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
	 * 삭제된 댓글 조회 View
	 */
	private void selectDeleteComment() {
		System.out.println("[삭제된 댓글 조회]");
		try {
			List<Map<String, Object>> list = cService.selectDeleteComment();
			
			if(list.isEmpty()) {
				System.out.println("삭제된 댓글이 존재하지 않습니다.");
			} else {
				for(Map<String, Object> map : list) {
					Comment com = (Comment) map.get("comment");
					String memNm = (String) map.get("memNm");

					System.out.printf("%3d | %-30s | %5s | %s\n",
							com.getCommentNo(), com.getContent(), memNm, com.getCreateDt());
				}
			}
			
		} catch (Exception e) {
			System.out.println("삭제된 댓글 조회 중 오류 발생");
			e.printStackTrace();
		}
	}
	

	/**
	 * 댓글 복구 View
	 */
	private void restoreComment() {
		System.out.println("[삭제된 댓글 복구]");
		System.out.print("복구할 댓글의 번호 입력 : ");
		int commentNo = sc.nextInt();
		sc.nextLine();
		
		try {
			int result = cService.restoreComment(commentNo);
			
			if(result > 0) {
				System.out.println("댓글 복구가 완료되었습니다.");
			} else {
				System.out.println("존재하지 않는 댓글번호거나 삭제 되지 않은 댓글입니다.");
			}
			
		} catch (Exception e) {
			System.out.println("댓글 복구 도중 오류 발생");
			e.printStackTrace();
		}
		
	}

}
