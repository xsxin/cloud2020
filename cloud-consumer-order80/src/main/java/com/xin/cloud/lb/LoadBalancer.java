package com.xin.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 手写ribbon负载均衡算法
 */
public interface LoadBalancer {

    public ServiceInstance instances(List<ServiceInstance> instanceList);
}
