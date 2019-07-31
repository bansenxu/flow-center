package com.bootdo.modules.flowable.service.impl;

import com.bootdo.modules.flowable.dao.RuExecutionDao;
import com.bootdo.modules.flowable.domain.RuExecutionDO;
import com.bootdo.modules.flowable.service.RuExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class RuExecutionServiceImpl implements RuExecutionService {
	@Autowired
	private RuExecutionDao ruExecutionDao;
	
	@Override
	public RuExecutionDO get(String id){
		return ruExecutionDao.get(id);
	}
	
	@Override
	public List<RuExecutionDO> list(Map<String, Object> map){
		return ruExecutionDao.list(map);
	}
	@Override
	public List<Map<String, Object>> taskList(Map<String, Object> map){
		return ruExecutionDao.taskList(map);
	}
	
	
	
	@Override
	public int count(Map<String, Object> map){
		return ruExecutionDao.count(map);
	}

	@Override
	public int taskCount(Map<String, Object> map){
		return ruExecutionDao.taskCount(map);
	}

	@Override
	public int save(RuExecutionDO ruExecution){
		return ruExecutionDao.save(ruExecution);
	}
	
	@Override
	public int update(RuExecutionDO ruExecution){
		return ruExecutionDao.update(ruExecution);
	}
	
	@Override
	public int remove(String id){
		return ruExecutionDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return ruExecutionDao.batchRemove(ids);
	}
	
}
