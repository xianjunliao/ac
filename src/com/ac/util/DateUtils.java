package com.ac.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat dateFormat_short = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat yyMMdd = new SimpleDateFormat("yyMMdd");
	public static final SimpleDateFormat MMdd = new SimpleDateFormat("MMdd");
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat timeFormat_short = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat dateFormatyyyyMM = new SimpleDateFormat("yyyy-MM");

	public static String getYyMmDd(Date date) {
		return yyMMdd.format(date);
	}

	public static String getYyMmDdHMS(Date date) {
		return timeFormat.format(date);
	}

	public static String getMmDd() {
		return MMdd.format(new Date());
	}

	public static String getCurrentDate() {
		return dateFormat.format(new Date());
	}
	public static String getYYYYMMDD(Date date) {
		return dateFormat.format(date);
	}
	public static String getCurrentDateShort() {
		return dateFormat_short.format(new Date());
	}

	public static String getCurrentTime() {
		return timeFormat.format(new Date());
	}
	
	public static String getCurrentTimeShort() {
		return timeFormat_short.format(new Date());
	}
	
	/**
	 * 两个时间相减天数
	 */
	public static BigDecimal getSubtractDay(Date startDate, Date endDate) {
		long day = (endDate.getTime()-startDate.getTime())/(24*60*60*1000);
		return BigDecimal.valueOf(day);
	}

	public static void main(String[] args) {
		
		
	}
}