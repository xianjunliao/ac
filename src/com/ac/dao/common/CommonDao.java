package com.ac.dao.common;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

import com.ac.util.DataGrid;

public interface CommonDao extends BaseCommonDao{
	
	/**
	 * 通过in ids获取数据
	 * @author jzc
	 * @param entityClass
	 * @param ids
	 * @return
	 */
	public <T> List<T> getListByIds(Class<T> entityClass, String[] ids);
	
	/**
	 *  通过参数获取数据
	 * @author jzc
	 * @param entityClass
	 * @param whereParams
	 * @return
	 */
	public <T> List<T> getListByProperty(Class<T> entityClass, LinkedHashMap<String, Object> whereParams);
	
	/**
	 *  通过参数获取数据
	 * @author jzc
	 * @param entityClass
	 * @param whereParams
	 * @return
	 */
	public <T> List<T> getListByPts(Class<T> entityClass, LinkedHashMap<String, Object> whereParams);
	
	/**
	 *  通过参数获取数据条数
	 * @author jzc
	 * @param entityClass
	 * @param whereParams
	 * @return 
	 * @return
	 */
	public <T> long getCountByWhereParams(Class<T> entityClass, LinkedHashMap<String, Object> whereParams);
	
	/**
	 * 通过in ids获取数据
	 * @author jzc
	 * @param sql 例如: select * from table_name where id in (:ids)
	 * @param ids
	 */
	public <T> List<T> getListBySqlInIds(String sql, String[] ids);
	
	/**
	 * 通过in ids获取数据
	 * @author jzc
	 * @param hql 例如: from ClassEntity where id in (:ids)
	 * @param ids
	 * @return
	 */
	public <T> List<T> getListByHqlInIds(String hql, String[] ids);
	
	/**
	 * 通过in ids更新
	 * @author jzc
	 * @param sql 例如: update table_name set type = 1 where id in (:ids)
	 * @param ids
	 */
	public void updateBySqlInIds(String sql, Object[] ids);
	
	/**
	 * 通过in ids更新
	 * @author jzc
	 * @param hql 例如: update ClassEntity set type = 1 where id in (:ids)
	 * @param ids
	 */
	public void updateByHqlInIds(String hql, String[] ids);
	
	/**
	 * 调用存储过程
	 * @author jzc
	 * @param sql 例如: {call test_proc(?,?)} (必须要{}号)
	 * @param params 的key规则为inName(输入参数带上in前缀),outName(输出参数带上out前缀)
	 */
	public String callProcedures(String sql, LinkedHashMap<String, Object> params);
	
	/**
	 * 通过in ids更新
	 * @author jzc
	 * @param entityClass
	 * @param setParams
	 * @param ids
	 */
	public <T> void updateByProperty(Class<T> entityClass, LinkedHashMap<String, Object> setParams, String[] ids);
	
	/**
	 * 通过参数更新
	 * @author jzc
	 * @param entityClass
	 * @param setParams
	 * @param whereParams
	 */
	public <T> void updateByProperty(Class<T> entityClass, LinkedHashMap<String, Object> setParams, LinkedHashMap<String, Object> whereParams);
	
	/**
	 * 通过in ids获取总数
	 * @author jzc
	 * @param entityClass
	 * @param ids
	 * @return
	 */
	public <T> Long getCountByProperty(Class<T> entityClass, String[] ids);
	
	/**
	 * 通过参数获取总数
	 * @author jzc
	 * @param entityClass
	 * @param whereParams
	 * @return
	 */
	public <T> Long getCountByProperty(Class<T> entityClass, LinkedHashMap<String, Object> whereParams);
	
	/**
	 * 获取mailCode数组
	 * @author jzc
	 * @param entityClass
	 * @param ids
	 * @param whereParams
	 * @return
	 */
	public Object[] getMailCode(Class<?> entityClass, String[] ids, LinkedHashMap<String, Object> whereParams);
	
	/**
	 * 执行*.sql文件中sql获取count, 
	 * 参数和sql中的?必须一一对应
	 * @author jzc
	 * @param sqlName
	 * @param params
	 * @return
	 */
	public Long sqlGetCount(String sqlName, Object... params);
	
	/**
	 * 执行*.sql文件中sql获取list, 
	 * 参数和sql中的?必须一一对应,
	 * dataGrid为空不分页
	 * @author jzc
	 * @param sqlName
	 * @param params
	 * @return
	 */
	public <T> List<T> sqlGetList(String sqlName, DataGrid dataGrid, Object... params);
	
	/**
	 * 执行*.sql文件中sql获取Object, 
	 * 参数和sql中的?必须一一对应
	 * @author jzc
	 * @param sqlName
	 * @param params
	 * @return
	 */
	public Object sqlGetObject(String sqlName, Object... params);
	
	/**
	 * 执行*.sql文件中sql获取BigDecimal, 
	 * 参数和sql中的?必须一一对应
	 * @author jzc
	 * @param sqlName
	 * @param params
	 * @return
	 */
	public BigDecimal sqlGetBigDecimal(String sqlName, Object... params);
	
}

