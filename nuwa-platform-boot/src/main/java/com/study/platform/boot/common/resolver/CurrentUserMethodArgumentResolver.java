package com.study.platform.boot.common.resolver;

import com.study.platform.base.annotation.auth.CurrentUser;
import com.study.platform.base.domain.NuwaUser;
import com.study.platform.boot.util.NuwaAuthUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author GitEgg
 * @ClassName: CurrentUserMethodArgumentResolver
 * @Description: 增加方法注入，将含有 @CurrentUser 注解的方法参数注入当前登录用户
 * @date 2019年5月18日 下午3:59:17
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(NuwaUser.class)
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        NuwaUser user = NuwaAuthUtils.getCurrentUser();
        return user;
    }
}
