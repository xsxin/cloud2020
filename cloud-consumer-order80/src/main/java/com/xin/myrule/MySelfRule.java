package com.xin.myrule;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ribbon自定义负载均衡路由规则类
 * tip:自定义配置类不能放在@ComponentScan所扫描的包以及子包下。
 *     否则我们的自定义配置类就会被所有的 ribbon客户端所共享。达不到特俗定制的目的
 * @author xsx
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        // 定义为轮询 算法规则：rest接口第几次请求数%服务集群数量 = 实际调用服务器位置下标。每次重启后rest接口从1计数
//        return new RoundRobinRule();
        // 随机
        return new RandomRule();
        // 重试
        //return new RetryRule();
        // 响应速度越快权重越大，越容易被选择
        //return new WeightedResponseTimeRule();
        // 过滤由于多次访问鼓掌而断掉的服务，选择并发小的服务
//        return new BestAvailableRule();
        // 过滤故障实例，再选择并发小的实例
//        return new AvailabilityFilteringRule();
        // 符合判断server所在区域性能和server的可用性选择
//        return new ZoneAvoidanceRule();
    }
}
