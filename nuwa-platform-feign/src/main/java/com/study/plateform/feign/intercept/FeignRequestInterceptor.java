package com.study.plateform.feign.intercept;

import cn.hutool.core.util.StrUtil;
import com.study.plateform.feign.utils.RequestContextUtils;
import com.study.platform.base.constant.TokenConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 解决feign中的令牌中继问题
 */
@Component
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        HttpServletRequest httpServletRequest = RequestContextUtils.getRequest();
        Map<String, String> headers = getHeaders(httpServletRequest);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            template.header(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 获取原请求头
     */
    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                String value = request.getHeader(key);
                //需要传递的两个请求头==》 1.令牌信息  2. 灰度发布的请求头
                if (StrUtil.equals(TokenConstant.ACCESS_TOKEN, key)) {
                    map.put(key, value);
                }
            }
        }
        return map;
    }
}