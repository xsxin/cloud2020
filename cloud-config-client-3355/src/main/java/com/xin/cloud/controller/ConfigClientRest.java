package com.xin.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xsxin
 * @Date: 2020/6/22 17:27
 *
 * @RefreshScope 监控刷新
 */
@RestController
@RefreshScope
public class ConfigClientRest {

    @Value("${config.info}")
    private String applicationName;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return applicationName;
    }
}
