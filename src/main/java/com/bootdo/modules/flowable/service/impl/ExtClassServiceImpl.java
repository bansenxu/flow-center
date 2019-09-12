package com.bootdo.modules.flowable.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.modules.flowable.dao.ExtClassDao;
import com.bootdo.modules.flowable.domain.ExtClassDO;
import com.bootdo.modules.flowable.service.ExtClassService;



@Service
public class ExtClassServiceImpl implements ExtClassService {
	@Autowired
	private ExtClassDao extClassDao;
	
	@Override
	public ExtClassDO get(String id){
		return extClassDao.get(id);
	}
	
	@Override
	public List<ExtClassDO> list(Map<String, Object> map){
		return extClassDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return extClassDao.count(map);
	}
	
	@Override
	public int save(ExtClassDO extClass){
		return extClassDao.save(extClass);
	}
	
	@Override
	public int update(ExtClassDO extClass){
		return extClassDao.update(extClass);
	}
	
	@Override
	public int remove(String id){
		return extClassDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return extClassDao.batchRemove(ids);
	}
	
}
