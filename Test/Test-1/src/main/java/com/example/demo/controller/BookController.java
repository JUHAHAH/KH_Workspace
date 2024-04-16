package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Book;

@Controller
@RequestMapping("book")
public class BookController {
	
@ResponseBody
@GetMapping("selectAllList")
public List<Book> selectAllList() {
	List<Book> list = new ArrayList<Book>();
	Book book1 = new Book(1, "불변의 법칙", "모건 하우절", 22500);
	Book book2 = new Book(2, "하하하", "ㅎㅎ", 3000);
	
	list.add(book1);
	list.add(book2);
	
    return list;
}


}
