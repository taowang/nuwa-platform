package com.study.platform.cloud.ribbon.config;


import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Ribbon公共负载均衡策略配置
 */
@Configuration
public class RibbonConfig {

    /**
     * 负载均衡策略配置
     * @return
     */
    @Bean
    public IRule rule(){
        //轮询策略
        return new RoundRobinRule();
        //随机策略  从所有可用的提供者中随机选择一个
        //return new RandomRule();
    }

}