package com.ac.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.ac.util.StringEscapeEditor;

public class BaseController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringEscapeEditor(false, false, false));
	}

	// 防止js和html注入 List<Map<String,Object>>
	public void strEscapeMap(List<Map<String, Object>> datas) {
		try {
			for (Map<String, Object> data : datas) {
				Iterator<String> it = data.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next();
					Object value = data.get(key);
					if (value instanceof String && value != null) {
						value = StringEscapeUtils.escapeHtml4(value.toString());
						value = StringEscapeUtils.escapeEcmaScript(value.toString());
						data.put(key, value);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 防止js和html注入 T
	public void strEscape(Object t) {
		if (t == null)
			return;
		Class c = t.getClass();
		Method[] ms = c.getMethods();
		for (Method m : ms) {
			if (m.getName().startsWith("set")) {
				Method m_get;
				try {
					m_get = c.getMethod("get" + m.getName().substring(3));
					if (m_get != null) {
						Object o = m_get.invoke(t);
						if (o instanceof String && o != null) {
							String value = o.toString();
							value = StringEscapeUtils.escapeHtml4(value.toString());
							value = StringEscapeUtils.escapeEcmaScript(value.toString());
							m.invoke(t, value);
						}
					}
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 防止js和html注入 List<T>
	public void strEscapeList(List<? extends Object> datas) {
		if (datas == null || datas.size() == 0)
			return;
		try {
			Object t = datas.get(0);
			for (Object d : datas)
				strEscape(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void downloadFile(String name, String path, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String filePath = path + "/" + name;

		response.setHeader("Content-Disposition", "attachment; filename=" + new String(name.getBytes(), "iso-8859-1"));
		response.setContentType("application/msword;charset=utf-8");

		ServletOutputStream out = response.getOutputStream();
		InputStream is = new FileInputStream(filePath);
		byte[] buffer = new byte[10240];
		for (int n = 0; -1 != (n = is.read(buffer));) {
			out.write(buffer, 0, n);
		}
		out.close();
		is.close();
	}
	
//	/**
//	 * 获取当前用户
//	 * @author jzc
//	 * @param request
//	 * @return
//	 */
//	public UserEntity getSysUser(HttpServletRequest request){
//		HttpSession session = request.getSession();
//		return ((UserEntity) session.getAttribute("ac"));
//	}
	
	
	/**
	 * 返回结果，1：成功，0：失败
	 */
	public void returnResult(HttpServletRequest request, int type, String msg) {
		request.setAttribute("error", type);
		request.setAttribute("msg", msg);
	}
	
	public void OutputFlowDiagramStream(InputStream is, HttpServletResponse response) throws IOException {
		ServletOutputStream out = response.getOutputStream();
		byte[] buffer = new byte[10240];
		for (int n = 0; -1 != (n = is.read(buffer));) {
			out.write(buffer, 0, n);
		}
		out.close();
		is.close();
	}
//	public void commonMapping(String menuCode,CommonService commonService,HttpServletRequest request) {
//		UserEntity sysUser = getSysUser(request);
//		List<MenuEntity> menus = new ArrayList<>();
//		Map<String, Object> map = new HashMap<>();
//		if (sysUser != null) {
//			map = new HashMap<>();
//			List<UserMenuEntity> userMenuEntities = commonService.findListByHql("from UserMenuEntity where userId=? and isShow=? order by umOrder asc ", sysUser.getId(),1);
//			for (UserMenuEntity userMenuEntity : userMenuEntities) {
//				MenuEntity menuEntity = commonService.findUniqueByProperty(
//						MenuEntity.class, "id", userMenuEntity.getMenuId());
//
//				menus.add(menuEntity);
//			}
//
//			request.setAttribute("menus", menus);
//			if (menus.isEmpty()) {
//				map = new HashMap<>();
//				map.put("menuCode", "home");
//				menus = commonService.findListByProperty(MenuEntity.class, map);
//				request.setAttribute("menus", menus);
//			}
//			List<MenuEntity> ms=new ArrayList<>();
//			List<UserMenuEntity> userMenuEntities2 = commonService.findListByHql("from UserMenuEntity where userId=? order by umOrder asc ", sysUser.getId());
//			for (UserMenuEntity userMenuEntity : userMenuEntities2) {
//				MenuEntity menuEntity = commonService.findUniqueByProperty(MenuEntity.class, "id", userMenuEntity.getMenuId());
//				if (!menuEntity.getMenuCode().equals("home")) {
//					MenuEntity newMenuEntity=new MenuEntity();
//					String menuName = userMenuEntity.getIsShow()==0?"显示"+menuEntity.getMenuName()+"菜单":"隐藏"+menuEntity.getMenuName()+"菜单";
//					newMenuEntity.setMenuName(menuName);
//					newMenuEntity.setMenuCode(menuEntity.getMenuCode());
//					ms.add(newMenuEntity);
//				}
//			}
//			
//			request.setAttribute("ms", ms);
//			request.setAttribute("username", sysUser.getName());
//		} else {
//			map.put("menuCode", "home");
//			menus = commonService.findListByProperty(MenuEntity.class, map);
//			request.setAttribute("menus", menus);
//
//		}
//		List<SubmenuEntity> submenus = commonService.findListByProperty(SubmenuEntity.class,"pCode",menuCode);
//		request.setAttribute("submenus", submenus);
//		List<MQEntity> mqs = commonService.findListByProperty(MQEntity.class);
//		request.setAttribute("mqs", mqs);
//		
//	}
}
