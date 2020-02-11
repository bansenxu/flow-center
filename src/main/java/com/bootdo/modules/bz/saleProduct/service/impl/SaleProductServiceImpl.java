package com.bootdo.modules.bz.saleProduct.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.modules.bz.saleProduct.dao.SaleProductDao;
import com.bootdo.modules.bz.saleProduct.domain.SaleProductDO;
import com.bootdo.modules.bz.saleProduct.service.SaleProductService;



@Service
public class SaleProductServiceImpl implements SaleProductService {
	@Autowired
	private SaleProductDao productDao;
	
	@Override
	public SaleProductDO get(String id){
		return productDao.get(id);
	}
	
	@Override
	public List<SaleProductDO> list(Map<String, Object> map){
		return productDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productDao.count(map);
	}
	
	@Override
	public int save(SaleProductDO product){
		return productDao.save(product);
	}
	
	@Override
	public int update(SaleProductDO product){
		return productDao.update(product);
	}
	
	@Override
	public int remove(String id){
		return productDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return productDao.batchRemove(ids);
	}
	
}
