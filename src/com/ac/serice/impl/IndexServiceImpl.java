package com.ac.serice.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ac.dao.common.impl.CommonDaoImpl;
import com.ac.entity.MenuEntity;
import com.ac.serice.IndexService;

@SuppressWarnings("unchecked")
@Service("IndexService")
@Transactional
public class IndexServiceImpl extends CommonDaoImpl implements IndexService  {

	

	@Override
	public List<MenuEntity> getAll() {
		List<MenuEntity> findListBySql =findListBySql("select * from menu", MenuEntity.class);
		return findListBySql;
	}

}
