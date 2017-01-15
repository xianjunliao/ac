package com.ac.controller.user;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ac.base.BaseController;
import com.ac.entity.UserEntity;
import com.ac.service.user.UserServise;
import com.ac.util.CryptUtils;
import com.ac.util.StringUtils;
@Controller
@RequestMapping("userController")
public class UserController extends BaseController{
    
	@Resource
	public UserServise userServise;
	
	@RequestMapping("/register")
	public String doLogin(HttpServletRequest request) {
		
	
		return "user/register";
	}
	@RequestMapping("/addUser")
	public String addUser(UserEntity userEntity, HttpServletRequest request) {
		
		userEntity.setCreateTime(new Date());
		userEntity.setType(0);
		userEntity.setStatus(0);
		String ip = StringUtils.getIp2(request);
		userEntity.setIp(ip);
		userEntity.setPassword(CryptUtils.getEncryptString(userEntity.getPassword()));
		userServise.save(userEntity);
		return  "redirect:/";
	}
}
