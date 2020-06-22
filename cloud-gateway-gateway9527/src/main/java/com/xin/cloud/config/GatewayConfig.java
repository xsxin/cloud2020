package com.xin.cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: xsxin
 * @Date: 2020/6/22 11:05
 *
 * 配置gateway网关路由转发规则
 */
@Configuration
public class GatewayConfig {

    /**
     * 创建一个ID为path_route 的路由转发规则。请求路径为/guonei时，转发到https://news.baidu.com/guonei地址
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {

        return routeLocatorBuilder.routes()
                .route("path_route", r -> r.path("/guonei").uri("https://news.baidu.com/guonei")).build();
    }
}
