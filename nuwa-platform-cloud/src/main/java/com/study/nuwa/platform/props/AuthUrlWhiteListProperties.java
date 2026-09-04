package com.study.nuwa.platform.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 公共鉴权 url 配置。
 * <p>
 * 原位于 nuwa-platform-oauth2（已临时下线），移至 nuwa-platform-cloud 以便网关模块直接依赖。
 */
@Data
@Component
@ConfigurationProperties(prefix = "oauth-list")
public class AuthUrlWhiteListProperties {

    /**
     * 静态文件白名单
     */
    private List<String> staticFiles;

    /**
     * 白名单（无需鉴权的 url）
     */
    private List<String> whiteUrls;

    /**
     * 必须登录但每个角色都有的功能 url
     */
    private List<String> authUrls;

    /**
     * 交给认证中心处理的 url（如单点登录）
     */
    private List<String> tokenUrls;
}
