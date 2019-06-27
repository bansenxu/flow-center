package com.bootdo.modules.flowable.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-13 14:52:13
 */
public class HiTaskinstDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String procDefId;
	//
	private String taskDefKey;
	//
	private String procInstId;
	//
	private String executionId;
	//
	private String name;
	//
	private String parentTaskId;
	//
	private String description;
	//
	private String owner;
	//
	private String assignee;
	//
	private Date startTime;
	//
	private Date claimTime;
	//
	private Date endTime;
	//
	private Long duration;
	//
	private String deleteReason;
	//
	private Integer priority;
	//
	private Date dueDate;
	//
	private String formKey;
	//
	private String category;
	//
	private String tenantId;
	//
	private Integer rev;
	//
	private Date lastUpdatedTime;
	//
	private String scopeId;
	//
	private String subScopeId;
	//
	private String scopeType;
	//
	private String scopeDefinitionId;
	//
	private String taskDefId;

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
	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}
	/**
	 * 获取：
	 */
	public String getProcDefId() {
		return procDefId;
	}
	/**
	 * 设置：
	 */
	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey;
	}
	/**
	 * 获取：
	 */
	public String getTaskDefKey() {
		return taskDefKey;
	}
	/**
	 * 设置：
	 */
	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
	/**
	 * 获取：
	 */
	public String getProcInstId() {
		return procInstId;
	}
	/**
	 * 设置：
	 */
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	/**
	 * 获取：
	 */
	public String getExecutionId() {
		return executionId;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}
	/**
	 * 获取：
	 */
	public String getParentTaskId() {
		return parentTaskId;
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
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * 获取：
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * 设置：
	 */
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	/**
	 * 获取：
	 */
	public String getAssignee() {
		return assignee;
	}
	/**
	 * 设置：
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：
	 */
	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
	}
	/**
	 * 获取：
	 */
	public Date getClaimTime() {
		return claimTime;
	}
	/**
	 * 设置：
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置：
	 */
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	/**
	 * 获取：
	 */
	public Long getDuration() {
		return duration;
	}
	/**
	 * 设置：
	 */
	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}
	/**
	 * 获取：
	 */
	public String getDeleteReason() {
		return deleteReason;
	}
	/**
	 * 设置：
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	/**
	 * 获取：
	 */
	public Integer getPriority() {
		return priority;
	}
	/**
	 * 设置：
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * 获取：
	 */
	public Date getDueDate() {
		return dueDate;
	}
	/**
	 * 设置：
	 */
	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}
	/**
	 * 获取：
	 */
	public String getFormKey() {
		return formKey;
	}
	/**
	 * 设置：
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * 获取：
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * 设置：
	 */
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	/**
	 * 获取：
	 */
	public String getTenantId() {
		return tenantId;
	}
	/**
	 * 设置：
	 */
	public void setRev(Integer rev) {
		this.rev = rev;
	}
	/**
	 * 获取：
	 */
	public Integer getRev() {
		return rev;
	}
	/**
	 * 设置：
	 */
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	/**
	 * 获取：
	 */
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	/**
	 * 设置：
	 */
	public void setScopeId(String scopeId) {
		this.scopeId = scopeId;
	}
	/**
	 * 获取：
	 */
	public String getScopeId() {
		return scopeId;
	}
	/**
	 * 设置：
	 */
	public void setSubScopeId(String subScopeId) {
		this.subScopeId = subScopeId;
	}
	/**
	 * 获取：
	 */
	public String getSubScopeId() {
		return subScopeId;
	}
	/**
	 * 设置：
	 */
	public void setScopeType(String scopeType) {
		this.scopeType = scopeType;
	}
	/**
	 * 获取：
	 */
	public String getScopeType() {
		return scopeType;
	}
	/**
	 * 设置：
	 */
	public void setScopeDefinitionId(String scopeDefinitionId) {
		this.scopeDefinitionId = scopeDefinitionId;
	}
	/**
	 * 获取：
	 */
	public String getScopeDefinitionId() {
		return scopeDefinitionId;
	}
	/**
	 * 设置：
	 */
	public void setTaskDefId(String taskDefId) {
		this.taskDefId = taskDefId;
	}
	/**
	 * 获取：
	 */
	public String getTaskDefId() {
		return taskDefId;
	}
}
