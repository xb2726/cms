package com.binx.cms.config;

import com.binx.core.Constants;
import com.binx.web.swagger.BaseSwaggerConfig;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther X .
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig extends BaseSwaggerConfig{
    private String getPackage(String name) {
        String basePackage = "com.binx.cms.controller";
        return basePackage.concat(".").concat(name);
    }

    @Override
    protected ApiInfoBuilder buildApiInfo(ApiInfoBuilder builder) {
        return builder.contact(new Contact("X .", "", ""));
    }

/*    @Bean
    @Profile({Constants.MODE_DEV, Constants.MODE_TEST})
    public Docket managementWebDocket() {
        return getDocket("管理平台-安防", "1.0.0", getPackage("management"), "management");
    }*/

    @Bean
    @Profile({Constants.MODE_DEV, Constants.MODE_TEST})
    public Docket cmsDocket() {
        return getDocket("cms框架", "1.0.0", getPackage("cms"), "cms");
    }

}
