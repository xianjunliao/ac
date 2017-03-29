package com.ac.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ac.base.BaseController;
import com.ac.entity.Menu1Entity;
import com.ac.entity.MenuEntity;
import com.ac.entity.PoolEntity;
import com.ac.serice.IndexService;

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
		List<Menu1Entity> menu1All = indexService.getMenu1All();
		List<PoolEntity> poolAll = indexService.getPoolAll();
		StringBuffer buffer = getMenuStr(all);
		StringBuffer buffer1 = getMenu1Str(menu1All);
		StringBuffer buffer2 = getPoolStr(poolAll);
		System.out.println(buffer.toString());
		System.out.println(buffer1.toString());
		System.out.println(buffer2.toString());
		request.setAttribute("menu", all);
		request.setAttribute("menuJson", buffer.toString());
		request.setAttribute("menu1Json", buffer1.toString());
		request.setAttribute("menu2Json", buffer2.toString());
		return "memory_tree/index";
	}

	private StringBuffer getMenuStr(List<MenuEntity> all) {
		StringBuffer buffer=new StringBuffer();
		buffer.append("{");
		int i=0;
		for (MenuEntity menuEntity : all) {
			buffer.append("'");
			buffer.append(menuEntity.getCode());
			buffer.append("'");
			buffer.append(":{");
			buffer.append("code:'"+menuEntity.getCode());
			buffer.append("',name:'"+menuEntity.getName());
			buffer.append("',type:'"+menuEntity.getType());
			buffer.append("','icon-class':'"+menuEntity.getColour());
			buffer.append("'}");
			i++;
			if(i<all.size()){
				buffer.append(",");
				buffer.append("\n");
			}
		
		}
		buffer.append("}");
		return buffer;
	}
	private StringBuffer getMenu1Str(List<Menu1Entity> all) {
		StringBuffer buffer=new StringBuffer();
		buffer.append("{");
		int i=0;
		for (Menu1Entity menuEntity : all) {
			buffer.append("'");
			buffer.append(menuEntity.getCode());
			buffer.append("'");
			buffer.append(":{");
			buffer.append("code:'"+menuEntity.getCode());
			buffer.append("',menuCode:'"+menuEntity.getMenuCode());
			buffer.append("',name:'"+menuEntity.getName());
			buffer.append("',type:'"+menuEntity.getType());
			buffer.append("',colour:'"+menuEntity.getColour());
			buffer.append("'}");
			i++;
			if(i<all.size()){
				buffer.append(",");
				buffer.append("\n");
			}
		
		}
		buffer.append("}");
		return buffer;
	}
	
	private StringBuffer getPoolStr(List<PoolEntity> all) {
		StringBuffer buffer=new StringBuffer();
		buffer.append("{");
		int i=0;
		for (PoolEntity entity : all) {
			buffer.append("'");
			buffer.append(entity.getCode());
			buffer.append("'");
			buffer.append(":{");
			buffer.append("code:'"+entity.getCode());
			buffer.append("',menuCode:'"+entity.getMenuCode());
			buffer.append("',menu1Code:'"+entity.getMenu1Code());
			buffer.append("',name:'"+entity.getName());
			buffer.append("',baiduType:'"+entity.getBaiduType());
			buffer.append("',type:'"+entity.getType());
			buffer.append("'}");
			i++;
			if(i<all.size()){
				buffer.append(",");
				buffer.append("\n");
			}
		
		}
		buffer.append("}");
		return buffer;
	}
}
