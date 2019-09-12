package com.bootdo.modules.flowable.config;


import org.flowable.idm.api.User;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.flowable.ui.common.security.SecurityUtils;
import org.keycloak.KeycloakPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShareniuHandlerInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShareniuHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String servletPath = request.getServletPath();

        if (servletPath.endsWith(".css") || servletPath.endsWith(".js") || servletPath.endsWith(".jpg") || servletPath.endsWith(".png")) {
            return true;
        }
        try{
        	KeycloakPrincipal userDetails = (KeycloakPrincipal) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
            String username = userDetails == null ? "" : userDetails.getName();
            LOGGER.info("ShareniuHandlerInterceptor:preHandle:username,{}", username);
            if (servletPath.startsWith("/app")) {
                User user = new UserEntityImpl();
                user.setId(username);
                SecurityUtils.assumeUser(user);
            }
        }catch(Exception e)
        {
        	System.out.println("keycloak 用户获取失败");
        }
        
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
