package com.bootdo.modules.flowable.resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bootdo.system.endpoint.AbstractEndpoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController  extends AbstractEndpoint {
	public Parametermap getParametermap() {
		Parametermap parametermap=new Parametermap(getRequest());
		//
		parametermap.put("shareniu", "分享牛");
		return parametermap;
		
	}
	
	public HttpSession getSession() {
		HttpSession session = this.getRequest().getSession();
		return session;
	}
	
	/**
	 * 获取HttpServletRequest
	 * @return
	 */
	public HttpServletRequest getRequest() {
		ServletRequestAttributes servletRequestAttributes= (org.springframework.web.context.request.ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		return request;
		
	}
	
}
