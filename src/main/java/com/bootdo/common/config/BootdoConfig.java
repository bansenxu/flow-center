package com.bootdo.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "bootdo")
public class BootdoConfig {

    //上传路径
    private String uploadPath;
    
    private String flowable_url;
    
    private String flowable_username;
    
    private String flowable_password;
    
    private String flowable_driver_name;
    
    private String keycloak_username;
    
    private String keycloak_password;
    
    private String keycloak_url;
    
    private String keycloak_realmname;
    
    private String keycloak_client_id;

}
