package com.bootdo.modules.flowable.domain;

import org.flowable.engine.impl.persistence.entity.ByteArrayEntity;

public class GeByteArrayDO {
	private ByteArrayEntity entity;
	private String name;
	
	public ByteArrayEntity getEntity() {
		return entity;
	}
	public void setEntity(ByteArrayEntity entity) {
		this.entity = entity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
