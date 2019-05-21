package com.gexiao.lift.config;

import com.alibaba.fastjson.JSONObject;
import com.gexiao.lift.common.Result;
import com.gexiao.lift.common.UserConstant;
import com.gexiao.lift.entity.Authority;
import com.gexiao.lift.entity.RoleAuth;
import com.gexiao.lift.service.IAuthService;
import com.gexiao.lift.service.IRoleAuthService;
import com.gexiao.lift.util.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录拦截器,用于鉴权
 *
 * @Auther: gexiao
 * @Date: 2019/4/10 16:22
 * @Description:
 */
public class LoginInterceptor implements HandlerInterceptor {

    private final static String[] DEFAULT_HTTP_METHOD_LIST = {"GET","POST","DELETE","PUT"};


    @Autowired
    private GatewayParams gatewayParams;
    @Autowired
    private IAuthService authService;
    @Autowired
    private IRoleAuthService roleAuthService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        System.out.println("请求uri = " + uri);

        // 是否需要拦截
        if (isIntercept(uri))
            return true;

        //从token中获取用户信息
        JWTUtil.UserInfo user = JWTUtil.getUserByRequest(request);
        if (user == null) {
            respondToClientMsg(response, "token异常");
            return false;
        }

        //校验用户的权限
        List<Authority> resources = authService.list();
        //权限资源列表,如果匹配的
        if (resources.stream().anyMatch(e -> matchUri(uri, e.getUrl()))) {
            for (String role : user.getRoles()) {
                //角色拥有的资源权限
                List<String> authIdList = roleAuthService.listByRoleId(role).stream().map(RoleAuth::getAuthId).collect(Collectors.toList());
                Collection<Authority> authList = authService.listByIds(authIdList);
                if (authList.stream().noneMatch(roleAuth -> (matchUri(uri, roleAuth.getUrl())
                                                          && matchMethod(method, roleAuth.getHttpMethod())))) {
                    respondToClientMsg(response, "用户没有权限");
                    return false;
                }
                break;
            }
        }

        //将用户名.id放入request中
        request.setAttribute(UserConstant.USER_ID,user.getId());
        request.setAttribute(UserConstant.NAME,user.getName());

        return true;
    }

    /**
     * 响应给客户端内容
     *
     * @param response
     * @param msg      响应内容
     * @throws IOException
     */
    private void respondToClientMsg(HttpServletResponse response, String msg) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(JSONObject.toJSONString(Result.fail(msg)));
    }

    /**
     * 是否需要拦截uri
     */
    private boolean isIntercept(String uri) {
        boolean flag = false;
        for (String unchecked : gatewayParams.getUnchecked()) {
            if (unchecked.equals(uri))
                flag = true;
        }
        return flag;
    }

    /**
     * 匹配url
     *
     * @param uri     需要匹配的uri
     * @param destUri 目标uri
     * @return
     */
    private boolean matchUri(String uri, String destUri) {
        //先处理destUri存在/**的路径
        if (destUri.matches(".*[\\*]{2}"))
            // /xxx/.*
            destUri = destUri.substring(0, destUri.length() - 2) + ".*";
        else
            destUri = destUri + ".*";
        return uri.matches(destUri);
    }


    /**
     * 匹配httpMethod
     *
     * @param method     方法
     * @param destMethod 目标方法
     * @return
     */
    private boolean matchMethod(String method, String destMethod) {
        boolean flag = false;
        String[] methods;

        if (StringUtils.isBlank(destMethod))
            return false;

        if (destMethod.matches("\\*"))
            methods = DEFAULT_HTTP_METHOD_LIST;
        else
            methods = destMethod.split(",");
        for (String m : methods) {
            if (method.equalsIgnoreCase(m))
                flag = true;
        }
        return flag;
    }
}
