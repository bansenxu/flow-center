package com.bootdo.modules.bz.saleorder.service;

import java.util.List;
import java.util.Map;

import com.bootdo.modules.bz.saleorder.domain.SaleOrderDO;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-21 14:34:42
 */
public interface SaleOrderService {
	
	SaleOrderDO get(String id);
	
	List<SaleOrderDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SaleOrderDO order);
	
	int update(SaleOrderDO order);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
