package com.ac.controller.book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.ac.entity.SequenceEntity;
import com.ac.entity.UserEntity;
import com.ac.service.book.BookService;
import com.ac.util.DateUtils;

@Controller
@RequestMapping("book")
public class BookController extends BaseController {

	@Resource
	public BookService bookService;

	@RequestMapping("/books")
	public String goBooks(HttpServletRequest request) {

		UserEntity sysUser = getSysUser(request);
		request.setAttribute("username", sysUser.getName());
		request.setAttribute("newDate",DateUtils.getCurrentDate ());
		List<ACBooksEntity> booksEntities =bookService.findListByProperty(ACBooksEntity.class, "userId", sysUser.getId());
		for (ACBooksEntity acBooksEntity : booksEntities) {
			ACTempEntity tempEntity = bookService.findUniqueByProperty(ACTempEntity.class, "id", acBooksEntity.getTempTxId());
			acBooksEntity.setTxName(tempEntity.getTxName());
			
		} 
		
		List<ACTempEntity> tempEntities = bookService.findListByProperty(ACTempEntity.class, "userId", sysUser.getId());
	
		request.setAttribute("temps", tempEntities);
		if (booksEntities.size() > 0) {
			request.setAttribute("listDate",DateUtils.getYYYYMMDD(booksEntities.get(0).getAcDate()));
			request.setAttribute("list", booksEntities);
		}
		return "book/books";
	}

	@RequestMapping("/addBook")
	@ResponseBody
	public int addBook(String array, HttpServletRequest request) throws ParseException {
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd" );
		Date date = sdf.parse(DateUtils.getCurrentDate());
		bookService.deleteByHql("DELETE ACBooksEntity where acDate=?", date);
		UserEntity sysUser = getSysUser(request);
		try {
			Map<String, Double> map=new HashMap<>();
			String[] split = array.split(",");
			for (int i = 0; i < split.length; i++) {
				String key = split[i].split(":")[0];
				String value = split[i].split(":")[1];
				map.put(key, Double.valueOf(value));
				ACBooksEntity acBooksEntity=new ACBooksEntity();
				acBooksEntity.setAcAmount(Double.valueOf(value));
				acBooksEntity.setAcCreateTime(new Date());
				acBooksEntity.setAcDate(new Date());
				acBooksEntity.setTempTxId(Integer.parseInt(key));
				acBooksEntity.setUserId(sysUser.getId());
				bookService.save(acBooksEntity);
			}
			return 0;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return 0;
	}
	@RequestMapping("/addTemp")
	@ResponseBody 
	public int addTemp(String txName,String txType,HttpServletRequest request){
		SequenceEntity sequenceEntity = bookService.findUniqueByProperty(SequenceEntity.class, "name", "AC_TEMP");
         System.out.println(txName);
		UserEntity sysUser = getSysUser(request);
		Integer currentValue = sequenceEntity.getCurrentValue()+1;
		ACTempEntity acTempEntity=new ACTempEntity();
		acTempEntity.setId(currentValue.intValue());
		acTempEntity.setTxName(txName);
		acTempEntity.setTxType(Integer.valueOf(txType));
		acTempEntity.setUserId(sysUser.getId());
		acTempEntity.setCreateTime(new Date());
		bookService.save(acTempEntity);
		sequenceEntity.setCurrentValue(currentValue);
		bookService.update(sequenceEntity);
		return 0;
		
	}
	
	
	@RequestMapping("/deleteTx")
	public String addTemp(String id,HttpServletRequest request){
		bookService.deleteBySql("delete from AC_TEMP where id=?", Integer.parseInt(id));
		return "book/books";
		
	}
}
