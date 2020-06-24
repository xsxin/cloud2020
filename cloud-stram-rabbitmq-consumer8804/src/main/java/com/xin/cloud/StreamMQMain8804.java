package com.xin.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: xsxin
 * @Date: 2020/6/24 18:12
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamMQMain8804 {

    public static void main(String[] args) {
        SpringApplication.run(StreamMQMain8804.class, args);
    }
}
