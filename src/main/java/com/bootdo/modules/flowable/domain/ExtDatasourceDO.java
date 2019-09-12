package com.bootdo.modules.flowable.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author wangxing
 * @email 358402521@qq.com
 * @date 2019-08-15 16:49:39
 */
public class ExtDatasourceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private String id;
	//名称
	private String dbname;
	//驱动名称
	private String driverclassname;
	//URL
	private String url;
	//用户名
	private String username;
	//密码
	private String userpwd;

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
	 * 设置：名称
	 */
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	/**
	 * 获取：名称
	 */
	public String getDbname() {
		return dbname;
	}
	/**
	 * 设置：驱动名称
	 */
	public void setDriverclassname(String driverclassname) {
		this.driverclassname = driverclassname;
	}
	/**
	 * 获取：驱动名称
	 */
	public String getDriverclassname() {
		return driverclassname;
	}
	/**
	 * 设置：URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：URL
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：密码
	 */
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	/**
	 * 获取：密码
	 */
	public String getUserpwd() {
		return userpwd;
	}
}
