package com.ac.util.hibernate;

import com.ac.util.CriteriaQuery;

public interface HqlParse {

	public void addCriteria(CriteriaQuery cq, String name, Object value);

	public void addCriteria(CriteriaQuery cq, String name, Object value, String beginValue, String endValue);
}
