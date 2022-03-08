package com.serendipity.cms.config;

import com.serendipity.core.utils.CommonUtils;
import com.serendipity.web.config.BaseProperties;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther bin     跨域
 */
@SpringBootConfiguration
public class GlobalCorsFilter implements Filter {
    @Resource
    private BaseProperties properties;
    private static final String STR_ANY = "*";



    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (this.properties.isCorsEnable() && servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest)servletRequest;
            String origin = this.properties.getCorsOrigin();
            if ("*".equals(origin)) {
                origin = request.getHeader("Origin");
            }

            String headers = this.properties.getCorsHeaders();
            String requestHeaders = request.getHeader("Access-Control-Request-Headers");
            if ("*".equals(headers) && !CommonUtils.isEmpty(requestHeaders)) {
                headers = requestHeaders;
            }

            HttpServletResponse response = (HttpServletResponse)servletResponse;
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Origin", origin);
            response.addHeader("Access-Control-Allow-Methods", this.properties.getCorsMethods());
            response.addHeader("Access-Control-Allow-Headers", headers);
            if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
                response.getWriter().println("ok");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
