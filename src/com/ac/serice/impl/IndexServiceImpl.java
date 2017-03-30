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
	public List<Menu1Entity> getMenu1All(String code) {
		List<Menu1Entity> findListBySql =findListBySql("select * from menu1 where menu_code=?", Menu1Entity.class,code);
		System.out.println(findListBySql);
		return findListBySql;
	}


	@Override
	public List<PoolEntity> getPoolAll(String code) {
		List<PoolEntity> findListBySql =findListBySql("select * from pool where menu_code=?", PoolEntity.class);
		System.out.println(findListBySql);
		return findListBySql;
	}

}
