package edu.kh.project.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {
	@Autowired
	private MemberService service;

	@RequestMapping("/")
	public String mainPage(Model model) {

		List<String> result = service.getMemberList();

		model.addAttribute("memberList", result);

		return "common/main";
	}

	@GetMapping("loginError")
	public String loginError(RedirectAttributes ra) {

		ra.addFlashAttribute("message", "로그인 후 이용해주세요!");

		return "redirect:/";
	}

}
