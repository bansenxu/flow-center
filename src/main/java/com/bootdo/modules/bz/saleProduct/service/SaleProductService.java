package com.bootdo.modules.bz.saleProduct.service;

import java.util.List;
import java.util.Map;

import com.bootdo.modules.bz.saleProduct.domain.SaleProductDO;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-20 09:43:14
 */
public interface SaleProductService {
	
	SaleProductDO get(String id);
	
	List<SaleProductDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SaleProductDO product);
	
	int update(SaleProductDO product);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
