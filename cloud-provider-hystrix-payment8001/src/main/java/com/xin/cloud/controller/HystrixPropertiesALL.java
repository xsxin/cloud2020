package com.xin.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * Hystrix所有的配置信息
 * @author xsx
 */
public class HystrixPropertiesALL {


    @HystrixCommand(fallbackMethod = "fallback_method",
        groupKey = "strGroupCommand",
        commandKey = "strCommand",
        threadPoolKey = "strThreadPool",
        commandProperties = {
            //执行隔离时要使用的隔离策略 THREAD:线程池 SEMAPHORE：信号隔离池
            @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
            // 当隔离策略选择信号隔离池时，设置信号池大小（最大并发数）
            @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "10"),
            // 设置命令执行的超时时间
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10"),
                // 是否启用超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "true"),
                // 执行超时的时候是否中断
            @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
                // 执行被取消的时候是否中断
            @HystrixProperty(name = "execution.isolation.thread.interruptOnFutureCancel", value = "true"),
                // 允许回调方法执行的最大并发数
            @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "10"),
                // 服务降级是否启用，是否执行回调函数
            @HystrixProperty(name = "fallback.enabled", value = "true"),
                // 是否启用断路器
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                // 该属性设置为在滚动时间窗中， 断路器熔断的最小请求数。
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),
                // 在滚动时间窗中，请求数量超过requestVolumeThreshold的请求数，如果错误请求数超过百分之50%。
                // 断路器打开，否则就设置为关闭状态
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                // 该属性表示断路器打开的休眠时间窗，休眠时间窗结束后，会将断路器设置为“半开”状态，尝试熔断的请求命令。
                // 如果依然失败就将断路器继续设置为“打开”状态，如果成功就设置为“关闭”状态
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
                // 断路器强制打开
            @HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
                // 滚动时间窗设置，改时间用于断路器判断健康度时需要收集信息的持续时间
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
                // 滚动时间窗统计指标信息划分“桶”的数量，断路器在收集指标信息的时候会根据设置的时间窗长度拆分成多个桶来累计各度量值。
                // 每个桶记录了一段时间内的采集指标。timeInMilliseconds必须能被numBuckets整除，否则会报错
            @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "10"),
                // 设置对命令执行的延迟是否使用百分位数来跟踪和计算，如果设置为false，那么所有的概要统计都将返回-1
            @HystrixProperty(name = "metrics.rollingPercentile.enabled", value = "false"),
                // 该属性用来设置百分位统计的滚动窗口中的持续时间，单位为毫秒
            @HystrixProperty(name = "metrics.rollingPercentile.timeInMilliseconds", value = "60000"),
                // 该属性用来设置百分位统计滚动窗口中使用“桶”的数量
            @HystrixProperty(name = "metrics.rollingPercentile.numBuckets", value = "60000"),
                // 该属性用来设置在执行过程中每个“桶”中保留的最大执行次数，如果在滚动时间窗内发生超过该设定值的执行次数，就从最开始的位置重写。
            @HystrixProperty(name = "metrics.rollingPercentile.bucketSize", value = "100"),
                // 该属性用来设置采集影响断路器状态的健康快照。（请求的成功、错误百分比）的间隔等待时间
            @HystrixProperty(name = "metrics.healthSnapshot.intervalInMilliseconds", value = "500"),
                // 是否开启请求缓存
            @HystrixProperty(name = "requestCache.enabled", value = "true"),
                // hystrixCommand的执行和事件是否打印日志到HystrixRequestlog中
            @HystrixProperty(name = "requestLog.enabled", value = "true")
        },
        threadPoolProperties = {
            // 该参数用来设置执行命令线程池 核心线程数，该值也就是命令执行的最大并发数
            @HystrixProperty(name = "coreSize", value = "10"),
                // 该参数用来设置线程池最大队列大小，当设置为-1时，线程池使用 SynchronousQueue实现的队列，
                // 否则将使用LinkedBlockingQueue实现的队列
            @HystrixProperty(name = "maxQueueSize", value = "-1"),
                // 该参数用来为队列设置拒绝阈值，通过该参数，即使队列么有达到最大值也能拒绝请求。该参数主要是对LinkedBlockingQueue队列的补充，
                // 因为LinkedBlockingQueue队列不能动态修改它的对象大小。
            @HystrixProperty(name = "queueSizeRejectionThreshold", value = "5")
        })
    public String strConsumer() {
        return "hello 2020";
    }
}
