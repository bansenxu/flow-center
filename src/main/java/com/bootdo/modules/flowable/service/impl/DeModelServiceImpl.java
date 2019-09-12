package com.bootdo.modules.flowable.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.modules.flowable.dao.DeModelDao;
import com.bootdo.modules.flowable.domain.DeModel;
import com.bootdo.modules.flowable.service.DeModelService;



@Service("com.bootdo.modules.flowable.service.impl.DeModelServiceImpl")
public class DeModelServiceImpl implements DeModelService {
	@Autowired
	private DeModelDao deModelDao;
	
	@Override
	public DeModel get(String name){
		return deModelDao.get(name);
	}
}
