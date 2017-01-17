package com.ac.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ac.base.BaseController;
import com.ac.entity.UserEntity;
import com.ac.service.LoginService;
import com.ac.util.CryptUtils;

@Controller
@RequestMapping()
public class LoginController extends BaseController {

	@Resource
	public LoginService loginService;

	@RequestMapping("/")
	public String goTask(String name,HttpServletRequest request) {
		request.setAttribute("name", name);
		return "/login";
	}

	@RequestMapping("/goLogin")
	@ResponseBody
	public int doLogin(UserEntity userEntity, HttpServletRequest request,HttpSession session) {

		UserEntity user = loginService.findUniqueByProperty(
				UserEntity.class, "name", userEntity.getName());
		
		if (user==null) {
			return 0;
		}  else {
			if (user.getPassword().equals(CryptUtils.getEncryptString(userEntity.getPassword()))) {
				session.setAttribute("ac", user);
				return 1;
			}
			else {
				return 2;
			}
		}
	}
}
