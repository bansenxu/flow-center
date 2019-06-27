package com.bootdo.modules.flowable.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-10 19:40:02
 */
public class RuExecutionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private Integer rev;
	//
	private String procInstId;
	//
	private String businessKey;
	//
	private String parentId;
	//
	private String procDefId;
	//
	private String superExec;
	//
	private String actId;
	//
	private Integer isActive;
	//
	private Integer isConcurrent;
	//
	private Integer isScope;
	//
	private Integer isEventScope;
	//
	private Integer suspensionState;
	//
	private Integer cachedEntState;
	//
	private String tenantId;
	//
	private String name;
	//
	private Date lockTime;
	//
	private String rootProcInstId;
	//
	private Integer isMiRoot;
	//
	private Date startTime;
	//
	private String startUserId;
	//
	private Integer isCountEnabled;
	//
	private Integer evtSubscrCount;
	//
	private Integer taskCount;
	//
	private Integer jobCount;
	//
	private Integer timerJobCount;
	//
	private Integer suspJobCount;
	//
	private Integer deadletterJobCount;
	//
	private Integer varCount;
	//
	private Integer idLinkCount;
	//
	private String startActId;
	//
	private String callbackId;
	//
	private String callbackType;

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
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	/**
	 * 获取：
	 */
	public String getBusinessKey() {
		return businessKey;
	}
	/**
	 * 设置：
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：
	 */
	public String getParentId() {
		return parentId;
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
	public void setSuperExec(String superExec) {
		this.superExec = superExec;
	}
	/**
	 * 获取：
	 */
	public String getSuperExec() {
		return superExec;
	}
	/**
	 * 设置：
	 */
	public void setActId(String actId) {
		this.actId = actId;
	}
	/**
	 * 获取：
	 */
	public String getActId() {
		return actId;
	}
	/**
	 * 设置：
	 */
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	/**
	 * 获取：
	 */
	public Integer getIsActive() {
		return isActive;
	}
	/**
	 * 设置：
	 */
	public void setIsConcurrent(Integer isConcurrent) {
		this.isConcurrent = isConcurrent;
	}
	/**
	 * 获取：
	 */
	public Integer getIsConcurrent() {
		return isConcurrent;
	}
	/**
	 * 设置：
	 */
	public void setIsScope(Integer isScope) {
		this.isScope = isScope;
	}
	/**
	 * 获取：
	 */
	public Integer getIsScope() {
		return isScope;
	}
	/**
	 * 设置：
	 */
	public void setIsEventScope(Integer isEventScope) {
		this.isEventScope = isEventScope;
	}
	/**
	 * 获取：
	 */
	public Integer getIsEventScope() {
		return isEventScope;
	}
	/**
	 * 设置：
	 */
	public void setSuspensionState(Integer suspensionState) {
		this.suspensionState = suspensionState;
	}
	/**
	 * 获取：
	 */
	public Integer getSuspensionState() {
		return suspensionState;
	}
	/**
	 * 设置：
	 */
	public void setCachedEntState(Integer cachedEntState) {
		this.cachedEntState = cachedEntState;
	}
	/**
	 * 获取：
	 */
	public Integer getCachedEntState() {
		return cachedEntState;
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
	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}
	/**
	 * 获取：
	 */
	public Date getLockTime() {
		return lockTime;
	}
	/**
	 * 设置：
	 */
	public void setRootProcInstId(String rootProcInstId) {
		this.rootProcInstId = rootProcInstId;
	}
	/**
	 * 获取：
	 */
	public String getRootProcInstId() {
		return rootProcInstId;
	}
	/**
	 * 设置：
	 */
	public void setIsMiRoot(Integer isMiRoot) {
		this.isMiRoot = isMiRoot;
	}
	/**
	 * 获取：
	 */
	public Integer getIsMiRoot() {
		return isMiRoot;
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
	public void setStartUserId(String startUserId) {
		this.startUserId = startUserId;
	}
	/**
	 * 获取：
	 */
	public String getStartUserId() {
		return startUserId;
	}
	/**
	 * 设置：
	 */
	public void setIsCountEnabled(Integer isCountEnabled) {
		this.isCountEnabled = isCountEnabled;
	}
	/**
	 * 获取：
	 */
	public Integer getIsCountEnabled() {
		return isCountEnabled;
	}
	/**
	 * 设置：
	 */
	public void setEvtSubscrCount(Integer evtSubscrCount) {
		this.evtSubscrCount = evtSubscrCount;
	}
	/**
	 * 获取：
	 */
	public Integer getEvtSubscrCount() {
		return evtSubscrCount;
	}
	/**
	 * 设置：
	 */
	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}
	/**
	 * 获取：
	 */
	public Integer getTaskCount() {
		return taskCount;
	}
	/**
	 * 设置：
	 */
	public void setJobCount(Integer jobCount) {
		this.jobCount = jobCount;
	}
	/**
	 * 获取：
	 */
	public Integer getJobCount() {
		return jobCount;
	}
	/**
	 * 设置：
	 */
	public void setTimerJobCount(Integer timerJobCount) {
		this.timerJobCount = timerJobCount;
	}
	/**
	 * 获取：
	 */
	public Integer getTimerJobCount() {
		return timerJobCount;
	}
	/**
	 * 设置：
	 */
	public void setSuspJobCount(Integer suspJobCount) {
		this.suspJobCount = suspJobCount;
	}
	/**
	 * 获取：
	 */
	public Integer getSuspJobCount() {
		return suspJobCount;
	}
	/**
	 * 设置：
	 */
	public void setDeadletterJobCount(Integer deadletterJobCount) {
		this.deadletterJobCount = deadletterJobCount;
	}
	/**
	 * 获取：
	 */
	public Integer getDeadletterJobCount() {
		return deadletterJobCount;
	}
	/**
	 * 设置：
	 */
	public void setVarCount(Integer varCount) {
		this.varCount = varCount;
	}
	/**
	 * 获取：
	 */
	public Integer getVarCount() {
		return varCount;
	}
	/**
	 * 设置：
	 */
	public void setIdLinkCount(Integer idLinkCount) {
		this.idLinkCount = idLinkCount;
	}
	/**
	 * 获取：
	 */
	public Integer getIdLinkCount() {
		return idLinkCount;
	}
	/**
	 * 设置：
	 */
	public void setStartActId(String startActId) {
		this.startActId = startActId;
	}
	/**
	 * 获取：
	 */
	public String getStartActId() {
		return startActId;
	}
	/**
	 * 设置：
	 */
	public void setCallbackId(String callbackId) {
		this.callbackId = callbackId;
	}
	/**
	 * 获取：
	 */
	public String getCallbackId() {
		return callbackId;
	}
	/**
	 * 设置：
	 */
	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}
	/**
	 * 获取：
	 */
	public String getCallbackType() {
		return callbackType;
	}
}
