package com.ac.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public interface JdbcDao {
	 public List<Map<String,Object>> queryBySql(String sql);
	 public List<Map<String,Object>> queryBySql(String sql,Object[]params);
	 public JdbcTemplate getJdbcTemplate();
}
