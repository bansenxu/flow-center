package com.bootdo.modules.flowable.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author wangxing
 * @email 358402521@qq.com
 * @date 2019-08-15 15:59:38
 */
public class ExtClassDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String classname;
	//
	private String description;
	//
	private String fullname;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setClassname(String classname) {
		this.classname = classname;
	}
	/**
	 * 获取：
	 */
	public String getClassname() {
		return classname;
	}
	/**
	 * 设置：
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	/**
	 * 获取：
	 */
	public String getFullname() {
		return fullname;
	}
}
