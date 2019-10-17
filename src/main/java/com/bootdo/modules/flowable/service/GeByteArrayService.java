package com.bootdo.modules.flowable.service;


import java.util.List;

import org.flowable.engine.impl.persistence.entity.ByteArrayEntityImpl;

public interface GeByteArrayService {

	List<ByteArrayEntityImpl> selectBytesOfByteArray(String id);
}
