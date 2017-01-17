package com.ac.controller.book;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ac.base.BaseController;
import com.ac.entity.AcBooksEntity;
import com.ac.entity.UserEntity;
import com.ac.service.book.BookService;

@Controller
@RequestMapping("book")
public class BookController extends BaseController {

	@Resource
	public BookService bookService;

	@RequestMapping("/books")
	public String goBooks(HttpServletRequest request) {

		UserEntity attribute = (UserEntity) request.getSession().getAttribute(
				"ac");
		request.setAttribute("username", attribute.getName());

		List<AcBooksEntity> list = bookService.findListByProperty(
				AcBooksEntity.class, "userId", attribute.getId());
		if (list.size() > 0) {
			request.setAttribute("list", list);
		}
		return "book/books";
	}

	@RequestMapping("/addBook")
	@ResponseBody
	public int addBook(List<AcBooksEntity> list, HttpServletRequest request) {

		if (list.size() > 0) {

			bookService.batchSave(list);
		}

		return 0;
	}
}
