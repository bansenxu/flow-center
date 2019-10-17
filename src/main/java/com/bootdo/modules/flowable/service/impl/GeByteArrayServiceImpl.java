package com.bootdo.modules.flowable.service.impl;


import java.util.List;

import org.flowable.engine.impl.persistence.entity.ByteArrayEntityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.modules.flowable.dao.GeByteArrayDao;
import com.bootdo.modules.flowable.service.GeByteArrayService;

@Service
public class GeByteArrayServiceImpl implements GeByteArrayService {
    @Autowired
    private GeByteArrayDao geByteArrayServiceDao;

    public List<ByteArrayEntityImpl> selectBytesOfByteArray(String id){
    	return geByteArrayServiceDao.selectBytesOfByteArray(id);
    }
}
