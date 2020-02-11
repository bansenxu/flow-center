package com.bootdo.modules.bz.product.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.modules.bz.product.dao.ProductDao;
import com.bootdo.modules.bz.product.domain.ProductDO;
import com.bootdo.modules.bz.product.service.ProductService;



@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	
	@Override
	public ProductDO get(String id){
		return productDao.get(id);
	}
	
	@Override
	public List<ProductDO> list(Map<String, Object> map){
		return productDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productDao.count(map);
	}
	
	@Override
	public int save(ProductDO product){
		return productDao.save(product);
	}
	
	@Override
	public int update(ProductDO product){
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
