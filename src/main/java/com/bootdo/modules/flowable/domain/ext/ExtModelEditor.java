package com.bootdo.modules.flowable.domain.ext;

import java.io.Serializable;
import java.util.List;

/*顶层ModelDO*/
public class ExtModelEditor  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String modelId;
	private List<ExtChildNode> childShapes;
	private String bounds;
	private String properties;
	private String stencil;
	private String stencilset;
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public List<ExtChildNode> getChildShapes() {
		return childShapes;
	}
	public void setChildShapes(List<ExtChildNode> childShapes) {
		this.childShapes = childShapes;
	}
	public String getBounds() {
		return bounds;
	}
	public void setBounds(String bounds) {
		this.bounds = bounds;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public String getStencil() {
		return stencil;
	}
	public void setStencil(String stencil) {
		this.stencil = stencil;
	}
	public String getStencilset() {
		return stencilset;
	}
	public void setStencilset(String stencilset) {
		this.stencilset = stencilset;
	}

}
