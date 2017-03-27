package com.ac.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ac.base.BaseController;

@Controller
@RequestMapping()
public class IndexController extends BaseController {


//	@RequestMapping("/")
//	public String goTask(HttpServletRequest request) {
//		commonMapping("home",indexService,request);
//		return "/index";
//	}
	@RequestMapping("/")
	public String goPage(HttpServletRequest request) {
		
		return "memory_tree/index";
	}
	
	
}
