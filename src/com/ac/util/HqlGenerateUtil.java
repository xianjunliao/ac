package com.ac.util;

import java.beans.PropertyDescriptor;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import com.ac.util.hibernate.HqlParse;
import com.ac.util.hibernate.impl.BigDecimalParseImpl;
import com.ac.util.hibernate.impl.DoubleParseImpl;
import com.ac.util.hibernate.impl.FloatParseImpl;
import com.ac.util.hibernate.impl.IntegerParseImpl;
import com.ac.util.hibernate.impl.LongParseImpl;
import com.ac.util.hibernate.impl.ShortParseImpl;
import com.ac.util.hibernate.impl.StringParseImpl;

public class HqlGenerateUtil {

	/** 时间查询符号 */
	private static final String BEGIN = "_begin";
	private static final String END = "_end";

	private static Map<String, HqlParse> map = new HashMap<String, HqlParse>();

	private static final SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	static {
		map.put("class java.lang.Integer", new IntegerParseImpl());
		map.put("class java.lang.Short", new ShortParseImpl());
		map.put("class java.lang.String", new StringParseImpl());
		map.put("class java.lang.Long", new LongParseImpl());
		map.put("class java.lang.Double", new DoubleParseImpl());
		map.put("class java.lang.Float", new FloatParseImpl());
		map.put("class java.math.BigDecimal", new BigDecimalParseImpl());
	}

	public static void installHql(CriteriaQuery cq, Object searchObj, Map<String, String[]> parameterMap){
		installHql(cq, searchObj, parameterMap, "");
	}
	
	public static void installHql(CriteriaQuery cq, Object searchObj, Map<String, String[]> parameterMap, String alias) {
		try {
			PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(searchObj);
			String aliasName, name, type;
			for (int i = 0; i < origDescriptors.length; i++) {
				aliasName = (alias.equals("") ? "" : alias + ".") + origDescriptors[i].getName();
				name = origDescriptors[i].getName();
				type = origDescriptors[i].getPropertyType().toString();

				if (judgedIsUselessField(name) || !PropertyUtils.isReadable(searchObj, name)) {
					continue;
				}
				// 添加 判断是否有区间值
				String beginValue = null;
				String endValue = null;
				if (parameterMap != null && parameterMap.containsKey(name + BEGIN)) {
					beginValue = parameterMap.get(name + BEGIN)[0].trim();
				}
				if (parameterMap != null && parameterMap.containsKey(name + END)) {
					endValue = parameterMap.get(name + END)[0].trim();
				}

				Object value = PropertyUtils.getSimpleProperty(searchObj, name);
				
				// 根据类型分类处理
				if (type.contains("class java.lang") || type.contains("class java.math")) {
					if(beginValue == null && endValue == null){
						if("".equals(value)||value==null) continue;
					}
					if (parameterMap == null) {
						map.get(type).addCriteria(cq, aliasName, value);
					} else {
						map.get(type).addCriteria(cq, aliasName, value, beginValue, endValue);
					}
				} else if ("class java.util.Date".equals(type)) {

					if (StringUtils.isNotBlank(beginValue)) {
						if (beginValue.length() == 19) {
							cq.ge(aliasName, time.parse(beginValue));
						} else if (beginValue.length() == 10) {
							cq.ge(aliasName, date.parse(beginValue));
						}
					}
					if (StringUtils.isNotBlank(endValue)) {
						if (endValue.length() == 19) {
							cq.le(aliasName, time.parse(endValue));
						} else if (endValue.length() == 10) {
							cq.le(aliasName, time.parse(endValue + " 23:59:59"));
						}
					}
					if (value != null && !"".equals(value)) {
						cq.eq(aliasName, value);
					}
				} else if (!isJavaClass(origDescriptors[i].getPropertyType())) {
					Object param = PropertyUtils.getSimpleProperty(searchObj, name);
					if (isNotEmpty(param) && itIsNotAllEmpty(param)) {
						cq.createAlias(aliasName, aliasName);
						installHql(cq, param, parameterMap, aliasName);
					}
				}
			}
			cq.add();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean judgedIsUselessField(String name) {
		return "class".equals(name) || "ids".equals(name) || "offset".equals(name) || "limit".equals(name) || "sort".equals(name) || "order".equals(name);
	}

	/**
	 * 判断是不是空
	 */
	public static boolean isNotEmpty(Object value) {
		return value != null && !"".equals(value);
	}

	/**
	 * 判断这个类是不是java自带的类
	 * 
	 * @param clazz
	 * @return
	 */
	private static boolean isJavaClass(Class<?> clazz) {
		boolean isBaseClass = false;
		if (clazz.isArray()) {
			isBaseClass = false;
		} else if (clazz.isPrimitive() || clazz.getPackage() == null || clazz.getPackage().getName().equals("java.lang") || clazz.getPackage().getName().equals("java.math")
				|| clazz.getPackage().getName().equals("java.util")) {
			isBaseClass = true;
		}
		return isBaseClass;
	}

	/**
	 * 判断对象里是不是都为空
	 * 
	 * @param param
	 * @return
	 */
	private static boolean itIsNotAllEmpty(Object param) {
		boolean isNotEmpty = false;
		try {
			PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(param);
			String name;
			for (int i = 0; i < origDescriptors.length; i++) {
				name = origDescriptors[i].getName();
				if ("class".equals(name) || !PropertyUtils.isReadable(param, name)) {
					continue;
				}
				if (Map.class.isAssignableFrom(origDescriptors[i].getPropertyType())) {
					Map<?, ?> map = (Map<?, ?>) PropertyUtils.getSimpleProperty(param, name);
					if (map != null && map.size() > 0) {
						isNotEmpty = true;
						break;
					}
				} else if (Collection.class.isAssignableFrom(origDescriptors[i].getPropertyType())) {
					Collection<?> c = (Collection<?>) PropertyUtils.getSimpleProperty(param, name);
					if (c != null && c.size() > 0) {
						isNotEmpty = true;
						break;
					}
				} else if (isNotEmpty(PropertyUtils.getSimpleProperty(param, name))) {
					isNotEmpty = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isNotEmpty;
	}
}
