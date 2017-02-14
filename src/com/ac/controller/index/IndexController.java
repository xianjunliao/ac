package com.ac.controller.index;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ac.base.BaseController;
import com.ac.entity.ACTempEntity;
import com.ac.entity.MenuEntity;
import com.ac.entity.UserAcTempEntity;
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
	@RequestMapping("/page")
	public String goPage(HttpServletRequest request) {
		
		return "newPage/block_index";
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

				Integer id = user.getId();
				List<UserMenuEntity> userMenus = indexService
						.findListBySql("select * from user_menu where user_id=?",id);
				List<UserAcTempEntity> userAcTemps = indexService.findListByHql("from UserAcTempEntity where userId=?", id);
				if (userAcTemps==null||userAcTemps.isEmpty()) {
					List<ACTempEntity> list = indexService.findListByProperty(ACTempEntity.class);
					for (ACTempEntity acTempEntity : list) {
						UserAcTempEntity userAcTempEntity=new UserAcTempEntity();
						userAcTempEntity.setAcStatus(0);
						userAcTempEntity.setTempId(acTempEntity.getId());
						userAcTempEntity.setUserId(id);
						indexService.save(userAcTempEntity);
					}
				}
				int i=0;
				if (userMenus == null || userMenus.isEmpty()) {

					List<MenuEntity> menus = indexService.findListByProperty(
							MenuEntity.class);
					for (MenuEntity menuEntity : menus) {
						
						UserMenuEntity userMenuEntity = indexService.findUniqueBySql("select * from user_menu where menu_id=? and user_id=?",menuEntity.getId(),id);
						if (userMenuEntity==null) {
							userMenuEntity = new UserMenuEntity();
							userMenuEntity.setIsShow(1);
							userMenuEntity.setMenuId(menuEntity.getId());
							userMenuEntity.setUserId(id);
							userMenuEntity.setUmOrder(i);
							indexService.save(userMenuEntity);
							i++;
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
