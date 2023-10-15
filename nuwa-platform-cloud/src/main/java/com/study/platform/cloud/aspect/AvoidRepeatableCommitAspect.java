package com.study.platform.cloud.aspect;

import com.study.platform.base.constant.KeyConstant;
import com.study.platform.base.enums.ResultCode;
import com.study.platform.base.result.Result;
import com.study.platform.cloud.util.IpUtils;
import com.study.platform.cloud.util.RequestContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 非幂等的接口做了防重复提交
 * 1. 根据请求的 ip+hash(类名+方法名) 作为key
 * 2. 将这个key存入redis中，且设置过期时间，在过期之前保持互斥
 * 注意：当然一定要在接口执行之前拦截，可以使用拦截器或者AOP的方式
 */
@Aspect
@Component
@Slf4j
public class AvoidRepeatableCommitAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @param point
     */
    @Around("@annotation(com.study.platform.cloud.aspect.AvoidRepeatableCommit)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = RequestContextUtils.getRequest();
        String ip = IpUtils.getIpAddress(request);
        //获取注解
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //目标类、方法
        String className = method.getDeclaringClass().getName();
        String name = method.getName();
        // 得到类名和方法
        String ipKey = String.format("%s#%s", className, name);
        // 转换成HashCode:将类名+方法名做了一次hash
        int hashCode = Math.abs(ipKey.hashCode());
        // 拼接最终的key
        String key = String.format("%s:%s_%d", KeyConstant.AVOID_REPEATABLE_COMMIT, ip, hashCode);
        log.info("ipKey={}, hashCode={}, key={}", ipKey, hashCode, key);
        AvoidRepeatableCommit avoidRepeatableCommit = method.getAnnotation(AvoidRepeatableCommit.class);
        long timeout = avoidRepeatableCommit.timeout();
        // 设置过期时间:存入redis中，采用的是setNx互斥操作，如果返回false则直接返回重复提交。
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(key, UUID.randomUUID().toString(), timeout, TimeUnit.MILLISECONDS);
        if (!Objects.requireNonNull(flag)) {
            log.info("请勿重复提交表单");
            return Result.error(ResultCode.NO_REPEATABLE_SUBMIT.getCode(), ResultCode.NO_REPEATABLE_SUBMIT.getMessage());
        }
        //执行方法
        return point.proceed();
    }
}
