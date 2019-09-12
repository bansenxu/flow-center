package com.bootdo.modules.flowable.service;

import com.bootdo.modules.flowable.domain.ExtDatasourceDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wangxing
 * @email 358402521@qq.com
 * @date 2019-08-15 16:49:39
 */
public interface ExtDatasourceService {
	
	ExtDatasourceDO get(String id);
	
	List<ExtDatasourceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExtDatasourceDO extDatasource);
	
	int update(ExtDatasourceDO extDatasource);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
