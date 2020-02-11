package com.bootdo.modules.bz.account.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-19 10:43:14
 */
public class AccountDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String comCode;
	//
	private String accountCode;
	//现金余额
	private BigDecimal balance;
	//虚拟币余额
	private BigDecimal couBalance;
	//额度余额
	private BigDecimal creBalance;
	//冻结金额
	private BigDecimal iceAmount;
	//可开票余额
	private BigDecimal recBalance;
	//
	private Date createDate;
	//
	private Date editeDate;
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
	 * 设置：
	 */
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	/**
	 * 获取：
	 */
	public String getAccountCode() {
		return accountCode;
	}
	/**
	 * 设置：现金余额
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	/**
	 * 获取：现金余额
	 */
	public BigDecimal getBalance() {
		return balance;
	}
	/**
	 * 设置：虚拟币余额
	 */
	public void setCouBalance(BigDecimal couBalance) {
		this.couBalance = couBalance;
	}
	/**
	 * 获取：虚拟币余额
	 */
	public BigDecimal getCouBalance() {
		return couBalance;
	}
	/**
	 * 设置：额度余额
	 */
	public void setCreBalance(BigDecimal creBalance) {
		this.creBalance = creBalance;
	}
	/**
	 * 获取：额度余额
	 */
	public BigDecimal getCreBalance() {
		return creBalance;
	}
	/**
	 * 设置：冻结金额
	 */
	public void setIceAmount(BigDecimal iceAmount) {
		this.iceAmount = iceAmount;
	}
	/**
	 * 获取：冻结金额
	 */
	public BigDecimal getIceAmount() {
		return iceAmount;
	}
	/**
	 * 设置：可开票余额
	 */
	public void setRecBalance(BigDecimal recBalance) {
		this.recBalance = recBalance;
	}
	/**
	 * 获取：可开票余额
	 */
	public BigDecimal getRecBalance() {
		return recBalance;
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
