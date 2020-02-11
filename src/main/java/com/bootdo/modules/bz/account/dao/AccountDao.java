package com.bootdo.modules.bz.account.dao;
        
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.modules.bz.account.domain.AccountDO;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-19 10:43:14
 */
@Mapper
public interface AccountDao {

	AccountDO get(String id);
	
	List<AccountDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AccountDO account);
	
	int update(AccountDO account);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}
