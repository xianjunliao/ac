package com.ac.util.hibernate.impl;

import java.math.BigDecimal;

import com.ac.util.CriteriaQuery;
import com.ac.util.HqlGenerateUtil;
import com.ac.util.hibernate.HqlParse;

public class BigDecimalParseImpl implements HqlParse {

	public void addCriteria(CriteriaQuery cq, String name, Object value) {
		if (HqlGenerateUtil.isNotEmpty(value)){
			cq.eq(name, value);
		}
	}

	public void addCriteria(CriteriaQuery cq, String name, Object value, String beginValue, String endValue) {
		if (HqlGenerateUtil.isNotEmpty(beginValue)) {
			cq.ge(name, beginValue.contains(".") ? BigDecimal.valueOf(Double.parseDouble(beginValue)) : BigDecimal.valueOf(Long.parseLong(beginValue)));
		}
		if (HqlGenerateUtil.isNotEmpty(endValue)) {
			cq.le(name, beginValue.contains(".") ? BigDecimal.valueOf(Double.parseDouble(endValue)) : BigDecimal.valueOf(Long.parseLong(endValue)));
		}
		if (HqlGenerateUtil.isNotEmpty(value)) {
			cq.eq(name, value);
		}
	}

}
