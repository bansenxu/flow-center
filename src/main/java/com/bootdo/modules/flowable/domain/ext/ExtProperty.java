package com.bootdo.modules.flowable.domain.ext;

import java.io.Serializable;

/*第三层DO*/
public class ExtProperty  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String overrideid;
	private String name;
	private String documentation;
	private String asynchronousdefinition;
	private String exclusivedefinition;
	private String servicetasktriggerable;
	private String executionlisteners;
	private String multiinstance_type;
	private String multiinstance_cardinality;
	private String multiinstance_collection;
	private String multiinstance_variable;
	private String multiinstance_condition;
	private String isforcompensation;
	private String servicetaskclass;
	private String servicetaskexpression;
	private String servicetaskdelegateexpression;
	private String servicetaskfields;
	private String servicetaskresultvariable;
	private String servicetaskuselocalscopeforresultvariable;
	private String skipexpression;
	
	public String getOverrideid() {
		return overrideid;
	}
	public void setOverrideid(String overrideid) {
		this.overrideid = overrideid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDocumentation() {
		return documentation;
	}
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	public String getAsynchronousdefinition() {
		return asynchronousdefinition;
	}
	public void setAsynchronousdefinition(String asynchronousdefinition) {
		this.asynchronousdefinition = asynchronousdefinition;
	}
	public String getExclusivedefinition() {
		return exclusivedefinition;
	}
	public void setExclusivedefinition(String exclusivedefinition) {
		this.exclusivedefinition = exclusivedefinition;
	}
	public String getServicetasktriggerable() {
		return servicetasktriggerable;
	}
	public void setServicetasktriggerable(String servicetasktriggerable) {
		this.servicetasktriggerable = servicetasktriggerable;
	}
	public String getExecutionlisteners() {
		return executionlisteners;
	}
	public void setExecutionlisteners(String executionlisteners) {
		this.executionlisteners = executionlisteners;
	}
	public String getMultiinstance_type() {
		return multiinstance_type;
	}
	public void setMultiinstance_type(String multiinstance_type) {
		this.multiinstance_type = multiinstance_type;
	}
	public String getMultiinstance_cardinality() {
		return multiinstance_cardinality;
	}
	public void setMultiinstance_cardinality(String multiinstance_cardinality) {
		this.multiinstance_cardinality = multiinstance_cardinality;
	}
	public String getMultiinstance_collection() {
		return multiinstance_collection;
	}
	public void setMultiinstance_collection(String multiinstance_collection) {
		this.multiinstance_collection = multiinstance_collection;
	}
	public String getMultiinstance_variable() {
		return multiinstance_variable;
	}
	public void setMultiinstance_variable(String multiinstance_variable) {
		this.multiinstance_variable = multiinstance_variable;
	}
	public String getMultiinstance_condition() {
		return multiinstance_condition;
	}
	public void setMultiinstance_condition(String multiinstance_condition) {
		this.multiinstance_condition = multiinstance_condition;
	}
	public String getIsforcompensation() {
		return isforcompensation;
	}
	public void setIsforcompensation(String isforcompensation) {
		this.isforcompensation = isforcompensation;
	}
	public String getServicetaskclass() {
		return servicetaskclass;
	}
	public void setServicetaskclass(String servicetaskclass) {
		this.servicetaskclass = servicetaskclass;
	}
	public String getServicetaskexpression() {
		return servicetaskexpression;
	}
	public void setServicetaskexpression(String servicetaskexpression) {
		this.servicetaskexpression = servicetaskexpression;
	}
	public String getServicetaskdelegateexpression() {
		return servicetaskdelegateexpression;
	}
	public void setServicetaskdelegateexpression(String servicetaskdelegateexpression) {
		this.servicetaskdelegateexpression = servicetaskdelegateexpression;
	}
	public String getServicetaskfields() {
		return servicetaskfields;
	}
	public void setServicetaskfields(String servicetaskfields) {
		this.servicetaskfields = servicetaskfields;
	}
	public String getServicetaskresultvariable() {
		return servicetaskresultvariable;
	}
	public void setServicetaskresultvariable(String servicetaskresultvariable) {
		this.servicetaskresultvariable = servicetaskresultvariable;
	}
	public String getServicetaskuselocalscopeforresultvariable() {
		return servicetaskuselocalscopeforresultvariable;
	}
	public void setServicetaskuselocalscopeforresultvariable(String servicetaskuselocalscopeforresultvariable) {
		this.servicetaskuselocalscopeforresultvariable = servicetaskuselocalscopeforresultvariable;
	}
	public String getSkipexpression() {
		return skipexpression;
	}
	public void setSkipexpression(String skipexpression) {
		this.skipexpression = skipexpression;
	}

}
