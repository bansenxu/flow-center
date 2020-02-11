package com.bootdo.modules.bz.company.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-19 10:44:05
 */
public class CompanyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String comName;
	//
	private String comCode;
	//0-未审核  1-已审核   9-账户余额不足   2-黑名单
	private String statue;
	//企业类型 0-购买方 1-渠道商 2-供应商
	private String comType;
	//
	private Date createDate;
	//
	private Date editeDate;
	//
	private String invComCode;
	//数据有效性 0-无效 1-有效
	private String validateState;

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
	public void setComName(String comName) {
		this.comName = comName;
	}
	/**
	 * 获取：
	 */
	public String getComName() {
		return comName;
	}
	/**
	 * 设置：
	 */
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	/**
	 * 获取：
	 */
	public String getComCode() {
		return comCode;
	}
	/**
	 * 设置：0-未审核  1-已审核   9-账户余额不足   2-黑名单
	 */
	public void setStatue(String statue) {
		this.statue = statue;
	}
	/**
	 * 获取：0-未审核  1-已审核   9-账户余额不足   2-黑名单
	 */
	public String getStatue() {
		return statue;
	}
	/**
	 * 设置：企业类型 0-购买方 1-渠道商 2-供应商
	 */
	public void setComType(String comType) {
		this.comType = comType;
	}
	/**
	 * 获取：企业类型 0-购买方 1-渠道商 2-供应商
	 */
	public String getComType() {
		return comType;
	}
	/**
	 * 设置：
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：
	 */
	public void setEditeDate(Date editeDate) {
		this.editeDate = editeDate;
	}
	/**
	 * 获取：
	 */
	public Date getEditeDate() {
		return editeDate;
	}
	/**
	 * 设置：
	 */
	public void setInvComCode(String invComCode) {
		this.invComCode = invComCode;
	}
	/**
	 * 获取：
	 */
	public String getInvComCode() {
		return invComCode;
	}
	/**
	 * 设置：数据有效性 0-无效 1-有效
	 */
	public void setValidateState(String validateState) {
		this.validateState = validateState;
	}
	/**
	 * 获取：数据有效性 0-无效 1-有效
	 */
	public String getValidateState() {
		return validateState;
	}
}
