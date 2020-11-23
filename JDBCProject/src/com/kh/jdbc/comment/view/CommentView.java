package com.kh.jdbc.comment.view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.kh.jdbc.comment.model.service.CommentService;
import com.kh.jdbc.comment.model.vo.Comment;

public class CommentView {
	private Scanner sc = new Scanner(System.in);
	private CommentService cService = new CommentService();
	
	/** 해당 게시글 댓글 조회 View
	 * @param boardNo
	 */
	public void selectComment(int boardNo) {
		System.out.println("[댓글]");
		
		try {
			List<Map<String, Object>> list = cService.selectComment(boardNo);
			
			// 댓글 리스트 출력
			for(Map<String, Object> map : list) {
				// map에 저장된 요소들을 꺼내두기
				Comment com = (Comment) map.get("comment");
				String memNm = (String) map.get("memNm");
				
				// %d : 10진 정수      / %f : 실수
				// %s : 문자열(String) / %c : 문자(char)
				
				// %10d : 오른쪽 정렬된 10칸짜리 정수
				// %-10d : 왼쪽 정렬된 10칸짜리 정수
				System.out.printf("%3d | %-30s | %5s | %s\n",
						com.getCommentNo(), com.getContent(), memNm, com.getCreateDt());
			}
			
		} catch (Exception e) {
			System.out.println("댓글 조회 과정에서 오류 발생");
			e.printStackTrace();
		}
		
	}

	/** 댓글 작성 View
	 * @param comment
	 */
	public void insertComment(Comment comment) {
		System.out.println("[댓글 작성]");
		String content = sc.nextLine();
		
		// comment 객체에 입력 받은 content를 저장하여 
		// comment 객체 하나로 데이터 전달
		comment.setContent(content);
		try {
			int result = cService.insertComment(comment);
			
			if(result > 0) {
				System.out.println("댓글 작성에 성공했습니다.");
			} else {
				System.out.println("댓글 작성에 실패했습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("댓글 작성 과정에서 오류가 발생 했습니다.");
			e.printStackTrace();
		}
		
	}

	/** 댓글 수정 View
	 * @param comment
	 */
	public void updateComment(Comment comment) {
		// 댓글 번호를 입력받아 자신의 댓글이 맞는지 확인
		int commentNo = checkMyComment(comment);
		
		if(commentNo > 0) { // 자신의 댓글이 맞다면
			comment.setCommentNo(commentNo);
			
			System.out.print("[댓글 수정] : ");
			String content = sc.nextLine();
			
			comment.setContent(content); // 입력받은 수정 내용도 comment 객체에 세팅
			
			try {
				int result = cService.updateComment(comment);
				
				if(result > 0) {
					System.out.println("댓글 수정에 성공했습니다.");
				} else {
					System.out.println("댓글 수정에 실패 했씁니다.");
				}
			} catch (Exception e) {
				System.out.println("댓글 수정 중 오류가 발생했습니다.");
				e.printStackTrace();
			}
		}
		
	}

	/** 댓글 삭제 View
	 * @param comment
	 */
	public void updateDeleteFl(Comment comment) {
		// 댓글 번호를 입력받아 자신의 댓글이 맞는지 확인
		int commentNo = checkMyComment(comment);
		
		if(commentNo > 0) { 
			System.out.print("정말 삭제하시겠습니까? (Y/N) : ");
			char input = sc.nextLine().toUpperCase().charAt(0);
			
			if(input == 'Y') {
				comment.setCommentNo(commentNo);
				try {
					int result = cService.updateDeleteFl(comment);
					
					if(result > 0) {
						System.out.println("댓글 삭제에 성공했습니다.");
					} else {
						System.out.println("댓글 삭제에 실패 했씁니다.");
					}
				} catch (Exception e) {
					System.out.println("댓글 삭제 중 오류가 발생했습니다.");
					e.printStackTrace();
				}
			} else {
				System.out.println("삭제를 취소했습니다.");
			}
		}
	}
	

	/** 자신의 댓글인지 확인하는 View
	 * @param comment
	 * @return result
	 */
	private int checkMyComment(Comment comment) {
		System.out.print("댓글 번호 입력 : ");
		int commentNo = sc.nextInt();
		sc.nextLine();
		
		// 게시글 번호, 회원 번호가 저장 되어있는 comment 객체에
		// 입력받은 댓글 번호 commentNo를 세팅
		comment.setCommentNo(commentNo);
		
		int result = 0; // 자신의 댓글이 맞다면 댓글 번호, 아니면 0을 반환
		
		try {
			result = cService.checkMyComment(comment);
			
			if(result > 0) {
				System.out.println("[확인 완료]");
				result = commentNo;
			} else {
				System.out.println("자신의 댓글이 아니거나, 없는 댓글 번호 입니다.");
			}
			
		} catch (Exception e) {
			System.out.println("댓글 확인 과정에서 오류가 발생했습니다.");
			e.printStackTrace();
		}
		
		return result;
	}
}
