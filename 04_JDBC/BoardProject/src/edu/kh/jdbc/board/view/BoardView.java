package edu.kh.jdbc.board.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.board.model.dto.Board;
import edu.kh.jdbc.board.model.dto.Comment;
import edu.kh.jdbc.board.model.service.BoardService;
import edu.kh.jdbc.common.Session;

public class BoardView {
	
	private Scanner sc = new Scanner(System.in);
	
	private BoardService boardService = new BoardService();
	
	// 댓글 화면 출력 객체
	private CommentView commentView = new CommentView();
	

	public void boardMenu() {
		
		int input = -1;
		
		do {
			try {
				System.out.println("\n===== 게시판 기능 =====\n");
				System.out.println("1. 게시글 목록 조회");
				System.out.println("2. 게시글 상세 조회(+ 댓글 기능)");
				System.out.println("3. 게시글 작성");
				// 제목, 내용(StringBuffer 이용) 입력
				// -> 게시글 삽입 서비스(제목, 내용, 로그인 회원 번호) 호출
				
				System.out.println("9. 메인 메뉴로 돌아가기");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("\n메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine(); 
				
				System.out.println();
				
				switch(input) {
				case 1: selectAllBoard();  break; // 게시글 목록 조회
				case 2: selectBoard(); break; // 게시글 상세 조회
				case 3: //insertBoard(); break; // 게시글 등록(삽입)
				
				case 9: 
					System.out.println("\n===== 메인 메뉴로 돌아갑니다 =====\n");
					break;
				
				case 0: 
					System.out.println("\n=== 프로그램 종료 ===\n"); 
					System.exit(0);
				default: System.out.println("\n*** 메뉴 번호만 입력 해주세요 ***\n");  
				}
				
				System.out.println();
				
			}catch (InputMismatchException e) {
				System.out.println("\n*** 입력 형식이 올바르지 않습니다***\n");
				sc.nextLine(); // 입력버퍼에 잘못된 문자열 제거
				input = -1; // while문 종료 방지
			}
			
		}while(input != 9);
		
	}
	
	/**
	 * 게시글 목록 조회
	 */
	public void selectAllBoard() {
		System.out.println("\n==== 게시글 목록 조회 ====\n");
		
		// 3 | 샘플 제목[2] | 신짱구 | 2024-03-19 | 0
		// 3 | 샘플 제목[2] | 신짱구 | 2024-03-19 | 0
		// 3 | 샘플 제목[2] | 신짱구 | 2024-03-19 | 0
		// ... 
		
		try {
			// 게시글 목록 조회 서비스 호출
			List<Board> boardList = boardService.selectAllBoard();
			
			// 게시글이 없는 경우
			if(boardList.isEmpty()) {
				System.out.println("\n*** 게시글이 존재하지 않습니다 ***\n");
				return;
			}
			
			for(Board b : boardList) {
				System.out.printf("%d | %s[%d] | %s | %s | %d \n",
								b.getBoardNo(),
								b.getBoardTitle(),
								b.getCommentCount(),
								b.getMemberName(),
								b.getCreateDate(),
								b.getReadCount()
						);
			}
			
			
			
		} catch (Exception e) {
			System.out.println("\n*** 게시글 목록 조회 중 예외 발생 ***\n");
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 *  게시글 상세 조회 + 댓글 목록 조회
	 */
	public void selectBoard() {
		System.out.println("\n=== 게시글 상세 조회 ===\n");
		
		// 게시글 번호 입력
		// 1) 번호가 일치하는 게시글이 있으면 조회
		//	-> 조회수 증가(단, 자신이 작성한 게시글일 경우 조회수 증가 X)
		//  -> 자신이 작성한 게시글일 경우
		//		수정/삭제 기능 노출
		// + 댓글 목록/댓글 기능 추가 예정
		
		// 2) 번호가 일치하는 게시글 없으면
		//	-> 해당 게시글이 존재하지 않습니다.
		
		System.out.print("게시글 번호 입력 : ");
		int input = sc.nextInt();
		sc.nextLine();
		
		// 게시글 상세 조회 서비스 호출
		try {
			
			Board board = boardService.selectBoard(input, Session.loginMember.getMemberNo());
									//	 입력받은 게시글번호, 로그인한 회원번호(조회수 증가 사용)
			
			if(board == null) {
				System.out.println("\n*** 해당 게시글이 존재하지 않습니다 ***\n");
				return;
			}
			
			System.out.println("--------------------------------------------------------");
			System.out.printf("글번호 : %d \n제목 : %s\n", board.getBoardNo(), board.getBoardTitle());
			System.out.printf("작성자 : %s | 작성일 : %s  \n조회수 : %d\n", 
					board.getMemberName(), board.getCreateDate(), board.getReadCount());
			System.out.println("--------------------------------------------------------\n");
			System.out.println(board.getBoardContent());
			System.out.println("\n--------------------------------------------------------");
			
			// ******************************************************************************
			
			/* 해당 게시글의 댓글 목록 조회 */
			if(!board.getCommentList().isEmpty()) {
				
				for(Comment c : board.getCommentList()) {
					System.out.printf("[댓글 번호 : %d]  작성자 : %s   작성일 : %s\n%s\n",
							c.getCommentNo(), c.getMemberName(), c.getCreateDate(), c.getCommentContent()
							);
					System.out.println("----------------------------------------------------");
				}
				
				if(Session.loginMember.getMemberNo() == board.getMemberNo()) {
					while(true) {
						System.out.println("1) 수정");
						System.out.println("1) 삭제");
						System.out.println("1) 게시판 메뉴로 돌아가기");
						
						System.out.print("선택: ");
						int input = sc.nextInt();
						
						sc.nextLine();
						
						switch(input) {
						case 1: boardService.updateBoard(boardNo); break;
						case 2: break;
						case 0: return;
						default: System.out.println("\n*** 잘못 입력하셨습니다 ***\n");
						
						
						}
						
					}
					
				}
				
			}
			
			/* 댓글 메뉴 출력 */
			// 1) 댓글 등록 - 누가 몇번 게시글에 작성하는가?
			// 2) 댓글 수정 - 누가 몇번 게시글에있는 몇번 댓글을 수정할 것인가?
			// 3) 댓글 삭제 - 누가 몇번 게시글에있는 몇번 댓글을 삭제할 것인가?
			commentView.commentMenu(input); 
								// 게시글 번호
			
			//**************************************************************************************
			
			
			
		} catch (Exception e) {
			System.out.println("\n**** 게시글 상세 조회 중 예외 발생 ****\n");
			e.printStackTrace();
		}
		
		
	}
	
	public void boardUpdate() {
		
		StringBuffer sb = new StringBuffer();
		System.out.println("게시글 수정");
		
		while(true) {
			String str = sc.nextLine();
			
			sb.append(str);
			sb.append("\n");
			
			
		}
		try {
			int result = boardService.updateBoard(boardTitle , sb.toString() , boardNo);
			
			if(result > 0) {
				System.out.println("수정 성공");
				
			} else {
				System.out.println("수정 실패");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		
		
	}
	
	
	
	
	
	
	
	
}
