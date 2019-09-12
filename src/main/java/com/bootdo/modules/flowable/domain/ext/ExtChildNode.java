package com.bootdo.modules.flowable.domain.ext;

import java.io.Serializable;

/*次顶层DO*/
public class ExtChildNode  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String resourceId;
	private ExtProperty properties;
	private String stencil;
	private String childShapes;
	private String outgoing;
	private String bounds;
	private String dockers;
	
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public ExtProperty getProperties() {
		return properties;
	}
	public void setProperties(ExtProperty properties) {
		this.properties = properties;
	}
	public String getStencil() {
		return stencil;
	}
	public void setStencil(String stencil) {
		this.stencil = stencil;
	}
	public String getChildShapes() {
		return childShapes;
	}
	public void setChildShapes(String childShapes) {
		this.childShapes = childShapes;
	}
	public String getOutgoing() {
		return outgoing;
	}
	public void setOutgoing(String outgoing) {
		this.outgoing = outgoing;
	}
	public String getBounds() {
		return bounds;
	}
	public void setBounds(String bounds) {
		this.bounds = bounds;
	}
	public String getDockers() {
		return dockers;
	}
	public void setDockers(String dockers) {
		this.dockers = dockers;
	}

}
