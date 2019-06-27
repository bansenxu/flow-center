package com.bootdo.modules.flowable.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.modules.flowable.dao.HttpUrlDao;
import com.bootdo.modules.flowable.domain.HttpUrlDO;
import com.bootdo.modules.flowable.service.HttpUrlService;



@Service
public class HttpUrlServiceImpl implements HttpUrlService {
	@Autowired
	private HttpUrlDao httpUrlDao;
	
	@Override
	public HttpUrlDO get(String id){
		return httpUrlDao.get(id);
	}
	
	@Override
	public List<HttpUrlDO> list(Map<String, Object> map){
		return httpUrlDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return httpUrlDao.count(map);
	}
	
	@Override
	public int save(HttpUrlDO httpUrl){
		return httpUrlDao.save(httpUrl);
	}
	
	@Override
	public int update(HttpUrlDO httpUrl){
		return httpUrlDao.update(httpUrl);
	}
	
	@Override
	public int remove(String id){
		return httpUrlDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return httpUrlDao.batchRemove(ids);
	}
	
}
