package com.ac.controller.book;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ac.base.BaseController;
@Controller
@RequestMapping("bookController")
public class BookController extends BaseController {
	@RequestMapping("/books")
	public String goBooks(HttpServletRequest request) {
		
		request.setAttribute("msg", "欢迎...");
		
		return "book/books";
	}
}
