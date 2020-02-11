package com.bootdo.modules.bz.product.service;

import java.util.List;
import java.util.Map;

import com.bootdo.modules.bz.product.domain.ProductDO;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-18 17:03:39
 */
public interface ProductService {
	
	ProductDO get(String id);
	
	List<ProductDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProductDO product);
	
	int update(ProductDO product);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
