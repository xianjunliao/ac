package com.ac.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;


public class CriteriaQuery implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public CriteriaQuery() {}
	
	public CriteriaQuery(Class<?> entityClass,DataGrid dg) {
		this.detachedCriteria = DetachedCriteria.forClass(entityClass);
		this.dataGrid=dg;
	}
	
	private DetachedCriteria detachedCriteria;
	
	private DataGrid dataGrid;
	
	private CriterionList criterionList=new CriterionList();//自定义查询条件集合
	
	private Map<String, Object> ordermap=new HashMap<String, Object>();//排序字段
	
	private List<String> alias = new ArrayList<String>();//保存创建的aliasName 防止重复创建

	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}

	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}

	public DataGrid getDataGrid() {
		return dataGrid;
	}

	public void setDataGrid(DataGrid dataGrid) {
		this.dataGrid = dataGrid;
	}
	
	public CriterionList getCriterionList() {
		return criterionList;
	}

	public void setCriterionList(CriterionList criterionList) {
		this.criterionList = criterionList;
	}
	
	public Map<String, Object> getOrdermap() {
		return ordermap;
	}

	public void setOrdermap(Map<String, Object> ordermap) {
		this.ordermap = ordermap;
	}
	
	public void createAlias(String name, String value) {
		if(!alias.contains(name)){
			detachedCriteria.createAlias(name, value);
			alias.add(name);
		}
	}
	
	/**
	 * 加载条件
	 */
	public void add(Criterion c) {
		detachedCriteria.add(c);
	}

	/**
	 * 加载条件
	 */
	public void add() {
		for (int i = 0; i < getCriterionList().size(); i++) {
			add(getCriterionList().getParas(i));
		}
		getCriterionList().removeAll(getCriterionList());
	}

	public void addOrder(String ordername, SortDirection ordervalue) {
		ordermap.put(ordername,ordervalue);
	}
	
	public void setOrder(Map<String, Object> map) {
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			judgecreateAlias(entry.getKey());
			if (SortDirection.asc.equals(entry.getValue())) {
				detachedCriteria.addOrder(Order.asc(entry.getKey()));
			} else {
				detachedCriteria.addOrder(Order.desc(entry.getKey()));
			}
		}
	}
	
	public void judgecreateAlias(String entitys) {
		String[] aliass = entitys.split("\\.");
		for (int i = 0 ;i<aliass.length-1;i++){
			createAlias(aliass[i], aliass[i]);
		}
	}
	
	public void eq(String keyname, Object keyvalue) {
		if (keyvalue != null && keyvalue != "") {
			criterionList.addPara(Restrictions.eq(keyname, keyvalue));
		}
	}

	public void notEq(String keyname, Object keyvalue) {
		if (keyvalue != null && keyvalue != "") {
			criterionList.addPara(Restrictions.ne(keyname, keyvalue));
		}
	}
	
	public void like(String keyname, Object keyvalue) {
		if (keyvalue != null && keyvalue != "") {
			criterionList.addPara(Restrictions.like(keyname, keyvalue).ignoreCase());
		}
	}

	public void gt(String keyname, Object keyvalue) {
		if (keyvalue != null && keyvalue != "") {
			criterionList.addPara(Restrictions.gt(keyname, keyvalue));
		}
	}

	public void lt(String keyname, Object keyvalue) {
		if (keyvalue != null && keyvalue != "") {
			criterionList.addPara(Restrictions.lt(keyname, keyvalue));
		}
	}

	public void le(String keyname, Object keyvalue) {
		if (keyvalue != null && keyvalue != "") {
			criterionList.addPara(Restrictions.le(keyname, keyvalue));
		}
	}

	public void ge(String keyname, Object keyvalue) {
		if (keyvalue != null && keyvalue != "") {
			criterionList.addPara(Restrictions.ge(keyname, keyvalue));
		}
	}

	public void in(String keyname, Object[] keyvalue) {
		if (keyvalue != null && keyvalue[0] != "") {
			criterionList.addPara(Restrictions.in(keyname, keyvalue));
		}
	}

	public void isNull(String keyname) {
		criterionList.addPara(Restrictions.isNull(keyname));
	}

	public void isNotNull(String keyname) {
		criterionList.addPara(Restrictions.isNotNull(keyname));
	}

	public void sql(String sql) {
		criterionList.addPara(Restrictions.sqlRestriction(sql));
	}

	public void sql(String sql, Object[] objects, Type[] type) {
		criterionList.addPara(Restrictions.sqlRestriction(sql, objects, type));
	}

	public void sql(String sql, Object objects, Type type) {
		criterionList.addPara(Restrictions.sqlRestriction(sql, objects, type));
	}
}
