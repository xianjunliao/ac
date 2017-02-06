package com.ac.controller.custom;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ac.base.BaseController;
import com.ac.entity.MenuEntity;
import com.ac.entity.UserEntity;
import com.ac.service.custom.CustomService;

@Controller
@RequestMapping
public class CustomController extends BaseController {
	@Resource
	private CustomService customService;
	
	@RequestMapping("/custom")
	public String accounting(HttpServletRequest request) {
		UserEntity sysUser = getSysUser(request);
		if (sysUser != null) {

			request.setAttribute("username", sysUser.getName());
		}
		request.setAttribute("username", sysUser.getName());
		List<MenuEntity> menus = customService.findListByProperty(MenuEntity.class,"isShow",0);
		request.setAttribute("menus",menus);
		return "custom/custom";
	}
}
