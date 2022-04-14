package com.ep.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/*
 *
 * 1：Access-Control-Allow-Headers: 服务器允许使用的字段，定位为"*"代表所有的都可以。
 * 2：Access-Control-Allow-Methods: 真实请求允许的方法，定位为"*"代表所有的都可以。例如：“POST”, “GET”, “PUT”, “OPTIONS”, “DELETE”。
 * 3：Access-Control-Allow-Credentials: 是否允许用户发送、处理 cookie
 * 4：Access-Control-Max-Age: 预检请求的有效期，单位为秒。有效期内，不会重复发送预检请求，这个可以在代码中配置，也可以不配置。
 * 5:定义一个过滤器:CorsFilter, 声明CORS 配置对所有接口都有效
 * */
@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
