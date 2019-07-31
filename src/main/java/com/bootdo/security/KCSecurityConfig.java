package com.bootdo.security;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

/**
 * Created by IntelliJ IDEA. <br/>
 * User: 牛玉贤 <br/>
 * Date: 18-8-6 <br/>
 * Time: 下午11:49 <br/>
 * Email: ncc0706@gmail.com <br/>
 * To change this template use File | Settings | File Templates.
 */

@KeycloakConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class KCSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/js/**")
                .antMatchers("/assets/**")
                .antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                // 前端显示使用了iframe
                .headers().frameOptions().disable()
                .and()
                //.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/error/**").permitAll()
                .antMatchers("/flowable/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/sso/logout").logoutSuccessUrl("/")
                .and()
                    .exceptionHandling().accessDeniedPage("/error/403")
        ;
        http.csrf().disable();
    }
}
