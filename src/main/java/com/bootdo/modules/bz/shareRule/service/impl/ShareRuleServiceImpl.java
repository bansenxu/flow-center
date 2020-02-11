package com.bootdo.modules.bz.shareRule.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.modules.bz.shareRule.dao.ShareRuleDao;
import com.bootdo.modules.bz.shareRule.domain.ShareRuleDO;
import com.bootdo.modules.bz.shareRule.service.ShareRuleService;



@Service
public class ShareRuleServiceImpl implements ShareRuleService {
	@Autowired
	private ShareRuleDao ruleDao;
	
	@Override
	public ShareRuleDO get(String id){
		return ruleDao.get(id);
	}
	
	@Override
	public List<ShareRuleDO> list(Map<String, Object> map){
		return ruleDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return ruleDao.count(map);
	}
	
	@Override
	public int save(ShareRuleDO rule){
		return ruleDao.save(rule);
	}
	
	@Override
	public int update(ShareRuleDO rule){
		return ruleDao.update(rule);
	}
	
	@Override
	public int remove(String id){
		return ruleDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return ruleDao.batchRemove(ids);
	}
	
}
