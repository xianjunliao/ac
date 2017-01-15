package com.ac.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.ac.util.DataGrid;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
public class CommonDaoImpl extends BaseCommonDaoImpl implements CommonDao {

	private Query querySetList(Query query, List<Object> list) {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				query.setParameter(i, list.get(i));
			}
		}
		return query;
	}

	@Override
	public <T> List<T> getListByIds(Class<T> entityClass, String[] ids) {
		String hql = "from " + entityClass.getSimpleName() + " where id in (:ids)";
		return getListByHqlInIds(hql, ids);
	}

	@Override
	public <T> List<T> getListByProperty(Class<T> entityClass, LinkedHashMap<String, Object> whereParams) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from " + entityClass.getSimpleName() + " where 1 = 1 ");
		for (Entry<String, Object> entry : whereParams.entrySet()) {
			hql.append("and " + entry.getKey() + " = ? ");
			list.add(entry.getValue());
		}
		Query query = getSession().createQuery(hql.toString());
		return querySetList(query, list).list();
	}

	@Override
	public <T> List<T> getListBySqlInIds(String sql, String[] ids) {
		Query query = getSession().createSQLQuery(sql);
		query.setParameterList("ids", ids);
		return query.list();
	}

	@Override
	public <T> List<T> getListByHqlInIds(String hql, String[] ids) {
		Query query = getSession().createQuery(hql);
		query.setParameterList("ids", ids);
		return query.list();
	}

	@Override
	public void updateBySqlInIds(String sql, Object[] ids) {
		Query query = getSession().createSQLQuery(sql);
		query.setParameterList("ids", ids);
		query.executeUpdate();
	}

	@Override
	public void updateByHqlInIds(String hql, String[] ids) {
		Query query = getSession().createQuery(hql);
		query.setParameterList("ids", ids);
		query.executeUpdate();
	}

	@Override
	public String callProcedures(final String sql, final LinkedHashMap<String, Object> params) {
		return getSession().doReturningWork(new ReturningWork() {
			@Override
			public String execute(Connection conn) throws SQLException {
				CallableStatement stmt = conn.prepareCall(sql);
				int i = 0;
				for (Entry<String, Object> entry : params.entrySet()) {
					i++;
					String value = entry.getValue().toString();
					String type = entry.getValue().getClass().getName();
					if (entry.getKey().startsWith("in")) {
						if ("java.lang.String".equals(type)) {
							stmt.setString(i, value);
						} else if ("java.lang.Integer".equals(type)) {
							stmt.setInt(i, Integer.parseInt(value));
						} else if ("java.lang.Float".equals(type)) {
							stmt.setFloat(i, Float.parseFloat(value));
						} else if ("java.lang.Double".equals(type)) {
							stmt.setDouble(i, Double.parseDouble(value));
						} else {
							stmt.setObject(i, value);
						}
					} else if (entry.getKey().startsWith("out")) {
						stmt.registerOutParameter(i, Types.VARCHAR);
					}
				}
				stmt.execute();

				return stmt.getString(i);
			}
		});
	}

	private <T> StringBuffer updateSetHql(Class<T> entityClass, LinkedHashMap<String, Object> setParams, List<Object> list) {
		StringBuffer hql = new StringBuffer();
		hql.append("update " + entityClass.getSimpleName() + " set ");
		if (setParams == null || setParams.size() == 0) {
			return null;
		}
		for (Entry<String, Object> entry : setParams.entrySet()) {
			hql.append(entry.getKey() + " = ?, ");
			list.add(entry.getValue());
		}
		return hql.replace(hql.length() - 2, hql.length(), " ");
	}

	@Override
	public <T> void updateByProperty(Class<T> entityClass, LinkedHashMap<String, Object> setParams, String[] ids) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer hql = updateSetHql(entityClass, setParams, list);
		hql.append("where id in (:ids)");
		Query query = getSession().createQuery(hql.toString());
		query.setParameterList("ids", ids);
		querySetList(query, list).executeUpdate();
	}

	@Override
	public <T> void updateByProperty(Class<T> entityClass, LinkedHashMap<String, Object> setParams, LinkedHashMap<String, Object> whereParams) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer hql = updateSetHql(entityClass, setParams, list);
		if (hql == null) {
			return;
		}
		hql.append(" where 1 = 1 ");
		for (Entry<String, Object> entry : whereParams.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (key.endsWith("_begin") || key.endsWith("_end")) {
				if (key.endsWith("_begin")) {
					hql.append("and " + key.substring(0, key.length() - 6) + " >= ? ");
				} else {
					hql.append("and " + key.substring(0, key.length() - 4) + " <= ? ");
				}
				list.add(value);
			} /*
				 * else if ((key.equals("opProvince") ||
				 * key.equals("callerloc")) &&
				 * !entry.getValue().toString().contains(",")) { hql.append(
				 * "and " + key + " =  " + entry.getValue()); }
				 */ else if ((!key.equals("processingSteps") || !key.equals("callerloc")) && !entry.getValue().toString().contains(",")) {
				if (value.toString().contains("*")) {
					value = value.toString().replace("*", "%");
					hql.append("and lower(" + key + ") like lower(?) ");
				} else {
					hql.append("and " + key + " = ? ");
				}
				list.add(value);
			} else if (entry.getValue().toString().contains(",")) {
				hql.append("and " + key + " in (" + entry.getValue() + ") ");
			} else {
				hql.append("and " + key + " = ? ");
				list.add(value);
			}
		}

		Query query = getSession().createQuery(hql.toString());
		querySetList(query, list).executeUpdate();
	}

	@Override
	public <T> Long getCountByProperty(Class<T> entityClass, String[] ids) {
		String hql = "select count(*) from " + entityClass.getSimpleName() + " where id in (:ids)";
		Query query = getSession().createQuery(hql);
		return (Long) query.setParameterList("ids", ids).uniqueResult();
	}

	@Override
	public <T> Long getCountByProperty(Class<T> entityClass, LinkedHashMap<String, Object> whereParams) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("select count(*) from " + entityClass.getSimpleName() + " where 1 = 1 ");
		for (Entry<String, Object> entry : whereParams.entrySet()) {
			if ((entry.getKey().equals("opProvince") || entry.getKey().equals("processingStep")) && entry.getValue().toString().contains(",")) {
				hql.append("and " + entry.getKey() + " in (" + entry.getValue() + ") ");
			} else if (entry.getValue().toString().contains("*")) {
				String replace = ((String) entry.getValue()).replace("*", "%");
				hql.append("and lower(" + entry.getKey() + ") like lower(?) ");
				list.add(replace);
			} else {
				hql.append("and " + entry.getKey() + " = ? ");
				list.add(entry.getValue());
			}
		}

		Query query = getSession().createQuery(hql.toString());
		return (Long) querySetList(query, list).uniqueResult();
	}

	public Object[] getMailCode(Class<?> entityClass, String[] ids, LinkedHashMap<String, Object> whereParams) {
		if (ids != null && ids.length != 0) {
			String hql = "select mailCode from " + entityClass.getSimpleName() + " where id in (:ids) group by mailCode";
			Query query = getSession().createQuery(hql);
			return query.setParameterList("ids", ids).list().toArray();
		} else {
			List<Object> list = new ArrayList<Object>();
			StringBuffer hql = new StringBuffer();
			hql.append("select mailCode from " + entityClass.getSimpleName() + " where 1 = 1 ");
			for (Entry<String, Object> entry : whereParams.entrySet()) {
				hql.append("and " + entry.getKey() + " = ? ");
				list.add(entry.getValue());
			}

			hql.append("group by mailCode");
			Query query = getSession().createQuery(hql.toString());
			return querySetList(query, list).list().toArray();
		}
	}

	@Override
	public <T> List<T> getListByPts(Class<T> entityClass, LinkedHashMap<String, Object> whereParams) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from " + entityClass.getSimpleName() + " where 1 = 1 ");
		for (Entry<String, Object> entry : whereParams.entrySet()) {
			String key = entry.getKey();
			if (key.endsWith("_begin") || key.endsWith("_end")) {
				if (key.endsWith("_begin")) {
					hql.append("and " + key.substring(0, key.length() - 6) + " >= ? ");
				} else {
					hql.append("and " + key.substring(0, key.length() - 4) + " <= ? ");
				}
				list.add(entry.getValue());
			} else if (key.equals("processingSteps") && entry.getValue().toString().contains(",")) {
				hql.append("and " + key + " in (" + entry.getValue() + ") ");
			} else if (key.equals("processingStep") && entry.getValue().toString().contains(",")) {
				hql.append("and " + key + " in (" + entry.getValue() + ") ");
			} else if (key.equals("callerloc") && entry.getValue().toString().contains(",")) {
				hql.append("and " + key + " in (" + entry.getValue() + ") ");
			} else if (entry.getValue().toString().contains("*")) {
				String replace = ((String) entry.getValue()).replace("*", "%");
				hql.append("and lower(" + key + ") like lower(?) ");
				list.add(replace);
			} else {
				hql.append("and " + key + " = ? ");
				list.add(entry.getValue());
			}
		}
		Query query = getSession().createQuery(hql.toString());
		return querySetList(query, list).list();
	}
	
	@Override
	public <T> long getCountByWhereParams(Class<T> entityClass, LinkedHashMap<String, Object> whereParams) {
		
		List<Object> list = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("select count(*) from " + entityClass.getSimpleName() + " where 1 = 1 ");
		for (Entry<String, Object> entry : whereParams.entrySet()) {
			String key = entry.getKey();
			if (key.endsWith("_begin") || key.endsWith("_end")) {
				if (key.endsWith("_begin")) {
					hql.append("and " + key.substring(0, key.length() - 6) + " >= ? ");
				} else {
					hql.append("and " + key.substring(0, key.length() - 4) + " <= ? ");
				}
				list.add(entry.getValue());
			} else if (key.equals("processingSteps") && entry.getValue().toString().contains(",")) {
				hql.append("and " + key + " in (" + entry.getValue() + ") ");
			} else if (key.equals("processingStep") && entry.getValue().toString().contains(",")) {
				hql.append("and " + key + " in (" + entry.getValue() + ") ");
			} else if (key.equals("callerloc") && entry.getValue().toString().contains(",")) {
				hql.append("and " + key + " in (" + entry.getValue() + ") ");
			} else if (entry.getValue().toString().contains("*")) {
				String replace = ((String) entry.getValue()).replace("*", "%");
				hql.append("and lower(" + key + ") like lower(?) ");
				list.add(replace);
			} else {
				hql.append("and " + key + " = ? ");
				list.add(entry.getValue());
			}
		}
		Query query = getSession().createQuery(hql.toString());
		return (long) querySetList(query, list).uniqueResult();
	}

	@Override
	public Long sqlGetCount(String sqlName, Object... params) {
		Query query = getSession().getNamedQuery(sqlName);
		return ((BigDecimal) querySetParams(query, params).uniqueResult()).longValue();
	}

	@Override
	public <T> List<T> sqlGetList(String sqlName, DataGrid dataGrid, Object... params) {
		Query query = getSession().getNamedQuery(sqlName).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (dataGrid != null) {
			query.setFirstResult(dataGrid.getOffset());
			query.setMaxResults(dataGrid.getLimit());
		}
		return querySetParams(query, params).list();
	}

	@Override
	public Object sqlGetObject(String sqlName, Object... params) {
		Query query = getSession().getNamedQuery(sqlName).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return querySetParams(query, params).uniqueResult();
	}
	
	@Override
	public BigDecimal sqlGetBigDecimal(String sqlName, Object... params) {
		Query query = getSession().getNamedQuery(sqlName);
		return (BigDecimal) querySetParams(query, params).uniqueResult();
	}

}
