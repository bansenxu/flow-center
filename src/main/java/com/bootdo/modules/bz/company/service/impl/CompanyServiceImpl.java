package com.bootdo.modules.bz.company.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.modules.bz.company.dao.CompanyDao;
import com.bootdo.modules.bz.company.domain.CompanyDO;
import com.bootdo.modules.bz.company.service.CompanyService;



@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyDao companyDao;
	
	@Override
	public CompanyDO get(String id){
		return companyDao.get(id);
	}
	
	@Override
	public List<CompanyDO> list(Map<String, Object> map){
		return companyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return companyDao.count(map);
	}
	
	@Override
	public int save(CompanyDO company){
		return companyDao.save(company);
	}
	
	@Override
	public int update(CompanyDO company){
		return companyDao.update(company);
	}
	
	@Override
	public int remove(String comCode){
		return companyDao.remove(comCode);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return companyDao.batchRemove(ids);
	}
	
}
