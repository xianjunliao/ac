package com.ac.controller.index;

import java.math.BigInteger;
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
import com.ac.entity.UserMenuEntity;
import com.ac.service.index.IndexService;
import com.ac.util.CryptUtils;

@Controller
@RequestMapping()
public class IndexController extends BaseController {

	@Resource
	public IndexService indexService;

	@RequestMapping("/")
	public String goTask(HttpServletRequest request) {
		commonMapping("home",indexService,request);
		return "/index";
	}

	@RequestMapping("/outLogin")
	public String outLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("ac");
		return "redirect:/";
	}

	@RequestMapping("/doRegister")
	public String doRegister(HttpServletRequest request) {
		return "user/registerWindow";
	}

	@RequestMapping("/doLogin")
	public String goLogin(String name, HttpServletRequest request) {
		request.setAttribute("name", name);
		return "user/loginWindow";
	}

	@RequestMapping("/goLogin")
	@ResponseBody
	public int doLogin(UserEntity userEntity, HttpServletRequest request,
			HttpSession session) {

		UserEntity user = indexService.findUniqueByProperty(UserEntity.class,
				"name", userEntity.getName());

		if (user == null) {
			return 0;
		} else {
			if (user.getPassword().equals(
					CryptUtils.getEncryptString(userEntity.getPassword()))) {

				BigInteger order = indexService.findUniqueBySql("select ifnull(max(um_order),0) from user_menu where user_id=?",user.getId());
				List<UserMenuEntity> userMenus = indexService
						.findListBySql("select * from user_menu where menu_id in (1,2)");
				if (userMenus == null || userMenus.isEmpty()) {

					List<MenuEntity> menus = indexService.findListByProperty(
							MenuEntity.class);
					for (MenuEntity menuEntity : menus) {
						
						UserMenuEntity userMenuEntity = indexService.findUniqueBySql("select * from user_menu where menu_id=? and user_id=?",menuEntity.getId(),user.getId());
						if (userMenuEntity==null) {
							userMenuEntity = new UserMenuEntity();
							userMenuEntity.setIsShow(1);
							userMenuEntity.setMenuId(menuEntity.getId());
							userMenuEntity.setUserId(user.getId());
							userMenuEntity.setUmOrder(order.intValue()+1);
							indexService.save(userMenuEntity);
						}

					}
				}
				session.setAttribute("ac", user);
				session.setMaxInactiveInterval(1800);
				return 1;
			} else {
				return 2;
			}
		}
	}
}
