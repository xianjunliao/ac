package com.ac.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ac.base.BaseController;
import com.ac.entity.MenuEntity;
import com.ac.serice.IndexService;
import com.ac.util.json.JSONArray;
import com.ac.util.json.JSONObject;

@Controller
@RequestMapping()
public class IndexController extends BaseController {

	@Resource
	private IndexService indexService;

	// @RequestMapping("/")
	// public String goTask(HttpServletRequest request) {
	// commonMapping("home",indexService,request);
	// return "/index";
	// }
	@RequestMapping("/")
	public String goPage(HttpServletRequest request) {

		
		List<MenuEntity> all =indexService.getAll();
		StringBuffer buffer=new StringBuffer();
		buffer.append("{");
		int i=0;
		for (MenuEntity menuEntity : all) {
			buffer.append("'");
			buffer.append(menuEntity.getCode());
			buffer.append("'");
			buffer.append(":{");
			buffer.append("name:'"+menuEntity.getName());
			buffer.append("',type:'"+menuEntity.getType());
			buffer.append("','icon-class':'"+menuEntity.getColour());
			buffer.append("'}");
			i++;
			if(i<all.size()){
				buffer.append(",");
				buffer.append("\n");
			}
		
		}
		buffer.append("};");
		System.out.println(buffer.toString());
		
		
		request.setAttribute("menu", all);
		request.setAttribute("menuJson", buffer.toString());
		return "memory_tree/index";
	}

}
