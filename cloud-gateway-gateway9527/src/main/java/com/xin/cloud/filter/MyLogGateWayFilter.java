package com.xin.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @Author: xsxin
 * @Date: 2020/6/22 15:20
 *
 *  自定义配置全局filter拦截器
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {

    /**
     * filter验证参数是否携带uname
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("********come in MyLogGateWayFilter: " + new Date());
        // 获取参数值
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if (uname == null){
            log.info("********用户名为null，非法用户 /(ㄒoㄒ)/~~");
            // 设置返回状态编码
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        // 验证通过时进行下一个filter验证
        return chain.filter(exchange);
    }

    /**
     * 设置filter优先级 数值越小优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
