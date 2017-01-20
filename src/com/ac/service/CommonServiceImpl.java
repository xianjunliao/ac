package com.ac.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ac.dao.CommonDao;
import com.ac.dao.JdbcDao;
import com.ac.util.CriteriaQuery;
import com.ac.util.DataGrid;
import com.ac.util.SortDirection;

@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {

	@Resource
	private CommonDao commonDao;
	@Resource
	private JdbcDao jdbcDao;

	@Override
	public <T> T get(Class<T> entityClass, Serializable id) {
		return commonDao.get(entityClass, id);
	}

	@Override
	public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value) {
		return commonDao.findUniqueByProperty(entityClass, propertyName, value);
	}

	@Override
	public <T> T findUniqueByProperty(Class<T> entityClass, Map<String, Object> optionsMap) {
		return commonDao.findUniqueByProperty(entityClass, optionsMap);
	}

	@Override
	public <T> List<T> findListByProperty(Class<T> entityClass) {
		return commonDao.findListByProperty(entityClass, null);
	}

	@Override
	public <T> List<T> findListByProperty(Class<T> entityClass, String sort, SortDirection order) {
		return commonDao.findListByProperty(entityClass, sort, order);
	}

	@Override
	public <T> List<T> findListByProperty(Class<T> entityClass, String propertyName, Object value) {
		return commonDao.findListByProperty(entityClass, propertyName, value);
	}

	@Override
	public <T> Long getCountByProperty(Class<T> entityClass, String propertyName, Object value) {
		return commonDao.getCountByProperty(entityClass, propertyName, value);
	}

	@Override
	public <T> Long getCountByProperty(Class<T> entityClass, String[] ids) {
		return commonDao.getCountByProperty(entityClass, ids);
	}

	@Override
	public <T> Long getCountByProperty(Class<T> entityClass, LinkedHashMap<String, Object> whereParams) {
		return commonDao.getCountByProperty(entityClass, whereParams);
	}

	@Override
	public <T> List<T> findListByProperty(Class<T> entityClass, String propertyName, Object value, String sort, SortDirection order) {
		return commonDao.findListByProperty(entityClass, propertyName, value, sort, order);
	}

	@Override
	public <T> List<T> findListByProperty(Class<T> entityClass, Map<String, Object> optionsMap) {
		return commonDao.findListByProperty(entityClass, optionsMap);
	}

	@Override
	public <T> T findUniqueByHql(String hql, Object... params) {
		return commonDao.findUniqueByHql(hql, params);
	}

	@Override
	public <T> T findUniqueBySql(String sql, Object... params) {
		return commonDao.findUniqueBySql(sql, params);
	}

	@Override
	public <T> List<T> findListByHql(String hql, Object... params) {
		return commonDao.findListByHql(hql, params);
	}

	@Override
	public <T> List<T> findListBySql(String sql, Object... params) {
		return commonDao.findListBySql(sql, params);
	}

	@Override
	public <T> List<T> findListBySql(String sql, Class<T> entityClass, Object... params) {
		return commonDao.findListBySql(sql, entityClass, params);
	}

	@Override
	public <T> List<T> getListByIds(Class<T> entityClass, String[] ids) {
		return commonDao.getListByIds(entityClass, ids);
	}

	@Override
	public <T> List<T> getListByProperty(Class<T> entityClass, LinkedHashMap<String, Object> whereParams) {
		return commonDao.getListByProperty(entityClass, whereParams);
	}

	@Override
	public <T> List<T> getListBySqlInIds(String sql, String[] ids) {
		return commonDao.getListBySqlInIds(sql, ids);
	}

	@Override
	public <T> List<T> getListByHqlInIds(String hql, String[] ids) {
		return commonDao.getListByHqlInIds(hql, ids);
	}

	@Override
	public <T> Serializable save(T entity) {
		return commonDao.save(entity);
	}

	@Override
	public <T> void batchSave(List<T> entities) {
		commonDao.batchSave(entities);
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		commonDao.saveOrUpdate(entity);
	}

	@Override
	public <T> void delete(T entity) {
		commonDao.delete(entity);
	}

	@Override
	public <T> void batchDelete(Class<T> entityClass, String[] ids) {
		commonDao.batchDelete(entityClass, ids);
	}

	@Override
	public <T> void deleteEntityById(Class<T> entityClass, Serializable id) {
		commonDao.deleteEntityById(entityClass, id);
	}

	@Override
	public <T> void deleteEntities(List<T> entities) {
		commonDao.deleteEntities(entities);
	}

	@Override
	public <T> void deleteByHql(String hql, Object... params) {
		commonDao.deleteByHql(hql, params);
	}

	@Override
	public <T> void deleteBySql(String sql, Object... params) {
		commonDao.deleteBySql(sql, params);
	}

	@Override
	public <T> void update(T entity) {
		commonDao.update(entity);
	}

	@Override
	public <T> void merge(T entity) {
		commonDao.merge(entity);
	}

	@Override
	public <T> void updateByHql(String hql, Object... params) {
		commonDao.updateByHql(hql, params);
	}

	@Override
	public <T> void updateBySql(String sql, Object... params) {
		commonDao.updateBySql(sql, params);
	}

	@Override
	public <T> void updateBySqlInIds(String sql, Object[] ids) {
		commonDao.updateBySqlInIds(sql, ids);
	}

	@Override
	public <T> void updateByHqlInIds(String hql, String[] ids) {
		commonDao.updateByHqlInIds(hql, ids);
	}

	@Override
	public <T> void updateByProperty(Class<T> entityClass, LinkedHashMap<String, Object> setParams, String[] ids) {
		commonDao.updateByProperty(entityClass, setParams, ids);
	}

	@Override
	public <T> void updateByProperty(Class<T> entityClass, LinkedHashMap<String, Object> setParams, LinkedHashMap<String, Object> whereParams) {
		commonDao.updateByProperty(entityClass, setParams, whereParams);
	}

	@Override
	public Map<String, Object> getPageData(int offset, int limit, String hql, Map<String, Object> optionsMap) {
		return commonDao.getPageData(offset, limit, hql, optionsMap);
	}

	@Override
	public Map<String, Object> getDataGrid(CriteriaQuery cq) {
		return commonDao.getDataGrid(cq, true);
	}

	@Override
	public Long getCountByCriteriaQuery(CriteriaQuery cq) {
		return commonDao.getCountByCriteriaQuery(cq);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getDataGridNoPagination(CriteriaQuery cq) {
		Map<String, Object> map = commonDao.getDataGrid(cq, false);
		return (List<T>) map.get("rows");
	}

	@Override
	public Map<String, Object> getDataGrid(String sql, List<String> listParams, DataGrid datagrid) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(listParams==null)listParams=new ArrayList<String>();
		Object[] ps = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			ps[i] = listParams.get(i);
		}
		String cntSql = "select count(*) as cnt from (" + sql + ")";
		long total = Long.valueOf(jdbcDao.queryBySql(cntSql, ps).get(0).get("cnt").toString());
		map.put("total", total);
		sql = "select *from " + "(select *from ( select * from (" + sql + ") " + orderStr(datagrid) + ") where rownum>" + datagrid.getOffset() + ") where rownum<=" + datagrid.getLimit();

		List<Map<String, Object>> dataList = jdbcDao.queryBySql(sql, ps);
		map.put("rows", dataList);
		return map;
	}

	private String orderStr(DataGrid datagrid) {
		String order = datagrid.getSort();
		if (StringUtils.isNotBlank(order)) {
			return " order by \"" + order + "\" " + (StringUtils.isNotBlank(String.valueOf(datagrid.getOrder())) ? datagrid.getOrder() : "");
		} else
			return "";
	}


	@Override
	public void updateSeqByName(String seqName, Integer seq) {
		String hql = "SequenceEntity set currentValue=? where name=?";
		updateByHql(hql, new Object[] { seq, seqName });
	}


	@Override
	public String callProcedures(String sql, LinkedHashMap<String, Object> params) {
		return commonDao.callProcedures(sql, params);
	}

	@Override
	public List<Map<String, Object>> jdbcQuery(String sql, Object... params) throws Exception {
		try {
			return jdbcDao.queryBySql(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("查询出错！");
		}
	}

	@Override
	public Object[] getMailCode(Class<?> entityClass, String[] ids, LinkedHashMap<String, Object> whereParams) {
		return commonDao.getMailCode(entityClass, ids, whereParams);
	}

	@Override
	public <T> List<T> getListByPts(Class<T> entityClass, LinkedHashMap<String, Object> whereParams) {
		return commonDao.getListByPts(entityClass, whereParams);
	}

	@Override
	public Long sqlGetCount(String sqlName, Object... params) {
		return commonDao.sqlGetCount(sqlName, params);
	}

	@Override
	public <T> List<T> sqlGetList(String sqlName, DataGrid dataGrid, Object... params) {
		return commonDao.sqlGetList(sqlName, dataGrid, params);
	}
	
	@Override
	public Object sqlGetObject(String sqlName, Object... params) {
		return commonDao.sqlGetObject(sqlName, params);
	}

	@Override
	public <T> long getCountByWhereParams(Class<T> entityClass, LinkedHashMap<String, Object> whereParams) {
		return commonDao.getCountByWhereParams(entityClass, whereParams);
	}
	
	@Override
	public BigDecimal sqlGetBigDecimal(String sqlName, Object... params) {
		return commonDao.sqlGetBigDecimal(sqlName, params);
	}
}
