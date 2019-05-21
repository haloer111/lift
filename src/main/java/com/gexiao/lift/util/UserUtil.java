package com.gexiao.lift.util;


import com.gexiao.lift.common.UserConstant;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

/**
 * 用户工具类（主要用于获取当前请求的用户ID）
 *
 * @author : lzx
 * created on 2018/01/05
 */
public class UserUtil {

    /**
     * 获取请求中的用户ID
     *
     * @return userId
     */
    public static String getCurrentUserId() {
        return Objects.requireNonNull(getRequestAttribute(UserConstant.USER_ID, RequestAttributes.SCOPE_REQUEST), "获取用户ID失败，请确认请求来源").toString();
    }

    /**
     * 获取请求中的用户名
     *
     * @return userName
     */
    public static String getCurrentUserName() {
        return Objects.requireNonNull(getRequestAttribute(UserConstant.NAME, RequestAttributes.SCOPE_REQUEST), "获取用户name失败，请确认请求来源").toString();
    }

    /**
     * 通过RequestAttributes获取请求中的参数
     *
     * @param key   参数名
     * @param scope 获取范围（详见RequestAttributes）
     * @see RequestAttributes
     */
    private static Object getRequestAttribute(String key, int scope) {
        RequestAttributes requestAttributes = Objects.requireNonNull(RequestContextHolder.getRequestAttributes(), "请确认当前处于web环境中");
        return requestAttributes.getAttribute(key, scope);
    }

    private UserUtil() {
    }

}
