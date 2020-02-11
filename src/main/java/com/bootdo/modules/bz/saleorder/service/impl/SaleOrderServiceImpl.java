package com.bootdo.modules.bz.saleorder.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.modules.bz.saleorder.dao.SaleOrderDao;
import com.bootdo.modules.bz.saleorder.domain.SaleOrderDO;
import com.bootdo.modules.bz.saleorder.service.SaleOrderService;



@Service
public class SaleOrderServiceImpl implements SaleOrderService {
	@Autowired
	private SaleOrderDao orderDao;
	
	@Override
	public SaleOrderDO get(String id){
		return orderDao.get(id);
	}
	
	@Override
	public List<SaleOrderDO> list(Map<String, Object> map){
		return orderDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderDao.count(map);
	}
	
	@Override
	public int save(SaleOrderDO order){
		return orderDao.save(order);
	}
	
	@Override
	public int update(SaleOrderDO order){
		return orderDao.update(order);
	}
	
	@Override
	public int remove(String id){
		return orderDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return orderDao.batchRemove(ids);
	}
	
}
