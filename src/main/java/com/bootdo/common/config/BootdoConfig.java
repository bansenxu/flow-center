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

}
