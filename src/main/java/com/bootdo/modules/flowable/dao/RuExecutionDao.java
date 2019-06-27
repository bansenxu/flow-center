package com.bootdo.modules.flowable.dao;

import com.bootdo.modules.flowable.domain.RuExecutionDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-10 19:40:02
 */
@Mapper
public interface RuExecutionDao {

	RuExecutionDO get(String id);

	List<RuExecutionDO> list(Map<String, Object> map);
	List<Map<String, Object>> taskList(Map<String, Object> map);

	int count(Map<String, Object> map);
	int taskCount(Map<String, Object> map);

	int save(RuExecutionDO ruExecution);
	
	int update(RuExecutionDO ruExecution);
	
	int remove(String ID_);
	
	int batchRemove(String[] ids);
}
