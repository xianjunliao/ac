package com.ac.controller.index;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ac.base.BaseController;
import com.ac.entity.MenuEntity;
import com.ac.entity.UserEntity;
import com.ac.service.index.IndexService;
import com.ac.util.CryptUtils;

@Controller
@RequestMapping()
public class IndexController extends BaseController {

	@Resource
	public IndexService indexService;

	@RequestMapping("/")
	public String goTask(HttpServletRequest request) {
		UserEntity sysUser = getSysUser(request);
		if (sysUser!=null) {
			
			request.setAttribute("username", sysUser.getName());
		}
		List<MenuEntity> menus = indexService.findListByProperty(MenuEntity.class,"isShow",0);
		request.setAttribute("menus",menus);
		return "/index";
	}
	@RequestMapping("/outLogin")
	public String outLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("ac");
		indexService.updateBySql("update menu set is_show=1 where menu_code='custom'");
		 return "redirect:/"; 
	}
	
	@RequestMapping("/doRegister")
	public String doRegister(HttpServletRequest request) {
		return "user/registerWindow";
	}
	
	@RequestMapping("/doLogin")
	public String goLogin(String name,HttpServletRequest request) {
		request.setAttribute("name", name);
		return "user/loginWindow";
	}
	@RequestMapping("/goLogin")
	@ResponseBody
	public int doLogin(UserEntity userEntity, HttpServletRequest request,HttpSession session) {

		UserEntity user = indexService.findUniqueByProperty(
				UserEntity.class, "name", userEntity.getName());
		
		if (user==null) {
			return 0;
		}  else {
			if (user.getPassword().equals(CryptUtils.getEncryptString(userEntity.getPassword()))) {
				session.setAttribute("ac", user);
				indexService.updateBySql("update menu set is_show=0 where menu_code='custom'");
				return 1;
			}
			else {
				return 2;
			}
		}
	}
}
