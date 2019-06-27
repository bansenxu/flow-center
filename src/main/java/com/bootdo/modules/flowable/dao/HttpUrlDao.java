package com.bootdo.modules.flowable.dao;

import com.bootdo.modules.flowable.domain.HttpUrlDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 */
@Mapper
public interface HttpUrlDao {

	HttpUrlDO get(String id);
	
	List<HttpUrlDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(HttpUrlDO httpUrl);
	
	int update(HttpUrlDO httpUrl);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
