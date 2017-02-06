package com.ac.util.hibernate.impl;

import com.ac.util.CriteriaQuery;
import com.ac.util.HqlGenerateUtil;
import com.ac.util.hibernate.HqlParse;

public class DoubleParseImpl implements HqlParse {

	public void addCriteria(CriteriaQuery cq, String name, Object value) {
		if (HqlGenerateUtil.isNotEmpty(value)) {
			cq.eq(name, value);
		}
	}

	public void addCriteria(CriteriaQuery cq, String name, Object value, String beginValue, String endValue) {
		if (HqlGenerateUtil.isNotEmpty(beginValue)) {
			cq.ge(name, Double.parseDouble(beginValue));
		}
		if (HqlGenerateUtil.isNotEmpty(endValue)) {
			cq.le(name, Double.parseDouble(endValue));
		}
		if (HqlGenerateUtil.isNotEmpty(value)) {
			cq.eq(name, value);
		}
	}

}
