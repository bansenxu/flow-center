package com.bootdo.modules.flowable.service.impl;

import com.bootdo.modules.flowable.dao.ReProcdefDao;
import com.bootdo.modules.flowable.domain.ReProcdefDO;
import com.bootdo.modules.flowable.service.ReProcdefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class ReProcdefServiceImpl implements ReProcdefService {
	@Autowired
	private ReProcdefDao reProcdefDao;
	
	@Override
	public ReProcdefDO get(String id){
		return reProcdefDao.get(id);
	}
	
	@Override
	public List<ReProcdefDO> list(Map<String, Object> map){
		return reProcdefDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return reProcdefDao.count(map);
	}
	
	@Override
	public int save(ReProcdefDO reProcdef){
		return reProcdefDao.save(reProcdef);
	}
	
	@Override
	public int update(ReProcdefDO reProcdef){
		return reProcdefDao.update(reProcdef);
	}
	
	@Override
	public int remove(String id){
		return reProcdefDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return reProcdefDao.batchRemove(ids);
	}
	
}
