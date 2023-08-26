package com.study.platform.cloud.sentinel.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.platform.base.enums.ResultCode;
import com.study.platform.base.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  自定义异常处理器
 */
@Slf4j
@Component
public class NuwaBlockExceptionHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        response.setStatus(429);
        response.setContentType("application/json;charset=utf-8");
        Result result = Result.error(ResultCode.SYSTEM_BUSY, ResultCode.SYSTEM_BUSY.getMessage());
        new ObjectMapper().writeValue(response.getWriter(), result);
    }

}
