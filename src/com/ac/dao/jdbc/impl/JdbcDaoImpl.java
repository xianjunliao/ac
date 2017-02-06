package com.ac.dao.jdbc.impl ;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ac.dao.jdbc.JdbcDao;

@Repository
public class JdbcDaoImpl extends JdbcDaoSupport implements JdbcDao{

	@Override
	public List<Map<String, Object>> queryBySql(String sql) {
		return getJdbcTemplate().queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> queryBySql(String sql,Object[] params) {
		return getJdbcTemplate().queryForList(sql,params);
	}
    
	@Resource
	public void setJb(JdbcTemplate jb) {
	 super.setJdbcTemplate(jb);
   }
}
