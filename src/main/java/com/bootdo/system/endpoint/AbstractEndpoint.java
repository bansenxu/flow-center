package com.bootdo.system.endpoint;

import org.keycloak.KeycloakPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;


public abstract class AbstractEndpoint {

    public final Logger logger = LoggerFactory.getLogger(getClass());
}