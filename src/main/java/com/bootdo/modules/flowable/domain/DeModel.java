package com.bootdo.modules.flowable.domain;

import java.io.Serializable;



/**
 * 
 * 
 * @author wangxing
 * @email 358402521@qq.com
 * @date 2019-08-15 15:59:38
 */
public class DeModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String modelEditerJson;

	public String getModelEditerJson() {
		return modelEditerJson;
	}

	public void setModelEditerJson(String modelEditerJson) {
		this.modelEditerJson = modelEditerJson;
	}

}
