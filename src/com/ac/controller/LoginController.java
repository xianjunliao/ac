package com.ac.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

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
	public String goTask(HttpServletRequest request) {
		return "/login";
	}

	@RequestMapping("/goLogin")
	@ResponsePayload
	public String doLogin(UserEntity userEntity, HttpServletRequest request) {

		UserEntity user = loginService.findUniqueByProperty(
				UserEntity.class, "name", userEntity.getName());
		
		if (user==null) {
			request.setAttribute("msg", "该用户不存在,");
		}  else {
			if (user.getPassword().equals(CryptUtils.getEncryptString(userEntity.getPassword()))) {
				request.setAttribute("msg", "登陆成功");
				return  "redirect:bookController/books";
			}
			else {
				request.setAttribute("msg", "密码错误");
			}
		}
		return "/login";
	}
}
