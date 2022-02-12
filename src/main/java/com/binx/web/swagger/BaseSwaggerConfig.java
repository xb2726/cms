package com.binx.web.swagger;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.binx.web.config.BaseProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther X .
 */
@Configuration
public abstract class BaseSwaggerConfig {

    protected static final String EMPTY = "";
    @Resource
    private BaseProperties properties;

    public BaseSwaggerConfig() {
    }

    protected ApiInfoBuilder buildApiInfo(ApiInfoBuilder builder) {
        return builder;
    }

    protected ApiSelectorBuilder buildApiSelector(ApiSelectorBuilder builder) {
        return builder;
    }

    private Docket customHeader(Docket docket) {
        List<Parameter> params = new ArrayList();
        Parameter header = (new ParameterBuilder()).name("Token").description("用户凭证").modelRef(new ModelRef("string")).parameterType("header").required(false).defaultValue(this.properties.getSwaggerToken()).build();
        params.add(header);
        return docket.globalOperationParameters(params);
    }

    private ApiInfo apiInfo(String title, String description, String version) {
        ApiInfoBuilder builder = (new ApiInfoBuilder()).title(title).description(description);
        if (!StrUtil.isEmpty(version)) {
            builder.version(version);
        }

        builder = this.buildApiInfo(builder);
        return builder.build();
    }

    protected Docket getDocket(String apiName, String basePackage) {
        return this.getDocket(apiName, "", basePackage);
    }

    protected Docket getDocket(String apiName, String version, String basePackage) {
        return this.getDocket(apiName, version, basePackage, "");
    }

    protected Docket getGroupDocket(String apiName, String version, String basePackage) {
        return this.getDocket(apiName, version, basePackage, apiName);
    }

    protected Docket getDocket(String apiName, String version, String basePackage, String groupName) {
        return this.getDocket(apiName, String.format("%s接口文档", apiName), version, basePackage, groupName);
    }

    protected Docket getDocket(String apiName, String apiDesc, String version, String basePackage, String groupName, String... paths) {
        Docket docket = (new Docket(DocumentationType.SWAGGER_2)).apiInfo(this.apiInfo(apiName, apiDesc, version));
        if (!StrUtil.isEmpty(groupName)) {
            docket.groupName(groupName);
        }

        docket.pathMapping(this.properties.getSwaggerBasePath());
        ApiSelectorBuilder builder = docket.select().apis(RequestHandlerSelectors.basePackage(basePackage));
        if (ArrayUtil.isEmpty(paths)) {
            builder.paths(PathSelectors.any());
        } else {
            StringBuilder pathRegex = new StringBuilder();
            String[] var10 = paths;
            int var11 = paths.length;

            for(int var12 = 0; var12 < var11; ++var12) {
                String path = var10[var12];
                pathRegex.append("(").append(path).append(")|");
            }

            builder.paths(PathSelectors.regex(pathRegex.substring(0, pathRegex.length() - 1)));
        }

        builder = this.buildApiSelector(builder);
        docket = builder.build();
        return this.customHeader(docket);
    }

}
