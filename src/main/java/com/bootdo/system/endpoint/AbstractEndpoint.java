package com.bootdo.system.endpoint;

import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.UserDO;
import org.keycloak.KeycloakPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public abstract class AbstractEndpoint {

    public final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDao userDao;

    public UserDO getUser() {
        KeycloakPrincipal userDetails = (KeycloakPrincipal) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getName();
        UserDO userDO = userDao.selectByUsername(username);
        return userDO;
    }

    public Long getUserId() {
        return getUser().getUserId();
    }

    public String getUsername() {
        return getUser().getUsername();
    }
}