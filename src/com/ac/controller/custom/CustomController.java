package com.ac.controller.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ac.base.BaseController;
import com.ac.entity.MenuEntity;
import com.ac.entity.SubmenuEntity;
import com.ac.entity.UserEntity;
import com.ac.entity.UserMenuEntity;
import com.ac.service.custom.CustomService;
import com.ac.util.CriteriaQuery;
import com.ac.util.DataGrid;
import com.ac.util.HqlGenerateUtil;

@Controller
@RequestMapping
public class CustomController extends BaseController {
	@Resource
	private CustomService customService;
	
	@RequestMapping("/custom")
	public String accounting(HttpServletRequest request) {
		UserEntity sysUser = getSysUser(request);
		List<MenuEntity> menus =new ArrayList<>();
		Map<String, Object> map=new HashMap<>();
		if (sysUser != null) {
			map=new HashMap<>();
			map.put("userId", sysUser.getId());
			map.put("isShow", 1);
			List<UserMenuEntity> userMenuEntities = customService.findListByProperty(UserMenuEntity.class,map);
			for (UserMenuEntity userMenuEntity : userMenuEntities) {
				MenuEntity menuEntity = customService.findUniqueByProperty(MenuEntity.class, "id", userMenuEntity.getMenuId());
				menus.add(menuEntity);
			}
			
			request.setAttribute("menus",menus);
			if (menus.isEmpty()) {
				map=new HashMap<>();
				map.put("type", 0);
				map.put("isShow", 1);
				 menus = customService.findListByProperty(MenuEntity.class,map);
				 request.setAttribute("menus",menus);
			}
			request.setAttribute("username", sysUser.getName());
		}else{
			map.put("type", 0);
			map.put("isShow", 1);
			
		    menus = customService.findListByProperty(MenuEntity.class,map);
			request.setAttribute("menus",menus);
			
		}
		
		MenuEntity menuEntity = customService.findUniqueByProperty(MenuEntity.class, "menuCode", "custom");
		List<SubmenuEntity> submenus = customService.findListByProperty(SubmenuEntity.class, "pId", menuEntity.getId());
		request.setAttribute("submenus",submenus);
		return "custom/custom";
	}
	
	@RequestMapping("/datagrid")
	@ResponseBody
	public Map<String, Object> datagrid( HttpServletRequest request, DataGrid dataGrid) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			CriteriaQuery cq = new CriteriaQuery(MenuEntity.class, dataGrid);

			HqlGenerateUtil.installHql(cq, null, request.getParameterMap());

			map = customService.getDataGrid(cq);
			System.out.println(map);
		} catch (Exception e) {
			
		}

		return map;
	}
	
	@RequestMapping("/showMenu")
	public String showMenu(String[] ids, HttpServletRequest request, DataGrid dataGrid) {
		try {
			UserEntity sysUser = getSysUser(request);
			customService.updateBySqlInIds("update menu set user_id="+sysUser.getId()+" where id in (:ids)" , ids);
			return "redirect:/custom";

		} catch (Exception e) {
			
			return "redirect:/custom";
		}

	}
	
}
