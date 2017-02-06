package com.ac.controller.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ac.base.BaseController;
import com.ac.entity.MenuEntity;
import com.ac.entity.SequenceEntity;
import com.ac.entity.SubmenuEntity;
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
		UserEntity sysUser = getSysUser(request);
		List<MenuEntity> menus =new ArrayList<>();
		Map<String, Object> map=new HashMap<>();

		if (sysUser != null) {
			map=new HashMap<>();
			map.put("userId", sysUser.getId());
			map.put("isShow", 1);
			List<UserMenuEntity> userMenuEntities = indexService.findListByProperty(UserMenuEntity.class,map);
			for (UserMenuEntity userMenuEntity : userMenuEntities) {
				MenuEntity menuEntity = indexService.findUniqueByProperty(MenuEntity.class, "id", userMenuEntity.getMenuId());
				menus.add(menuEntity);
			}
			
			request.setAttribute("menus",menus);
			if (menus.isEmpty()) {
				map=new HashMap<>();
				map.put("type", 0);
				map.put("isShow", 1);
				 menus = indexService.findListByProperty(MenuEntity.class,map);
				 request.setAttribute("menus",menus);
			}
			request.setAttribute("username", sysUser.getName());
		}else{
			map.put("type", 0);
			map.put("isShow", 1);
			
		    menus = indexService.findListByProperty(MenuEntity.class,map);
			request.setAttribute("menus",menus);
			
		}
		
		MenuEntity menuEntity = indexService.findUniqueByProperty(MenuEntity.class, "menuCode", "home");
		List<SubmenuEntity> submenus = indexService.findListByProperty(SubmenuEntity.class, "pId", menuEntity.getId());
		request.setAttribute("submenus",submenus);
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
				List<MenuEntity> menus = indexService.findListByProperty(MenuEntity.class, "type", 0);
				for (MenuEntity menuEntity : menus) {
					UserMenuEntity entity=new UserMenuEntity();
					entity.setIsShow(1);
					entity.setMenuId( menuEntity.getId());
					entity.setUserId(user.getId());
					indexService.save(entity);
					
				}
				session.setAttribute("ac", user);
				return 1;
			}
			else {
				return 2;
			}
		}
	}
}
