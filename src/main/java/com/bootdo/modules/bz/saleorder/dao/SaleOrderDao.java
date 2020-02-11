package com.bootdo.modules.bz.saleorder.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.modules.bz.saleorder.domain.SaleOrderDO;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-21 14:34:42
 */
@Mapper
public interface SaleOrderDao {

	SaleOrderDO get(String id);
	
	List<SaleOrderDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SaleOrderDO order);
	
	int update(SaleOrderDO order);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}
