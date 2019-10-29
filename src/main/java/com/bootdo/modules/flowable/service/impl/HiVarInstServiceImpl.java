package com.bootdo.modules.flowable.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.modules.flowable.dao.HiVarInstDao;
import com.bootdo.modules.flowable.domain.HiVarInstDO;
import com.bootdo.modules.flowable.service.HiVarInstService;

@Service
public class HiVarInstServiceImpl implements HiVarInstService {
    @Autowired
    private HiVarInstDao hiVarInstDao;

    public List<HiVarInstDO> selectHiVarInst(String id){
    	return hiVarInstDao.selectHiVarInst(id);
    }
}
