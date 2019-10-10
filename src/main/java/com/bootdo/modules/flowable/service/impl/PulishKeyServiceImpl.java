package com.bootdo.modules.flowable.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.modules.flowable.dao.PulishKeyDao;
import com.bootdo.modules.flowable.domain.PulishKeyDO;
import com.bootdo.modules.flowable.service.PulishKeyService;



@Service
public class PulishKeyServiceImpl implements PulishKeyService {
	@Autowired
	private PulishKeyDao pulishKeyDao;
	
	@Override
	public PulishKeyDO get(String id){
		return pulishKeyDao.get(id);
	}
	
	@Override
	public PulishKeyDO getByUserName(String username){
		return pulishKeyDao.getByUserName(username);
	}
	
	@Override
	public List<PulishKeyDO> list(Map<String, Object> map){
		return pulishKeyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return pulishKeyDao.count(map);
	}
	
	@Override
	public int save(PulishKeyDO pulishKey){
		return pulishKeyDao.save(pulishKey);
	}
	
	@Override
	public int update(PulishKeyDO pulishKey){
		return pulishKeyDao.update(pulishKey);
	}
	
	@Override
	public int remove(String id){
		return pulishKeyDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return pulishKeyDao.batchRemove(ids);
	}
	
}
