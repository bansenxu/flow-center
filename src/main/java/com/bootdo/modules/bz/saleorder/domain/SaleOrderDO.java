package com.bootdo.modules.bz.saleorder.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-21 14:34:42
 */
public class SaleOrderDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private String id;
	//分销记录id
	private String saleProductId;
	//买方企业名称
	private String buyComName;
	//买房企业编码
	private String buyComCode;
	//单价
	private BigDecimal price;
	//数量
	private Integer count;
	//产品编码
	private String productCode;
	//产品名称
	private String produceName;
	//票面金额
	private BigDecimal sumAmount;
	//卖方企业名称
	private String saleComName;
	//卖方企业编码
	private String saleComCode;
	//订单状态 0-申请状态 1-审核中 2-待支付 3-已支付 9-作废
	private String state;
	//
	private Date createDate;
	//
	private Date editeDate;
	//开票状态 0-未开票 1-已申请 2-已开票 9-作废
	private String taxState;
	//数据有效性 0-无效 1-有效
	private String validateState;

	/**
	 * 设置：id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：分销记录id
	 */
	public void setSaleProductId(String saleProductId) {
		this.saleProductId = saleProductId;
	}
	/**
	 * 获取：分销记录id
	 */
	public String getSaleProductId() {
		return saleProductId;
	}
	/**
	 * 设置：买方企业名称
	 */
	public void setBuyComName(String buyComName) {
		this.buyComName = buyComName;
	}
	/**
	 * 获取：买方企业名称
	 */
	public String getBuyComName() {
		return buyComName;
	}
	/**
	 * 设置：买房企业编码
	 */
	public void setBuyComCode(String buyComCode) {
		this.buyComCode = buyComCode;
	}
	/**
	 * 获取：买房企业编码
	 */
	public String getBuyComCode() {
		return buyComCode;
	}
	/**
	 * 设置：单价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：单价
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：数量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：数量
	 */
	public Integer getCount() {
		return count;
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
	 * 设置：产品名称
	 */
	public void setProduceName(String produceName) {
		this.produceName = produceName;
	}
	/**
	 * 获取：产品名称
	 */
	public String getProduceName() {
		return produceName;
	}
	/**
	 * 设置：票面金额
	 */
	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}
	/**
	 * 获取：票面金额
	 */
	public BigDecimal getSumAmount() {
		return sumAmount;
	}
	/**
	 * 设置：卖方企业名称
	 */
	public void setSaleComName(String saleComName) {
		this.saleComName = saleComName;
	}
	/**
	 * 获取：卖方企业名称
	 */
	public String getSaleComName() {
		return saleComName;
	}
	/**
	 * 设置：卖方企业编码
	 */
	public void setSaleComCode(String saleComCode) {
		this.saleComCode = saleComCode;
	}
	/**
	 * 获取：卖方企业编码
	 */
	public String getSaleComCode() {
		return saleComCode;
	}
	/**
	 * 设置：订单状态 0-申请状态 1-审核中 2-待支付 3-已支付 9-作废
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：订单状态 0-申请状态 1-审核中 2-待支付 3-已支付 9-作废
	 */
	public String getState() {
		return state;
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
	 * 设置：开票状态 0-未开票 1-已申请 2-已开票 9-作废
	 */
	public void setTaxState(String taxState) {
		this.taxState = taxState;
	}
	/**
	 * 获取：开票状态 0-未开票 1-已申请 2-已开票 9-作废
	 */
	public String getTaxState() {
		return taxState;
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
