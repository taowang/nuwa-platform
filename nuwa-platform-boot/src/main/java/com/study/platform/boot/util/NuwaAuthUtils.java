package com.study.platform.boot.util;

import cn.hutool.json.JSONUtil;
import com.study.platform.base.constant.AuthConstant;
import com.study.platform.base.domain.NuwaUser;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author GitEgg
 */
public class NuwaAuthUtils {

    /**
     * 获取用户信息
     *
     * @return nuwaUser
     */
    public static NuwaUser getCurrentUser() {
        HttpServletRequest request = NuwaWebUtils.getRequest();
        if (request == null) {
            return null;
        }
        try {
            String user = request.getHeader(AuthConstant.HEADER_USER);
            if (StringUtils.isEmpty(user)) {
                return null;
            }
            String userStr = URLDecoder.decode(user, "UTF-8");
            NuwaUser nuwaUser = JSONUtil.toBean(userStr, NuwaUser.class);
            return nuwaUser;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获取租户Id
     *
     * @return tenantId
     */
    public static String getTenantId() {
        HttpServletRequest request = NuwaWebUtils.getRequest();
        if (request == null) {
            return null;
        }
        try {
            String tenantId = request.getHeader(AuthConstant.TENANT_ID);
            tenantId = StringUtils.isEmpty(tenantId) ? String.valueOf(AuthConstant.DEFAULT_TENANT_ID) : tenantId;
            String user = request.getHeader(AuthConstant.HEADER_USER);
            //如果请求头中的tenantId为空，那么尝试是否能够从登陆用户中去获取租户id
            if (StringUtils.isEmpty(tenantId) && !StringUtils.isEmpty(user)) {
                String userStr = URLDecoder.decode(user, "UTF-8");
                NuwaUser nuwaUser = JSONUtil.toBean(userStr, NuwaUser.class);
                if (null != nuwaUser) {
                    tenantId = nuwaUser.getTenantId();
                }
            }
            return tenantId;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

    }
}
