package com.bootdo.modules.bz.shareRule.service;

import java.util.List;
import java.util.Map;

import com.bootdo.modules.bz.shareRule.domain.ShareRuleDO;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-20 09:43:50
 */
public interface ShareRuleService {
	
	ShareRuleDO get(String id);
	
	List<ShareRuleDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ShareRuleDO rule);
	
	int update(ShareRuleDO rule);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
