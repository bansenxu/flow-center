package com.bootdo.modules.flowable.dao;

import com.bootdo.modules.flowable.domain.PulishKeyDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-10-09 15:39:25
 */
@Mapper
public interface PulishKeyDao {

	PulishKeyDO get(String id);
	
	PulishKeyDO getByUserName(String username);
	
	List<PulishKeyDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PulishKeyDO pulishKey);
	
	int update(PulishKeyDO pulishKey);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
