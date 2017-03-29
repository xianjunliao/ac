package com.ac.serice.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ac.dao.common.impl.CommonDaoImpl;
import com.ac.entity.Menu1Entity;
import com.ac.entity.MenuEntity;
import com.ac.entity.PoolEntity;
import com.ac.serice.IndexService;

@SuppressWarnings("unchecked")
@Service("IndexService")
@Transactional
public class IndexServiceImpl extends CommonDaoImpl implements IndexService  {

	

	@Override
	public List<MenuEntity> getAll() {
		List<MenuEntity> findListBySql =findListBySql("select * from menu", MenuEntity.class);
		System.out.println(findListBySql);
		return findListBySql;
	}


	@Override
	public List<Menu1Entity> getMenu1All() {
		List<Menu1Entity> findListBySql =findListBySql("select * from menu1", Menu1Entity.class);
		System.out.println(findListBySql);
		return findListBySql;
	}


	@Override
	public List<PoolEntity> getPoolAll() {
		List<PoolEntity> findListBySql =findListBySql("select * from pool", PoolEntity.class);
		System.out.println(findListBySql);
		return findListBySql;
	}

}
