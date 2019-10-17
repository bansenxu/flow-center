package com.bootdo.modules.flowable.dao;

import java.util.List;

import org.flowable.engine.impl.persistence.entity.ByteArrayEntityImpl;

public interface GeByteArrayDao {

	List<ByteArrayEntityImpl> selectBytesOfByteArray(String id);
}
