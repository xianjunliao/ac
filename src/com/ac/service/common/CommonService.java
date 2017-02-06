package com.ac.service.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ac.util.CriteriaQuery;
import com.ac.util.DataGrid;
import com.ac.util.SortDirection;

public interface CommonService {

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
	 * 获取对象集合
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> findListByProperty(Class<T> entityClass);
	
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
	 * 获取总数
	 * @author jzc
	 * @param entityClass
	 * @param ids
	 * @return
	 */
	public <T> Long getCountByProperty(Class<T> entityClass, String[] ids);
	
	/**
	 * 获取总数
	 * @author jzc
	 * @param entityClass
	 * @param whereParams
	 * @return
	 */
	public <T> Long getCountByProperty(Class<T> entityClass, LinkedHashMap<String, Object> whereParams);
	
	/**
	 * 获取对象集合并排序
	 * @author jzc
	 * @param entityClassupdateBySqlInIds
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
	 *  通过参数获取数据条数
	 * @author jzc
	 * @param entityClass
	 * @param whereParams
	 * @return 
	 * @return
	 */
	public <T> long getCountByWhereParams(Class<T> entityClass, LinkedHashMap<String, Object> whereParams);
	/**
	 * 保存对象
	 * @param entity
	 * @return
	 */
	public <T> Serializable save(T entity);
	
	/**
	 * 批量保存
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
	 * 通过in ids更新
	 * @author jzc
	 * @param sql 例如: update table_name set type = 1 where id in (:ids)
	 * @param ids
	 */
	public <T> void updateBySqlInIds(String sql, Object[] ids);
	
	/**
	 * 通过in ids更新
	 * @author jzc
	 * @param hql 例如: update ClassEntity set type = 1 where id in (:ids)
	 * @param ids
	 */
	public <T> void updateByHqlInIds(String hql, String[] ids);
	
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
	 * 分页查询
	 * @param offset
	 * @param limit
	 * @param hql
	 * @param optionsMap
	 * @return
	 */
	public Map<String,Object> getPageData(int offset, int limit,String hql, Map<String, Object> optionsMap);
	
	/**
	 * 分页查询
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public Map<String,Object> getDataGrid(CriteriaQuery cq);
	
	/**
	 * 根据查询条件获取总数
	 * @author jzc
	 * @param cq
	 * @return
	 */
	public Long getCountByCriteriaQuery(CriteriaQuery cq);
	
	/**
	 * 不分页查询
	 * @author jzc
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public <T> List<T> getDataGridNoPagination(CriteriaQuery cq);
	
	/**
	 * 分页查询
	 * @param sql
	 * @param listParams
	 * @return
	 */
	public Map<String,Object>getDataGrid(String sql,List<String> listParams,DataGrid  datagrid);

	/**
	 * 更新序列
	 * @param seqName
	 * @param seq
	 */
	public void updateSeqByName(String seqName, Integer seq);

	/**
	 * 调用存储过程
	 * @author jzc
	 * @param sql 例如: {call test_proc(?,?)} (必须要{}号)
	 * @param params 的key规则为inName(输入参数带上in前缀),outName(输出参数带上out前缀)
	 */
	public String callProcedures(String sql, LinkedHashMap<String, Object> params);
	
	
	public List<Map<String,Object>> jdbcQuery(String sql,Object...params)throws Exception;
	/**
	 *  通过参数获取数据
	 * @author lxj
	 * @param entityClass
	 * @param whereParams
	 * @return
	 */
	public <T> List<T> getListByPts(Class<T> entityClass, LinkedHashMap<String, Object> whereParams);
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
	 * 执行*.sql文件中sql获取Object, 
	 * 参数和sql中的?必须一一对应
	 * @author jzc
	 * @param sqlName
	 * @param params
	 * @return
	 */
	public Object sqlGetObject(String sqlName, Object... params);
	
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
	 * 执行*.sql文件中sql获取BigDecimal, 
	 * 参数和sql中的?必须一一对应
	 * @author jzc
	 * @param sqlName
	 * @param params
	 * @return
	 */
	public BigDecimal sqlGetBigDecimal(String sqlName, Object... params);
	
}
