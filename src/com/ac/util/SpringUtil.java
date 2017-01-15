package com.ac.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * 
 * @author wy
 *
 */
public class SpringUtil implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringUtil.applicationContext = applicationContext;
	}

	
}
