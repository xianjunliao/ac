package com.ac.controller.ac;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ac.base.BaseController;
import com.ac.entity.ACBooksEntity;
import com.ac.entity.ACTempEntity;
import com.ac.entity.MenuEntity;
import com.ac.entity.SequenceEntity;
import com.ac.entity.SubmenuEntity;
//import com.ac.entity.SequenceEntity;
import com.ac.entity.UserEntity;
import com.ac.entity.UserMenuEntity;
import com.ac.service.ac.AcService;

//import com.ac.util.DateUtils;

@Controller
@RequestMapping()
public class AcController extends BaseController {

	@Resource
	public AcService acService;

	@RequestMapping("/accounting")
	public String accounting(HttpServletRequest request) {
		UserEntity sysUser = getSysUser(request);
		List<MenuEntity> menus =new ArrayList<>();
		Map<String, Object> map=new HashMap<>();
		if (sysUser != null) {
			map=new HashMap<>();
			map.put("userId", sysUser.getId());
			map.put("isShow", 1);
			List<UserMenuEntity> userMenuEntities = acService.findListByProperty(UserMenuEntity.class,map);
			for (UserMenuEntity userMenuEntity : userMenuEntities) {
				MenuEntity menuEntity = acService.findUniqueByProperty(MenuEntity.class, "id", userMenuEntity.getMenuId());
				menus.add(menuEntity);
			}
			
			request.setAttribute("menus",menus);
			if (menus.isEmpty()) {
				map=new HashMap<>();
				map.put("type", 0);
				map.put("isShow", 1);
				 menus = acService.findListByProperty(MenuEntity.class,map);
				 request.setAttribute("menus",menus);
			}
			request.setAttribute("username", sysUser.getName());
		}else{
			map.put("type", 0);
			map.put("isShow", 1);
			
		    menus = acService.findListByProperty(MenuEntity.class,map);
			request.setAttribute("menus",menus);
			
		}
		
		MenuEntity menuEntity = acService.findUniqueByProperty(MenuEntity.class, "menuCode", "home");
		List<SubmenuEntity> submenus = acService.findListByProperty(SubmenuEntity.class, "pId", menuEntity.getId());
		request.setAttribute("submenus",submenus);
		return "ac/ac";
	}

	@RequestMapping("/addItem")
	public String addItem(HttpServletRequest request) {
		UserEntity sysUser = getSysUser(request);
		request.setAttribute("username", sysUser.getName());
		List<ACTempEntity> tempEntities = acService.findListByProperty(
				ACTempEntity.class, "userId", sysUser.getId());
		request.setAttribute("temps", tempEntities);
		return "ac/addItem";
	}

	@RequestMapping("/books")
	public String goBooks(HttpServletRequest request) {

		UserEntity sysUser = getSysUser(request);
		request.setAttribute("username", sysUser.getName());
		List<ACBooksEntity> booksEntities = acService.findListByProperty(
				ACBooksEntity.class, "userId", sysUser.getId());
		for (ACBooksEntity acBooksEntity : booksEntities) {
			ACTempEntity tempEntity = acService.findUniqueByProperty(
					ACTempEntity.class, "id", acBooksEntity.getTempTxId());
			acBooksEntity.setTxName(tempEntity.getTxName());

		}

		List<ACTempEntity> tempEntities = acService.findListByProperty(
				ACTempEntity.class, "userId", sysUser.getId());

		request.setAttribute("temps", tempEntities);
		if (booksEntities.size() > 0) {
			request.setAttribute("list", booksEntities);
		}
		return "ac/ac";
	}

	@RequestMapping("/addBook")
	@ResponseBody
	public int addBook(String array, HttpServletRequest request)
			throws ParseException {
		acService.deleteByHql(
				"DELETE ACBooksEntity where acDate=to_date(?,'yyyy-MM-dd')",
				new Date());
		UserEntity sysUser = getSysUser(request);
		try {
			Map<String, Double> map = new HashMap<>();
			String[] split = array.split(",");
			for (int i = 0; i < split.length; i++) {
				String key = split[i].split(":")[0];
				String value = split[i].split(":")[1];
				map.put(key, Double.valueOf(value));
				ACBooksEntity acBooksEntity = new ACBooksEntity();
				acBooksEntity.setAcAmount(Double.valueOf(value));
				acBooksEntity.setAcCreateTime(new Date());
				acBooksEntity.setAcDate(new Date());
				acBooksEntity.setTempTxId(Integer.parseInt(key));
				acBooksEntity.setUserId(sysUser.getId());
				acService.save(acBooksEntity);
			}
			return 0;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@RequestMapping("/addTemp")
	@ResponseBody
	public int addTemp(String txName, String txType, HttpServletRequest request) {
		SequenceEntity sequenceEntity = acService.findUniqueByProperty(
				SequenceEntity.class, "name", "AC_TEMP");
		Integer currentValue2 = sequenceEntity.getCurrentValue();
		if (sequenceEntity == null || currentValue2 == null) {
			currentValue2 = 0;
		}
		UserEntity sysUser = getSysUser(request);
		Integer currentValue = currentValue2 + 1;
		ACTempEntity acTempEntity = new ACTempEntity();
		acTempEntity.setId(currentValue.intValue());
		acTempEntity.setTxName(txName);
		acTempEntity.setTxType(Integer.valueOf(txType));
		acTempEntity.setUserId(sysUser.getId());
		acTempEntity.setCreateTime(new Date());
		acService.save(acTempEntity);
		sequenceEntity.setCurrentValue(currentValue);
		acService.update(sequenceEntity);
		return 0;

	}

	@RequestMapping("/deleteTx")
	public String addTemp(String id, HttpServletRequest request) {
		acService.deleteBySql("delete from AC_TEMP where id=?",
				Integer.parseInt(id));
		return "ac/ac";

	}
}
