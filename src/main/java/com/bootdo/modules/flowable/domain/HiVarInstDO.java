package com.bootdo.modules.flowable.domain;

import org.flowable.engine.impl.persistence.entity.ByteArrayEntity;

public class HiVarInstDO {
	private String text;
	private String name;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
