package com.bootdo.modules.bz.product.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-18 17:03:39
 */
public class ProductDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//产品名称
	private String productName;
	//产品类型
	private String productType;
	//产品编码
	private String productCode;
	//产品底价
	private String price;
	//
	private String memo;
	//建议价格
	private String sugPrice;
	//最低价格
	private String minPrice;
	//最高价格
	private String maxPrice;
	//
	private Date createDate;
	//
	private Date editeDate;
	//数据有效性 0-无效 1-有效
	private String validateState;
	//产品状态 0-申请 1-审核中 2-上架 3-审核拒绝 9-下架
	private String state;

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
	 * 设置：产品类型
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * 获取：产品类型
	 */
	public String getProductType() {
		return productType;
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
	 * 设置：产品底价
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * 获取：产品底价
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * 设置：
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * 获取：
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * 设置：建议价格
	 */
	public void setSugPrice(String sugPrice) {
		this.sugPrice = sugPrice;
	}
	/**
	 * 获取：建议价格
	 */
	public String getSugPrice() {
		return sugPrice;
	}
	/**
	 * 设置：最低价格
	 */
	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}
	/**
	 * 获取：最低价格
	 */
	public String getMinPrice() {
		return minPrice;
	}
	/**
	 * 设置：最高价格
	 */
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
	/**
	 * 获取：最高价格
	 */
	public String getMaxPrice() {
		return maxPrice;
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
	/**
	 * 设置：产品状态 0-申请 1-审核中 2-上架 3-审核拒绝 9-下架
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：产品状态 0-申请 1-审核中 2-上架 3-审核拒绝 9-下架
	 */
	public String getState() {
		return state;
	}
}
