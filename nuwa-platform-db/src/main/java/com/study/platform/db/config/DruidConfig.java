package com.study.platform.db.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @ClassName: DruidConfig
 * @DESCRIPTION:
 * @author: 西门
 * @create: 2022-10-08 10:12 下午
 **/

@Configuration
public class DruidConfig {

    /*
     * 解决druid 日志报错：discard long time none received connection:xxx
     * */
    @PostConstruct
    public void setProperties(){
        System.setProperty("druid.mysql.usePingMethod","false");
    }
}
