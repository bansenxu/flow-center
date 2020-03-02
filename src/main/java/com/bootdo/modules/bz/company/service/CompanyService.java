package com.bootdo.modules.bz.company.service;

import java.util.List;
import java.util.Map;

import com.bootdo.modules.bz.company.domain.CompanyDO;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-19 10:44:05
 */
public interface CompanyService {
	
	CompanyDO get(String id);
	
	List<CompanyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CompanyDO company);
	
	int update(CompanyDO company);
	
	int remove(String comCode);
	
	int batchRemove(String[] ids);
}
