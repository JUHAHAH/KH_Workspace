package edu.kh.project.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.service.BoardService;
import edu.kh.project.board.model.service.EditBoardService;
import edu.kh.project.member.model.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("editBoard")
public class EditBoardController {
	private final EditBoardService service;
	private final BoardService boardService;

	/**
	 * 게시글 화면 전환
	 * 
	 * @param boardCode
	 * @return
	 */
	@GetMapping("{boardCode:[0-9]+}/insert")
	public String boardInsert(@PathVariable("boardCode") int boardCode) {

		return "board/boardWrite";
	}

	/**
	 * 게시글 작성
	 * 
	 * @param boardCode   : 어떤 게시글에 작성할 글인지 ㄱ구분
	 * @param inputBoard  : 입력된(제목, 내용) 값
	 * @param loginMember : 로그인한 회원 번호 조회용
	 * @param images      : 제출된 file 타입 input 태그 데이터들
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@PostMapping("{boardCode:[0-9]+}/insert")
	public String boardInsert(@PathVariable("boardCode") int boardCode, @ModelAttribute Board inputBoard,
			@SessionAttribute("loginMember") Member loginMember, @RequestParam("images") List<MultipartFile> images,
			RedirectAttributes ra) throws IllegalStateException, IOException {
		/*-
		 * 파일이 선택되지 않은 input 태그도 제출되고 있다 => 오류 발생
		 * 
		 * 무작정 서버에 저장하는 것보다 제출된 파일이 존재하는지 확인하는 로직 필요
		 * 
		 * */

		// 1. boardCode, 로그인한 회원 번호를 inputBoard에 세팅
		inputBoard.setBoardCode(boardCode);
		inputBoard.setMemberNo(loginMember.getMemberNo());

		// 2. 서비스 메서드 호출 후 결과 반환 받기
		// 삽입 성공 시 상세조회 요펑할 수 있도록 게시글 번호 반환
		int boardNo = service.boardInsert(inputBoard, images);

		// 3. 결과에 따라 message, 리다이렉트 경로 지정
		String path = null;
		String message = null;

		if (boardNo > 0) {
			path = "/board/" + boardCode + "/" + boardNo; // 상세조회 경로
			message = "게시글이 작성되었습니다";

		} else {
			path = "insert";
			message = "게시글 작성 실패";
		}

		ra.addFlashAttribute("message", message);

		return "redirect:" + path;
	}

	/**
	 * 게시글 수정 화면 전환
	 * 
	 * @param boardCode   : 게시판 종류
	 * @param boardNo     : 게시글 번호
	 * @param loginMember : 로그인 회원이 작성한 글이 맞는지 검사하는 용도
	 * @param model       : 포워드 시 request scope로 값 전달
	 * @param ra          : 리다이렉트 시 request scope로 값 전달
	 * @return
	 */
	@GetMapping("{boardCode:[0-9]+}/{boardNo:[0-9]+}/update")
	public String boardUpdate(@PathVariable("boardCode") int boardCode, @PathVariable("boardNo") int boardNo,
			@SessionAttribute("loginMember") Member loginMember, Model model, RedirectAttributes ra) {

		// 수정화면에 출력할 기존의 제목 / 내용 / 이미지 조회
		// -> 게시글 상세 조회

		Map<String, Integer> map = new HashMap<>();
		map.put("boardCode", boardCode);
		map.put("boardNo", boardNo);

		// BoardService.seelctOne(map) 호출
		Board board = boardService.selectOne(map);

		String message = null;
		String path = null;

		if (board == null) {
			message = "해당 게시글이 존재하지 않습니다";
			path = "redirect:/"; // 메인 페이지

			ra.addFlashAttribute("message", message);

		} else if (board.getMemberNo() != loginMember.getMemberNo()) {
			message = "자신이 작성한 글만 수정할 수 있습니다";
			// 해당 글을 상세 조회한 페이지로 리다이렉트
			path = String.format("redirect:/board/%d/%d", boardCode, boardNo);

			ra.addFlashAttribute("message", message);

		} else {
			path = "board/boardUpdate";
			model.addAttribute("board", board);
		}

		return path;
	}

	/**
	 * @param boardCode   : 게시판 종류
	 * @param boardNo     : 게사판 번호
	 * @param inputBoard  : 커맨드 객체
	 * @param loginMember : 로그인한 회원 번호
	 * @param images      : 제출된 input type="file" 모든 요소
	 * @param ra          : redirect 시 request scope 값 전달
	 * @param deleteOrder : 삭제된 이미지 순서가 기록된 문자열
	 * @param queryString : 수정 성공시 파라미터 유지
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@PostMapping("{boardCode:[0-9]+}/{boardNo:[0-9]+}/update")
	public String boardUpdate(@PathVariable("boardCode") int boardCode, @PathVariable("boardNo") int boardNo,
			@ModelAttribute Board inputBoard, @SessionAttribute("loginMember") Member loginMember,
			@RequestParam("images") List<MultipartFile> images, RedirectAttributes ra,
			@RequestParam(value = "deleteOrder", required = false) String deleteOrder,
			@RequestParam(value = "queryString", required = false, defaultValue = "") String queryString)
			throws IllegalStateException, IOException {

		// 1. 커맨드 객체에 boardCode, boardNo, memberNo 세팅
		inputBoard.setBoardCode(boardCode);
		inputBoard.setBoardNo(boardNo);
		inputBoard.setMemberNo(loginMember.getMemberNo());

		// 2. 게시글 수정 서비스 호출 후 결과 반환 받기
		int result = service.boardUpdate(inputBoard, images, deleteOrder);

		// 3, 서비스 결과에 따른 응답
		String message = null;
		String path = null;

		if (result > 0) {
			message = "게시글이 수정 되었습니다";
			path = String.format("/board/%d/%d%s", boardCode, boardNo, queryString); // /board/1/2000?cp=3
		} else {
			message = "수정 실패";
			path = "update"; // 수정 화면 전환 리다이렉트하는 상대경로
		}

		ra.addFlashAttribute("message", message);

		return "redirect:" + path;
	}
}
