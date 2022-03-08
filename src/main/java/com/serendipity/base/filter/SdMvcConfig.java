package com.serendipity.base.filter;

import com.serendipity.core.utils.redis.RedisStringUtil;
import com.serendipity.web.config.BaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Auther bin
 */
@Configuration
public class SdMvcConfig implements WebMvcConfigurer {

    @Resource
    private BaseProperties config;
    @Autowired
    private RedisStringUtil redisStringUtil;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(this.config,this.redisStringUtil)).addPathPatterns("/**")
                .excludePathPatterns("/*/login").excludePathPatterns("/swagger-resources");
    }
}
