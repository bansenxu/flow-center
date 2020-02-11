package com.bootdo.modules.bz.saleProduct.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.modules.bz.saleProduct.domain.SaleProductDO;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-20 09:43:14
 */
@Mapper
public interface SaleProductDao {

	SaleProductDO get(String id);
	
	List<SaleProductDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SaleProductDO product);
	
	int update(SaleProductDO product);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}
