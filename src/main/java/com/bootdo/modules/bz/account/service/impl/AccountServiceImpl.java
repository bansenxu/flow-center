package com.bootdo.modules.bz.account.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.modules.bz.account.dao.AccountDao;
import com.bootdo.modules.bz.account.domain.AccountDO;
import com.bootdo.modules.bz.account.service.AccountService;



@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountDao accountDao;

	@Override
	public AccountDO get(String id){
		return accountDao.get(id);
	}
	
	@Override
	public List<AccountDO> list(Map<String, Object> map){
		return accountDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return accountDao.count(map);
	}
	
	@Override
	public int save(AccountDO account){
		return accountDao.save(account);
	}
	
	@Override
	public int update(AccountDO account){
		return accountDao.update(account);
	}
	
	@Override
	public int remove(String id){
		return accountDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return accountDao.batchRemove(ids);
	}
	
}
