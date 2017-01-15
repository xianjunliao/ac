package com.ac.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ac.util.CriteriaQuery;
import com.ac.util.SortDirection;


public interface BaseCommonDao {

	/**
	 * 根据id获取对象
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> entityClass, Serializable id);
	
	/**
	 * 获取唯一对象
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value);
	
	/**
	 * 获取唯一对象
	 * @param entityClass
	 * @param optionsMap
	 * @return
	 */
	public <T> T findUniqueByProperty(Class<T> entityClass, Map<String, Object> optionsMap);
	
	/**
	 * 获取对象集合并排序
	 * @author jzc
	 * @param entityClass
	 * @param sort
	 * @param order
	 * @return
	 */
	public <T> List<T> findListByProperty(Class<T> entityClass, String sort, SortDirection order);
	
	/**
	 * 获取对象集合
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> List<T> findListByProperty(Class<T> entityClass, String propertyName, Object value);
	
	/**
	 * 单条件获取总数
	 * @author jzc
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> Long getCountByProperty(Class<T> entityClass, String propertyName, Object value);
	
	/**
	 * 获取对象集合并排序
	 * @author jzc
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @param sort
	 * @param order
	 * @return
	 */
	public <T> List<T> findListByProperty(Class<T> entityClass, String propertyName, Object value, String sort, SortDirection order);
	
	/**
	 * 获取对象集合
	 * @param entityClass
	 * @param optionsMap
	 * @return
	 */
	public <T> List<T> findListByProperty(Class<T> entityClass, Map<String, Object> optionsMap);
	
	/**
	 * 使用hql获取唯一对象
	 * @param hql
	 * @param params
	 * @return
	 */
	public <T> T findUniqueByHql(String hql, Object... params);
	
	/**
	 * 使用sql获取唯一对象
	 * @param sql
	 * @param params
	 * @return
	 */
	public <T> T findUniqueBySql(String sql, Object... params);
	
	/**
	 * 使用hql获取集合
	 * @param hql
	 * @param params
	 * @return
	 */
	public <T> List<T> findListByHql(String hql, Object... params);
	
	/**
	 * 使用sql获取集合
	 * @param sql
	 * @param params
	 * @return
	 */
	public <T> List<T> findListBySql(String sql, Object... params);
	
	/**
	 * 使用sql获取集合
	 * @param sql
	 * @param params
	 * @return
	 */
	public <T> List<T> findListBySql(String sql, Class<T> entityClass, Object... params);
	
	/**
	 * 保存对象
	 * @param entity
	 * @return
	 */
	public <T> Serializable save(T entity);
	
	/**
	 * 批量保存对象
	 * @param entities
	 */
	public <T> void batchSave(List<T> entities);
	
	/**
	 * 根据传入的实体添加或更新对象
	 * @param entity
	 */
	public <T> void saveOrUpdate(T entity);
	
	/**
	 * 删除对象
	 * @param entity
	 */
	public <T> void delete(T entity);
	
	/**
	 * 批量删除对象
	 * @param entityClass
	 * @param ids
	 */
	public <T> void batchDelete(Class<T> entityClass, String[] ids);
	
	/**
	 * 根据id删除对象
	 * @param entityClass
	 * @param id
	 */
	public <T> void deleteEntityById(Class<T> entityClass, Serializable id);
	
	/**
	 * 删除多个对象
	 * @param entities
	 */
	public <T> void deleteEntities(List<T> entities);
	
	/**
	 * 使用hql删除对象
	 * @param hql
	 * @param params
	 */
	public <T> void deleteByHql(String hql, Object... params);
	
	/**
	 * 使用sql删除对象
	 * @param sql
	 * @param params
	 */
	public <T> void deleteBySql(String sql, Object... params);
	
	/**
	 * 更新对象
	 * @param entity
	 */
	public <T> void update(T entity);
	
	/**
	 * merge对象
	 * @param entity
	 */
	public <T> void merge(T entity);
	
	/**
	 * 使用hql更新对象
	 * @param hql
	 * @param params
	 */
	public <T> void updateByHql(String hql, Object... params);
	
	/**
	 * 使用sql更新对象
	 * @param sql
	 * @param params
	 */
	public <T> void updateBySql(String sql, Object... params);
	
	/**
	 * 分页查询
	 * @param offset
	 * @param limit
	 * @param hql
	 * @param optionsMap
	 * @return
	 */
	public  Map<String,Object> getPageData(int offset, int limit,String hql, Map<String, Object> optionsMap);
	
	/**
	 * 分页查询
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public Map<String,Object> getDataGrid(CriteriaQuery cq, boolean isOffset);
	
	/**
	 * 根据查询条件获取总数
	 * @author jzc
	 * @param cq
	 * @return
	 */
	public Long getCountByCriteriaQuery(CriteriaQuery cq);
}
