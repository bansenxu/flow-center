package com.bootdo.modules.bz.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.modules.bz.product.domain.ProductDO;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-18 17:03:39
 */
@Mapper
public interface ProductDao {

	ProductDO get(String id);
	
	List<ProductDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProductDO product);
	
	int update(ProductDO product);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}
