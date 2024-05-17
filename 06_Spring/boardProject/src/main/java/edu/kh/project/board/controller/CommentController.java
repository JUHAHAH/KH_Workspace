package edu.kh.project.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.kh.project.board.model.dto.Comment;
import edu.kh.project.board.model.service.CommentService;
import lombok.RequiredArgsConstructor;

/*-
 * Rest API 구축용 컨트롤러 
 * @Controller + @ResponseBoy 와 동일한 효과
 * 
 * 모든 응답을 Ajax로 반환하는 경우
 * 
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
public class CommentController {
	private final CommentService service;

	@GetMapping("")
	public List<Comment> select(@RequestParam("boardNo") int boardNo) {
		return service.select(boardNo);
	}

	@PostMapping("")
	public int insert(@RequestBody Comment comment) {
		return service.insert(comment);
	}

	@PutMapping("")
	public int update(@RequestBody Comment comment) {
		return service.update(comment);
	}

	@DeleteMapping("")
	public int delete(@RequestBody int commentNo) {
		return service.delete(commentNo);

	}
}
