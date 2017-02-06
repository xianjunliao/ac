package com.ac.dao.common.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.util.Assert;

import com.ac.dao.common.BaseCommonDao;
import com.ac.util.CriteriaQuery;
import com.ac.util.DataGrid;
import com.ac.util.SortDirection;


@SuppressWarnings({ "unchecked", "hiding" })
public class BaseCommonDaoImpl<T, PK extends Serializable> implements BaseCommonDao {

	@Resource
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private <T> Criteria createCriteria(Class<T> entityClass, Map<String, Object> optionsMap) {
		List<String> alias = new ArrayList<String>();
		Criteria criteria = getSession().createCriteria(entityClass);
		if (optionsMap != null) {
			for (Entry<String, Object> entry : optionsMap.entrySet()) {
				Assert.hasText(entry.getKey());
				if (entry.getKey().contains(".")) {
					String aliasName = entry.getKey().substring(0, entry.getKey().indexOf("."));
					if (!alias.contains(aliasName)) {
						alias.add(aliasName);
						criteria.createAlias(aliasName, aliasName);
					}
				}
			}
			criteria.add(Restrictions.allEq(optionsMap));
		}
		return criteria;
	}
	
	private <T> Criteria createCriteria(Class<T> entityClass, String propertyName, Object value) {
		Assert.hasText(propertyName);
		Criteria criteria = getSession().createCriteria(entityClass);
		if (propertyName.contains(".")) {
			String aliasName = propertyName.substring(0, propertyName.indexOf("."));
			criteria.createAlias(aliasName, aliasName);
		}
		criteria.add(Restrictions.eq(propertyName, value));
		return criteria;
	}

	/**
	 * 组装参数
	 * 
	 * @param query
	 * @param params
	 * @return
	 */
	protected Query querySetParams(Query query, Object... params) {
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query;
	}

	@Override
	public <T> T get(Class<T> entityClass, Serializable id) {
		return (T) getSession().get(entityClass, id);
	}

	@Override
	public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value) {
		return (T) createCriteria(entityClass, propertyName, value).uniqueResult();
	}

	@Override
	public <T> T findUniqueByProperty(Class<T> entityClass, Map<String, Object> optionsMap) {
		return (T) createCriteria(entityClass, optionsMap).uniqueResult();
	}
	
	@Override
	public <T> List<T> findListByProperty(Class<T> entityClass, String sort, SortDirection order) {
		Assert.hasText(sort);
		Criteria criteria = getSession().createCriteria(entityClass);
		if (sort.contains(".")) {
			String aliasName = sort.substring(0, sort.indexOf("."));
			criteria.createAlias(aliasName, aliasName);
		}
		if (order.name().equals("asc")) {
			criteria.addOrder(Order.asc(sort));
		} else {
			criteria.addOrder(Order.desc(sort));
		}

		return (List<T>) criteria.list();
	}

	@Override
	public <T> List<T> findListByProperty(Class<T> entityClass, String propertyName, Object value) {
		return (List<T>) createCriteria(entityClass, propertyName, value).list();
	}
	
	public <T> Long getCountByProperty(Class<T> entityClass, String propertyName, Object value){
		return (Long) createCriteria(entityClass, propertyName, value).setProjection(Projections.rowCount()).uniqueResult();
	}
	
	@Override
	public <T> List<T> findListByProperty(Class<T> entityClass, String propertyName, Object value, String sort, SortDirection order) {
		Assert.hasText(sort);
		Criteria criteria = createCriteria(entityClass, propertyName, value);
		if (order.name().equals("asc")) {
			criteria.addOrder(Order.asc(sort));
		} else {
			criteria.addOrder(Order.desc(sort));
		}
		return (List<T>) criteria.list();
	}

	@Override
	public <T> List<T> findListByProperty(Class<T> entityClass, Map<String, Object> optionsMap) {
		return (List<T>) createCriteria(entityClass, optionsMap).list();
	}

	@Override
	public <T> T findUniqueByHql(String hql, Object... params) {
		Query query = getSession().createQuery(hql);
		return (T) querySetParams(query, params).uniqueResult();
	}

	@Override
	public <T> T findUniqueBySql(String sql, Object... params) {
		Query query = getSession().createSQLQuery(sql);
		return (T) querySetParams(query, params).uniqueResult();
	}

	@Override
	public <T> List<T> findListByHql(String hql, Object... params) {
		Query query = getSession().createQuery(hql);
		return querySetParams(query, params).list();
	}

	@Override
	public <T> List<T> findListBySql(String sql, Object... params) {
		Query query = getSession().createSQLQuery(sql);
		return querySetParams(query, params).list();
	}
	
	public <T> List<T> findListBySql(String sql, Class<T> entityClass, Object... params){
		Query query = getSession().createSQLQuery(sql).addEntity(entityClass);
		return querySetParams(query, params).list();
	}

	@Override
	public <T> Serializable save(T entity) {
		Serializable id = getSession().save(entity);
		getSession().flush();
		return id;
	}

	@Override
	public <T> void batchSave(List<T> entities) {
		for (int i = 1; i <= entities.size(); i++) {
			getSession().save(entities.get(i-1));
			if (i % 1000 == 0) {
				// 20个对象后才清理缓存，写入数据库
				getSession().flush();
				getSession().clear();
			}
		}
		// 最后清理一下----防止大于20小于40的不保存
		getSession().flush();
		getSession().clear();
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
		getSession().flush();
	}

	@Override
	public <T> void delete(T entity) {
		if(entity != null){
			getSession().delete(entity);
		}
	}
	
	@Override
	public <T> void batchDelete(Class<T> entityClass, String[] ids){
		for (int i = 1; i <= ids.length; i++) {
			delete(get(entityClass, ids[i-1]));
			if (i % 1000 == 0) {
				// 20个对象后才清理缓存，写入数据库
				getSession().flush();
				getSession().clear();
			}
		}
		// 最后清理一下----防止大于20小于40的不保存
		getSession().flush();
		getSession().clear();
	}

	@Override
	public <T> void deleteEntityById(Class<T> entityClass, Serializable id) {
		delete(get(entityClass, id));
		getSession().flush();
	}

	@Override
	public <T> void deleteEntities(List<T> entities) {
		for (T entity : entities) {
			delete(entity);
		}
		getSession().flush();
	}

	@Override
	public <T> void deleteByHql(String hql, Object... params) {
		Query query = getSession().createQuery(hql);
		querySetParams(query, params).executeUpdate();
		getSession().flush();
	}

	@Override
	public <T> void deleteBySql(String sql, Object... params) {
		Query query = getSession().createSQLQuery(sql);
		querySetParams(query, params).executeUpdate();
		getSession().flush();
	}

	@Override
	public <T> void update(T entity) {
		getSession().update(entity);
		getSession().flush();
	}
	
	@Override
	public <T> void merge(T entity){
		getSession().merge(entity);
		getSession().flush();
	}

	@Override
	public <T> void updateByHql(String hql, Object... params) {
		Query query = getSession().createQuery(hql);
		querySetParams(query, params).executeUpdate();
		getSession().flush();
	}

	@Override
	public <T> void updateBySql(String sql, Object... params) {
		Query query = getSession().createSQLQuery(sql);
		querySetParams(query, params).executeUpdate();
		getSession().flush();
	}

	public List<Map<String, Object>> findPageByQuery(int offset, int limit, String hql, Map<String, Object> optionsMap) {
		List<Map<String, Object>> result = null;
		try {
			Query query = getSession().createQuery(hql);

			if (optionsMap != null) {
				for (String key : optionsMap.keySet()) {
					query.setParameter(key.toString(), optionsMap.get(key));
				}
			}

			query.setFirstResult(offset);
			query.setMaxResults(limit);
			result = query.list();
		} catch (RuntimeException re) {
			throw re;
		}
		return result;
	}

	public int getTotalCount(String hql, Map<String, Object> optionsMap) {
		try {
			hql = "select count(id) " + hql;
			Query query = getSession().createQuery(hql);

			if (optionsMap != null) {
				for (String key : optionsMap.keySet()) {
					query.setParameter(key.toString(), optionsMap.get(key));
				}
			}

			Long count = (Long) query.uniqueResult();
			return count.intValue();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Map<String, Object> getPageData(int offset, int limit, String hql, Map<String, Object> optionsMap) {
		List<Map<String, Object>> data = findPageByQuery(offset, limit, hql, optionsMap);
		int count = getTotalCount(hql, optionsMap);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", count);
		result.put("rows", data);
		return result;
	}

	public Map<String, Object> getDataGrid(CriteriaQuery cq, boolean isOffset) {
		Map<String, Object> result = new HashMap<String, Object>();
		DataGrid dataGrid = cq.getDataGrid();

		Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(getSession());
		CriteriaImpl impl = (CriteriaImpl) criteria;
		// 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
		Projection projection = impl.getProjection();
		Long allCounts = (Long) (criteria.setProjection(Projections.rowCount()).uniqueResult());
		criteria.setProjection(projection);
		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (StringUtils.isNotBlank(dataGrid.getSort())) {
			cq.addOrder(dataGrid.getSort(), dataGrid.getOrder());
		}

		// 判断是否有排序字段
		if (!cq.getOrdermap().isEmpty()) {
			cq.setOrder(cq.getOrdermap());
		}
		
		if (isOffset) {// 是否分页
			criteria.setFirstResult(dataGrid.getOffset());
			criteria.setMaxResults(dataGrid.getLimit());
		}

		List<T> list = criteria.list();

		result.put("total", allCounts);
		result.put("rows", list);
		return result;
	}
	
	public Long getCountByCriteriaQuery(CriteriaQuery cq) {
		Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(getSession());
		Long allCounts = (Long) (criteria.setProjection(Projections.rowCount()).uniqueResult());
		return allCounts;
	}
}
