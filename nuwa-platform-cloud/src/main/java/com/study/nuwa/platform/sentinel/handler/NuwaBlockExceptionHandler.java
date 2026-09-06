package com.study.nuwa.platform.sentinel.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.platform.enums.ResultCode;
import com.study.platform.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 自定义 Sentinel 限流/熔断异常处理器：被流控时返回统一 JSON 响应。
 */
@Slf4j
@Component
public class NuwaBlockExceptionHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, String ruleName, BlockException e) throws Exception {
        // BlockException 的具体子类（FlowException / DegradeException / ParamFlowException 等）
        log.warn("Sentinel block: rule={}, route={}", ruleName, request.getRequestURI());
        response.setStatus(429);
        response.setContentType("application/json;charset=utf-8");
        Result<?> result = Result.error(ResultCode.SYSTEM_BUSY, ResultCode.SYSTEM_BUSY.getMessage());
        new ObjectMapper().writeValue(response.getWriter(), result);
    }
}
