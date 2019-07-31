package com.bootdo.modules.flowable.service;

import com.bootdo.modules.flowable.domain.RuExecutionDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-10 19:40:02
 */
public interface RuExecutionService {
	
	RuExecutionDO get(String id);

	List<RuExecutionDO> list(Map<String, Object> map);
	List<Map<String, Object>> taskList(Map<String, Object> map);

	int count(Map<String, Object> map);
	int taskCount(Map<String, Object> map);

	int save(RuExecutionDO ruExecution);
	
	int update(RuExecutionDO ruExecution);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
