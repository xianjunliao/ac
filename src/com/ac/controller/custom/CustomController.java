package com.ac.controller.custom;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ac.base.BaseController;
import com.ac.entity.MenuEntity;
import com.ac.entity.UserEntity;
import com.ac.entity.UserMenuEntity;
import com.ac.service.custom.CustomService;
import com.ac.util.CriteriaQuery;
import com.ac.util.DataGrid;
import com.ac.util.HqlGenerateUtil;
import com.ac.util.SortDirection;

@Controller
@RequestMapping
public class CustomController extends BaseController {
	@Resource
	private CustomService customService;

	@RequestMapping("/custom")
	public String accounting(HttpServletRequest request) {
		commonMapping(customService, request);
		return "custom/custom";
	}

	@RequestMapping("/datagrid")
	@ResponseBody
	public Map<String, Object> datagrid(HttpServletRequest request,
			DataGrid dataGrid) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			UserEntity sysUser = getSysUser(request);
			List<UserMenuEntity> list = customService.findListByHql("from UserMenuEntity where userId=? order by umOrder asc", sysUser.getId());
//			List<UserMenuEntity> list = customService.findListByProperty(UserMenuEntity.class,"umOrder",SortDirection.asc);
			List<MenuEntity> newList=new ArrayList<>();
			for (UserMenuEntity userMenuEntity : list) {
				MenuEntity menuEntity=customService.findUniqueByProperty(MenuEntity.class,"id", userMenuEntity.getMenuId());
				menuEntity.setMenuOrder(userMenuEntity.getUmOrder());
				newList.add(menuEntity);
			}
			map.put("total", newList.size());
			map.put("rows", newList);
			
		} catch (Exception e) {

		}

		return map;
	}

	@RequestMapping("/showMenu")
	public String showMenu(String[] ids, HttpServletRequest request) {
		try {
			UserEntity sysUser = getSysUser(request);
			BigInteger order = customService
					.findUniqueBySql(
							"select ifnull(max(um_order),0) from user_menu where user_id=?",
							sysUser.getId());
			for (String string : ids) {

				int mid = Integer.parseInt(string);
				LinkedHashMap<String, Object> whereParams = new LinkedHashMap<>();
				whereParams.put("userId", sysUser.getId());
				whereParams.put("menuId", mid);
				long count = customService.getCountByWhereParams(
						UserMenuEntity.class, whereParams);
				if (count == 0) {
					UserMenuEntity userMenuEntity = new UserMenuEntity();
					userMenuEntity.setIsShow(1);
					userMenuEntity.setMenuId(mid);
					userMenuEntity.setUserId(sysUser.getId());
					userMenuEntity.setUmOrder(order.intValue() + 1);
					customService.save(userMenuEntity);
				} else {

					customService.updateBySql(
							"update user_menu set is_show=1 where menu_id=?",
							mid);
				}
			}
			return "redirect:/custom";

		} catch (Exception e) {

			return "redirect:/custom";
		}

	}

	@RequestMapping("/hideMenu")
	public String hideMenu(String[] ids, HttpServletRequest request) {
		try {
			for (String id : ids) {

				customService.updateBySql(
						"update user_menu set is_show=0 where menu_id=?", id);
			}
			return "redirect:/custom";

		} catch (Exception e) {

			return "redirect:/custom";
		}

	}

	@RequestMapping("/showCustomMenu")
	public String showCustomMenu(String menuCode, HttpServletRequest request) {
		try {
			MenuEntity menuEntity = customService.findUniqueByProperty(
					MenuEntity.class, "menuCode", menuCode);
			UserEntity sysUser = getSysUser(request);
			Map<String, Object> map = new HashMap<>();
			map.put("userId", sysUser.getId());
			map.put("menuId", menuEntity.getId());

			List<UserMenuEntity> list = customService.findListByProperty(
					UserMenuEntity.class, map);
			for (UserMenuEntity userMenuEntity : list) {
				userMenuEntity.setIsShow(userMenuEntity.getIsShow() == 0 ? 1
						: 0);
				customService.update(userMenuEntity);
			}

			return "redirect:/";

		} catch (Exception e) {

			return "redirect:/";
		}

	}
	
	@RequestMapping("/goMenuOrder")
	public String goMenuOrder(HttpServletRequest request) {
		try {
			return "custom/menuOrder";

		} catch (Exception e) {

			return "custom/menuOrder";
		}

	}
	
	@RequestMapping("/goMenuOrderUp")
	public String goMenuOrderUp(String id,HttpServletRequest request) {
		try {
//			UserMenuEntity menuEntity = customService.findUniqueByProperty(UserMenuEntity.class, "id", id);
			UserEntity sysUser = getSysUser(request);
			int parseInt = Integer.parseInt(id);
			UserMenuEntity userMenuEntity = customService.findUniqueByHql("from UserMenuEntity where id=? ",parseInt);

			int umOrder = userMenuEntity.getUmOrder();
			customService.updateByHql("update UserMenuEntity set umOrder=? where id=?",umOrder-1 ,parseInt);
			
			customService.updateByHql("update UserMenuEntity set umOrder=? where userId=? and umOrder=?",userMenuEntity.getUmOrder()+1 ,sysUser.getId(),umOrder-1);
			
			return "custom/menuOrder";
			

		} catch (Exception e) {

			throw e;
		}

	}
}
