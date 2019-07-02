package com.bootdo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication(
                exclude= {
                //org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
            //  DataSourceProcessEngineAutoConfiguration.class,
                        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
                        UserDetailsServiceAutoConfiguration.class,
                LiquibaseAutoConfiguration.class
                }
                )
@EnableAutoConfiguration(exclude={org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@ServletComponentScan
@MapperScan({"com.bootdo.*.dao","com.bootdo.modules.*.dao","org.flowable.ui.modeler.domain"})
@ComponentScan({"com.bootdo.modules.flowable", "com.bootdo","org.flowable.ui.modeler","org.flowable.ui.common"})
public class FlowCenterApplication {
    public static void main(String[] args) {
    	
        SpringApplication.run(FlowCenterApplication.class, args);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
