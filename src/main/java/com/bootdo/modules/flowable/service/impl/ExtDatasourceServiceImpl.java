package com.bootdo.modules.flowable.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.modules.flowable.dao.ExtDatasourceDao;
import com.bootdo.modules.flowable.domain.ExtDatasourceDO;
import com.bootdo.modules.flowable.service.ExtDatasourceService;



@Service
public class ExtDatasourceServiceImpl implements ExtDatasourceService {
	@Autowired
	private ExtDatasourceDao extDatasourceDao;
	
	@Override
	public ExtDatasourceDO get(String id){
		return extDatasourceDao.get(id);
	}
	
	@Override
	public List<ExtDatasourceDO> list(Map<String, Object> map){
		return extDatasourceDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return extDatasourceDao.count(map);
	}
	
	@Override
	public int save(ExtDatasourceDO extDatasource){
		return extDatasourceDao.save(extDatasource);
	}
	
	@Override
	public int update(ExtDatasourceDO extDatasource){
		return extDatasourceDao.update(extDatasource);
	}
	
	@Override
	public int remove(String id){
		return extDatasourceDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return extDatasourceDao.batchRemove(ids);
	}
	
}
