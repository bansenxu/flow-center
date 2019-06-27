package com.bootdo.modules.flowable.service;

import com.bootdo.modules.flowable.domain.HttpUrlDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-08 15:41:38
 */
public interface HttpUrlService {
	
	HttpUrlDO get(String id);
	
	List<HttpUrlDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(HttpUrlDO httpUrl);
	
	int update(HttpUrlDO httpUrl);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
