package com.ac.util;

import java.util.HashMap;
import java.util.Map;

public class DataGrid {

	// 当前页
	private int offset;

	// 每页显示记录数
	private int limit;

	// 排序字段名
	private String sort;

	// 按什么排序(asc,desc)
	private SortDirection order;
	
	private Map<String,String>params=new HashMap<String,String>();

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public SortDirection getOrder() {
		return order;
	}

	public void setOrder(SortDirection order) {
		this.order = order;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
}
