package edu.kh.project.board.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.project.board.model.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService service;

	// /board/xxx 레벨 1 자리에 숫자로된 요청 주소가 작성되어있을 경우 다음과 같이 정규 표현식 가능!

	/*
	 * @param boardCode : 게시판 종류 구분 번호
	 * 
	 * @param cp : 현재 조회를 요청한 페이지 번호, 없으면 1
	 * 
	 * @return
	 * 
	 * @ex [0-9]: 한칸에 0-9 사이의 숫자 입력 가능, +: 하나 이상 == [0-9]+: 모든 숫자
	 * 
	 */
	@GetMapping("{boardCode:[0-9]+}")
	public String selectBoardList(@PathVariable("boardCode") int boardCode,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp, Model model) {

//		log.debug("boardCode: " + boardCode);

		// 조횟 서비스 호출 후 결과 반환
		// pagination, boardList 실어올 예정
		Map<String, Object> map = service.selectBoardList(boardCode, cp);

		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("boardList", map.get("boardList"));

		return "board/boardList"; // boardList.html로 forward
	}
}
