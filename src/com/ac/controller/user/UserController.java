package com.ac.controller.user;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ac.base.BaseController;
import com.ac.entity.UserEntity;
import com.ac.service.user.UserServise;
import com.ac.util.CryptUtils;
import com.ac.util.StringUtils;

@Controller
@RequestMapping
public class UserController extends BaseController {

	@Resource
	public UserServise userServise;

	@RequestMapping("/register")
	public String doLogin(HttpServletRequest request) {

		return "user/register";
	}

	@RequestMapping("/addUser")
	@ResponseBody
	public int addUser(UserEntity userEntity, HttpServletRequest request) {

		try {
			if (!org.apache.commons.lang.StringUtils.isEmpty(userEntity.getName())) {

				UserEntity entity = userServise.findUniqueByProperty(
						UserEntity.class, "name", userEntity.getName());
				if (null != entity) {
					return 1;
				}
			}
			userEntity.setCreateTime(new Date());
			userEntity.setType(0);
			userEntity.setStatus(0);
			String ip = StringUtils.getIp2(request);
			userEntity.setIp(ip);
			userEntity.setPassword(CryptUtils.getEncryptString(userEntity
					.getPassword()));
			userServise.save(userEntity);
			
			
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;

	}
	
	@RequestMapping("/addUserAfterLogin")
	public String addUserAfterLogin(UserEntity userEntity, HttpServletRequest request) {

		return "redirect:/goLogin?userEntity="+userEntity;

	}
	@RequestMapping("/isExist")
	@ResponseBody
	public int isExist(String name, HttpServletRequest request) {

		try {
			if (!org.apache.commons.lang.StringUtils.isEmpty(name)) {

				UserEntity entity = userServise.findUniqueByProperty(
						UserEntity.class, "name", name);
				if (null != entity && !name.equals("")) {
					return 1;
				}
			}

			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
