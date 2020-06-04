package com.xin.cloud;

import com.xin.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @RibbonClient:启用自定义算法规则
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PATMENT-SERVICE", configuration = MySelfRule.class)
public class CloudConsumerOrder80Application {

	public static void main(String[] args) {
		SpringApplication.run(CloudConsumerOrder80Application.class, args);
	}

}
