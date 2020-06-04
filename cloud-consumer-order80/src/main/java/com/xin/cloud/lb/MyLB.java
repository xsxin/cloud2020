package com.xin.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 手写负载均衡算法
 * @author 0
 */
@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 使用cas获取请求次数
     * @return
     */
    private final int getAntIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("******第几次访问， 次数next :" + next);
        return next;
    }

    /**
     * 算法规则：rest接口第几次请求数%服务集群数量 = 实际调用服务器位置下标。每次重启后rest接口从1计数
     * @param instanceList
     * @return
     */
    @Override
    public ServiceInstance instances(List<ServiceInstance> instanceList) {
        int index = getAntIncrement() % instanceList.size();
        return instanceList.get(index);
    }
}
