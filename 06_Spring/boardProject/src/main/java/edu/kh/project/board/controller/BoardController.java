package edu.kh.project.board.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.BoardImg;
import edu.kh.project.board.model.service.BoardService;
import edu.kh.project.member.model.dto.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	 * @param paramMap : 제출된 파라미터 모두 저장 Map
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
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp, Model model,
			@RequestParam Map<String, Object> paramMap) {

//		log.debug("boardCode: " + boardCode);

		// 조횟 서비스 호출 후 결과 반환
		// pagination, boardList 실어올 예정
//		Map<String, Object> map = service.selectBoardList(boardCode, cp);

		Map<String, Object> map = null;

		if (paramMap.get("key") == null) {
			map = service.selectBoardList(boardCode, cp);

		} else {
			// boardCode를 추가
			paramMap.put("boardCode", boardCode);

			// 검색 서비스 호출 시
			map = service.searchList(paramMap, cp);

		}

		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("boardList", map.get("boardList"));

		return "board/boardList"; // boardList.html로 forward
	}

	public String getMethodName(@RequestParam String param) {
		return new String();
	}

	// 상세 조회 요청 주소
	// /board/1/1998?cp=1
	// /board/2/1960?cp=2
	@GetMapping("{boardCode:[0-9]+}/{boardNo:[0-9]+}")
	public String boardDetail(@PathVariable("boardCode") int boardCode, @PathVariable("boardNo") int boardNo,
			Model model, RedirectAttributes ra,
			@SessionAttribute(value = "loginMember", required = false) Member loginMember, HttpServletRequest req,
			HttpServletResponse resp) {
		// session 존재하지 않아도 됨
		// 속성값 없을 경우 null을 반환

		// 1) Map으로 전달할 파라미터 묶기
		Map<String, Integer> map = new HashMap<>();
		map.put("boardCode", boardCode);
		map.put("boardNo", boardNo);

		// 로그인 경우
		if (loginMember != null) {
			map.put("memberNo", loginMember.getMemberNo());
		}

		// 2) 서비스 호출
		Board board = service.selectOne(map);

		String path = null;

		// 조회 결과가 없는 경우
		if (board == null) {
			path = "redirect:/board/" + boardCode; // 목록 재요청
			ra.addFlashAttribute("message", "게시글이 존재하지 않습니다");
		} else {
			// 조회 결과 있는 경우
			/*-----------------------------------------------------------*/
			// 쿠키를 이용한 조회 수 증가
			// 1. 비회원 또는 로그인한 회원의 글이 아닌 경우
			// 글쓴이를 뺀 다른 사람
			if (loginMember == null || loginMember.getMemberNo() != board.getMemberNo()) {
				Cookie[] cookies = req.getCookies();

				Cookie c = null;

				for (Cookie temp : cookies) {
					if (temp.getName().equals("readBoardNo")) {
						c = temp;
						break;
					}
				}

				int result = 0; // 조회수 증가 결과를 저장할 변수

				// "readBoardNo" 가 쿠키에 없을 때
				if (c == null) {
					c = new Cookie("readBoardNo", "[" + boardNo + "]");
					result = service.updateReadCount(boardNo);

				} else {
					// "readBoardNo" 가 쿠키에 존재
					// "readBoardNo" : [2][30][400][2000][4000]

					// 현재 글을 처음 읽은 경우
					if (c.getValue().indexOf("[" + boardNo + "]") == -1) {

						// 해당 글 번호를 쿠키에 누적시켜주고 + 서비스 호출
						c.setValue(c.getValue() + "[" + boardNo + "]");
						result = service.updateReadCount(boardNo);
					}

					// 조회수 증가 / 조회 성공 시
					if (result > 0) {
						board.setReadCount(result);

						// 적용 경로 설정
						c.setPath("/"); // "/" 이하 경로에서 요청 시 쿠키 서버로 전달

						// 수명 지정

						// 현재 시간 얻어오기
						LocalDateTime now = LocalDateTime.now();

						// 다음날 자정
						LocalDateTime nextDayMidnight = now.plusDays(1).withHour(0).withMinute(0).withSecond(0)
								.withNano(0);

						// 다음날 자정까지 남은 시간 계산 (초 단위)
						long secondsUntilNextDay = Duration.between(now, nextDayMidnight).getSeconds();

						// 쿠키 수명 설정
						c.setMaxAge((int) secondsUntilNextDay);

						resp.addCookie(c); // 응답 객체를 잉요해서 클라이언트에게 전달

					}

				}

			}

			/*-----------------------------------------------------------*/
			path = "board/boardDetail"; // board/boardDetail.html
			// 게시글 내용, imageList, commentList
			model.addAttribute("board", board);

			// 조회된 이미지 목록이 존재하면
			if (!board.getImageList().isEmpty()) {
				BoardImg thumbnail = null;

				// imageList의 0번 인덱스 == 가장 빠른 순서(ImgOrder)
				// 이밎 목록의 첫번째 행이 순서 0 == 썸네일인 경우
				if (board.getImageList().get(0).getImgOrder() == 0) {
					thumbnail = board.getImageList().get(0);

				}

				model.addAttribute("thumbnail", thumbnail);
				model.addAttribute("start", thumbnail != null ? 1 : 0);
			}
		}

		return path; // board/boardDetail.html로 forward
	}

	@ResponseBody
	@PostMapping("like")
	public int boardLike(@RequestBody Map<String, Integer> map) {
		return service.boardLike(map);
	}
}
