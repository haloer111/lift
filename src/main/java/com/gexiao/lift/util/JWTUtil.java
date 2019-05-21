package com.gexiao.lift.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * @Auther: gexiao
 * @Date: 2019/4/10 11:08
 * @Description:
 */
public class JWTUtil {
    /**
     * 默认使用系统当前时区
     */
    private static final ZoneId ZONE = ZoneId.systemDefault();

    /**
     * 默认时间单位: 天
     */
    private static final ChronoUnit DEFAULT_TIME_UNIT = ChronoUnit.DAYS;

    /**
     * 默认超时时间
     */
    private static final Long DEFAULT_EXPIRE = 1L;

    /**
     * 默认超时时间
     */
    private static final String  DEFAULT_KEY = Base64.getEncoder().encodeToString("gexiao".getBytes());

    /**
     * jwt存储用户ID的key
     */
    public static final String USER_ID = "userId";

    /**
     * jwt存储用户名的key
     */
    public static final String NAME = "name";

    /**
     * 默认算法HS256
     */
    public static final SignatureAlgorithm DEFAULT_SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    /**
     * jwt存储用户权限的key
     */
    public static final String ROLES = "roles";

    /**
     * jwt存储用户权限的key
     */
    public static final String HEADER_TOKEN = "X-user-info";



    /**
     * 生成JWT token
     * @param user 用户信息
     * @return
     */
    public static String generateToken(UserInfo user) {
        return generateToken(user, DEFAULT_KEY, DEFAULT_EXPIRE, DEFAULT_TIME_UNIT);
    }

    /**
     * 生成JWT token
     * @param user 用户信息
     * @param key 秘钥
     * @param expire 过期时间 ,默认天
     * @return
     */
    public static String generateToken(UserInfo user, String key, Long expire) {
        return generateToken(user, key, expire, DEFAULT_TIME_UNIT);
    }

    /**
     * 生成JWT token
     * @param user 用户信息
     * @param key 秘钥
     * @param expire 过期时间
     * @param unit 时间单位
     * @return
     */
    public static String generateToken(UserInfo user, String key , Long expire,ChronoUnit unit) {
        LocalDateTime now = LocalDateTime.now();
        Date expireTime = Date.from(now.plus(expire, unit).atZone(ZONE).toInstant());
        return Jwts.builder()
                   .setSubject(user.getLoginName())
                   .claim(USER_ID, user.getId())
                   .claim(NAME, user.getName())
                   .claim(ROLES, user.getRoles())
                   .signWith(DEFAULT_SIGNATURE_ALGORITHM,key)
                   .setExpiration(expireTime)
                   .compact();
    }

    /**
     * 从request的头或者参数中获取用户信息
     * @param request
     * @return
     */
    public static UserInfo getUserByRequest(HttpServletRequest request) {
        String token;
        token = request.getHeader(HEADER_TOKEN);

        if (StringUtils.isBlank(token)) {
            token = request.getParameter(HEADER_TOKEN);
            if (StringUtils.isBlank(token))
                return null;
        }

        Jws<Claims> claims = parseToken(token);

        return new UserInfo()
                            .setId(claims.getBody().get(USER_ID).toString())
                            .setLoginName(claims.getBody().getSubject())
                            .setName(claims.getBody().get(NAME).toString())
                            .setRoles((List<String>) claims.getBody().get(ROLES));
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    private static Jws<Claims> parseToken(String token){
        return Jwts.parser().setSigningKey(DEFAULT_KEY).parseClaimsJws(token);
    }






    public static class UserInfo {
        private String name;
        private String id;
        private List<String> roles;
        private String loginName;

        public String getName() {
            return name;
        }

        public UserInfo setName(String name) {
            this.name = name;
            return this;
        }

        public String getId() {
            return id;
        }

        public UserInfo setId(String id) {
            this.id = id;
            return this;
        }

        public List<String> getRoles() {
            return roles;
        }

        public UserInfo setRoles(List<String> roles) {
            this.roles = roles;
            return this;

        }

        public String getLoginName() {
            return loginName;
        }

        public UserInfo setLoginName(String loginName) {
            this.loginName = loginName;
            return this;

        }
    }

}
