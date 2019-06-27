package com.bootdo.modules.flowable.dao;

import com.bootdo.modules.flowable.domain.ReProcdefDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-10 11:15:19
 */
@Mapper
public interface ReProcdefDao {

	ReProcdefDO get(String id);
	
	List<ReProcdefDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ReProcdefDO reProcdef);
	
	int update(ReProcdefDO reProcdef);
	
	int remove(String ID_);
	
	int batchRemove(String[] ids);
}
