package com.bootdo.modules.bz.shareRule.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-20 09:43:50
 */
public class ShareRuleDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//产品分销权记录id
	private String saleProductId;
	//计算基准 1-底价 2-毛利润 3-固定金额
	private String ruleType;
	//比例 存除以100的小数
	private String ruleRate;
	//描述
	private String memo;
	
	//分润企业编码
	private String inComCode;
	
	//数据有效性 0-无效 1-有效
	private String validateState;
	//
	private Date createDate;
	//
	private Date editeDate;

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
	 * 设置：产品分销权记录id
	 */
	public void setSaleProductId(String saleProductId) {
		this.saleProductId = saleProductId;
	}
	/**
	 * 获取：产品分销权记录id
	 */
	public String getSaleProductId() {
		return saleProductId;
	}
	/**
	 * 设置：计算基准 1-底价 2-毛利润 3-固定金额
	 */
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	/**
	 * 获取：计算基准 1-底价 2-毛利润 3-固定金额
	 */
	public String getRuleType() {
		return ruleType;
	}
	/**
	 * 设置：比例 存除以100的小数
	 */
	public void setRuleRate(String ruleRate) {
		this.ruleRate = ruleRate;
	}
	/**
	 * 获取：比例 存除以100的小数
	 */
	public String getRuleRate() {
		return ruleRate;
	}
	/**
	 * 设置：描述
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * 获取：描述
	 */
	public String getMemo() {
		return memo;
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
	public String getInComCode() {
		return inComCode;
	}
	public void setInComCode(String inComCode) {
		this.inComCode = inComCode;
	}
}
