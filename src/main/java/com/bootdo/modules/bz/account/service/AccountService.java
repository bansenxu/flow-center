package com.bootdo.modules.bz.account.service;

import java.util.List;
import java.util.Map;

import com.bootdo.modules.bz.account.domain.AccountDO;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-19 10:43:14
 */
public interface AccountService {
	
	AccountDO get(String id);
	
	List<AccountDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AccountDO account);
	
	int update(AccountDO account);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
