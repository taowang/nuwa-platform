package com.study.platform.nacos;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置类上标注了@EnableDiscoveryClient这个注解，也就是说依赖这个依赖的微服务，都不要重复标注了
 */
@Configuration
@EnableDiscoveryClient
public class NacosAutoConfig {
}
