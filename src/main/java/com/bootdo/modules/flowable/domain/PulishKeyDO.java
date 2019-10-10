package com.bootdo.modules.flowable.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-10-09 15:39:25
 */
public class PulishKeyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private String id;
	//租户名称
	private String username;
	//公钥
	private String pulishKey;

	/**
	 * 设置：ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：租户名称
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：租户名称
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：公钥
	 */
	public void setPulishKey(String pulishKey) {
		this.pulishKey = pulishKey;
	}
	/**
	 * 获取：公钥
	 */
	public String getPulishKey() {
		return pulishKey;
	}
}
