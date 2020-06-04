package com.xin.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author xsx
 */
@Configuration
public class ApplicationContextConfig {

    /**
     * 注入RestTemplate模板
     * @LoadBalanced 赋予Resttemplate负载均衡能力
     * @return
     */
    @Bean
//    @LoadBalanced  注释默认轮训算法
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
