package com.study.nuwa.platform.ribbon.config;

import org.springframework.context.annotation.Configuration;

/**
 * 原 Ribbon 负载均衡策略配置。
 * <p>
 * Spring Cloud 2022+ 起 Netflix Ribbon 不再维护，Spring Cloud LoadBalancer 取代之。
 * 如需自定义负载均衡策略，请使用
 * {@code org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient} 或
 * 实现 {@code org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer}。
 * <p>
 * 保留此类仅为兼容旧 import，方法体留空。
 *
 * @deprecated 升级到 Spring Cloud LoadBalancer
 */
@Configuration
@Deprecated
public class RibbonConfig {
}
