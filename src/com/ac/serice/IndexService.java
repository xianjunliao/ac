package com.ac.serice;

import java.util.List;

import com.ac.entity.Menu1Entity;
import com.ac.entity.MenuEntity;
import com.ac.entity.PoolEntity;

public interface IndexService{

	public List<MenuEntity> getAll();
	public List<Menu1Entity> getMenu1All(String code);
	public List<PoolEntity> getPoolAll(String code);
 }
