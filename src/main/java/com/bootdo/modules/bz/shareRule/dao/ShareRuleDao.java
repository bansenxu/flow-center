package com.bootdo.modules.bz.shareRule.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.modules.bz.shareRule.domain.ShareRuleDO;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-20 09:43:50
 */
@Mapper
public interface ShareRuleDao {

	ShareRuleDO get(String id);
	
	List<ShareRuleDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ShareRuleDO rule);
	
	int update(ShareRuleDO rule);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}
