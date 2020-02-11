package com.bootdo.modules.bz.saleProduct.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-20 09:43:14
 */
public class SaleProductDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//产品名称
	private String productName;
	//产品编码
	private String productCode;
	//供应商编码
	private String gComCode;
	//产品单价
	private BigDecimal productPrice;
	//最小售卖价格
	private BigDecimal minPrice;
	//最大售卖价格
	private BigDecimal maxPrice;
	//分销商企业编码
	private String retailComCode;
	//状态  0-申请 1-审核中 2-审核通过 3-审核拒绝 9-作废
	private String state;
	//数据有效性 0-无效 1-有效
	private String validateState;
	//
	private Date createDate;
	//
	private Date editDate;
	//开始日期
	private Date startDate;
	//结束日期
	private Date endDate;

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
	 * 设置：产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取：产品名称
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置：产品编码
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * 获取：产品编码
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
	 * 设置：供应商编码
	 */
	public void setGComCode(String gComCode) {
		this.gComCode = gComCode;
	}
	/**
	 * 获取：供应商编码
	 */
	public String getGComCode() {
		return gComCode;
	}
	/**
	 * 设置：产品单价
	 */
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	/**
	 * 获取：产品单价
	 */
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	/**
	 * 设置：最小售卖价格
	 */
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}
	/**
	 * 获取：最小售卖价格
	 */
	public BigDecimal getMinPrice() {
		return minPrice;
	}
	/**
	 * 设置：最大售卖价格
	 */
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	/**
	 * 获取：最大售卖价格
	 */
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}
	/**
	 * 设置：分销商企业编码
	 */
	public void setRetailComCode(String retailComCode) {
		this.retailComCode = retailComCode;
	}
	/**
	 * 获取：分销商企业编码
	 */
	public String getRetailComCode() {
		return retailComCode;
	}
	/**
	 * 设置：状态  0-申请 1-审核中 2-审核通过 3-审核拒绝 9-作废
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：状态  0-申请 1-审核中 2-审核通过 3-审核拒绝 9-作废
	 */
	public String getState() {
		return state;
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
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	/**
	 * 获取：
	 */
	public Date getEditDate() {
		return editDate;
	}
	/**
	 * 设置：开始日期
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 获取：开始日期
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * 设置：结束日期
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：结束日期
	 */
	public Date getEndDate() {
		return endDate;
	}
}
