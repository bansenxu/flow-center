package com.bootdo.common.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by IntelliJ IDEA. <br/>
 * User: 牛玉贤 <br/>
 * Date: 18-8-2 <br/>
 * Time: 上午9:55 <br/>
 * Email: ncc0706@gmail.com <br/>
 * To change this template use File | Settings | File Templates.
 */
//@Configuration
//@EnableCaching
public class CacheConfiguration {

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("config/ehcache.xml"));
        factoryBean.setShared(true);//也说是说通过这个来设置cache的基地是这里的Spring独用,还是跟别的(如hibernate的Ehcache共享)
        return factoryBean;
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean factoryBean) {
        System.out.println("CacheConfiguration.ehcacheManager()");
        return new EhCacheCacheManager(factoryBean.getObject());

    }

}
